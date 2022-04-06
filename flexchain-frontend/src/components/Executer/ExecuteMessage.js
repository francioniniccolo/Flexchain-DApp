import React, {useState, useEffect} from "react";
import {getDiagramNames, getSender, getWeb3,getIds} from "../BlockchainFunctions";
import Container from 'react-bootstrap/Container'
import SelectAddress from "../SelectAddress/SelectAddress";
import SelectMessage from "../SelectMessage/SelectMessage"
import {Button, Form, Row, Col} from 'react-bootstrap';
import Viewer from 'chor-js/lib/NavigatedViewer';
import {RiUploadCloudFill as IconUpload} from "react-icons/ri";
import './style.css'
import {TEMPLATE_ABI} from "../../contracts/ProcessTemplate";
import Offcanvas from "react-bootstrap/Offcanvas";
import Modal from "react-bootstrap/Modal"
import {GoChevronLeft as RightIcon} from "react-icons/go";
import EventAccordion from "../EventAccordion/EventAccordion";

const ExecuteMessage = () => {

    const handleClose = () => setShow(false);
    const handleShow = async () => {
       if(address!=null) {
           const cont = new web3.eth.Contract(TEMPLATE_ABI, address);
           setContract(cont);
           await cont.getPastEvents('allEvents',
               {
                   fromBlock: 0,
                   toBlock: 'latest'
               }, function (error, e) {
                   setEvents(e);
                   console.log(e);
               });
           setShow(true);
       }else {alert("Selezionare un contratto!")}
    }

    const handleCloseModal = () => setShowModal(false);
    const handleShowModal = () => setShowModal(true);

    const[varValue,setVarValue]=useState();
    const[varName,setVarName]=useState("");
    const [showModal, setShowModal] = useState(false);
    const [show, setShow] = useState(false);
    const [parameters, setParameters] = useState();
    const [message, setMessage] = useState();
    const [previousMessage, setPreviousMessage] = useState(null);
    const [diagramsList, setDiagramsList] = useState([]);
    const [ids, setIds] = useState([]);
    const [contract, setContract] = useState();
    const [events,setEvents]=useState();
    const [abi, setAbi] = useState();
    const [address, setAddress] = useState(null);
    const [viewer, setViewer] = useState();
    const web3 = getWeb3();

    useEffect(async () => {
        const viewer = new Viewer({
            container: '#canvas-viewer',
            keyboard: {
                bindTo: document
            }
        });
        setViewer(viewer);
        const result = await getDiagramNames();
        setDiagramsList(result);
        setAbi(TEMPLATE_ABI);
    }, [])

    const getAddressFromSelect = async (data) => {
        console.log(data)
        setAddress(data)
        const ids = await getIds(data);
        setIds(ids);
        const i= await fetch('/messageListener/'+data, {
            method: 'POST'
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });

    }

    const getMessage = (message) => {
        setMessage(message);
        let canvas = viewer.get('canvas');
        let registry = viewer.get('elementRegistry')
        const elements = registry.getAll();
        let check = diagramCheck(ids, elements)

        if (check == true) {
            alert("Diagramma e contratto non corrispondono")

        } else {
            elements.map((element) => {

                //console.log(element.businessObject.id)
                if (element.businessObject.id == message) {
                    if (previousMessage != null) {
                        console.log(previousMessage.businessObject.id)
                        canvas.removeMarker(previousMessage, 'highlight')
                        console.log("remove")
                    }
                    canvas.addMarker(element, 'highlight');
                    console.log(element)
                    setPreviousMessage(element)
                }

            })
        }
    }

    function loadDiagram(file) {
        if (file) {
            const reader = new FileReader();
            reader.onload = async () => {
                await viewer.importXML(reader.result);
            }
            reader.readAsText(file);
        }

    }

   async function getValue(){
        const response= await fetch('/getValue/'+varName+"/"+address);
        setVarValue( await response.text());
    }

    return (
        <Container className='mt-5'>

            <Modal show={showModal} onHide={handleCloseModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Get variable value from contract</Modal.Title>
                </Modal.Header>
                <Modal.Body><Form.Label style={{marginBottom: '0'}}>Variable name</Form.Label>
                    <Form.Control type="text" placeholder="Insert name..." style={{width: '40%'}}
                                  onChange={e => setVarName([e.target.value])}
                    />
                    <br/>
                    <Form.Control
                        style={{width: '40%'}}
                        type="text"
                        value={varValue}
                        disabled
                        readOnly
                    />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseModal}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={(e)=>getValue()}>
                        Get Value
                    </Button>
                </Modal.Footer>
            </Modal>

            <h5 title='Show events panel' style={{fontFamily:'Arial', width:'200px',float:'right'}} className='mt-4' onClick={handleShow} onMouseLeave={(e)=>e.target.style.color='black'} onMouseOver={(e)=>e.target.style.color='grey'}><i>Events Panel</i><RightIcon/></h5>

            <Offcanvas placement={'end'} show={show} onHide={handleClose} >
                <Offcanvas.Header closeButton>
                    <Offcanvas.Title>Events Panel</Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>
                 <EventAccordion events={events} address={address}/>
                </Offcanvas.Body>
            </Offcanvas>

            <SelectAddress  diagramsList={diagramsList} childToParent={getAddressFromSelect}/>
            <Form className='mt-4'>
                <Form.Group as={Row} className="mb-3" controlId="formBasicEmail">

                    <Col xs={2}>
                        <SelectMessage idsList={ids} getMessage={getMessage}/>
                    </Col>
                    <Col>
                        <Form.Label style={{marginBottom: '0'}}>Message Parameters</Form.Label>
                        <Form.Control type="text" placeholder="Insert parameters..." style={{width: '30%'}}
                                      value={parameters}
                                      onChange={e => setParameters([e.target.value])}
                        />
                    </Col>
                </Form.Group>
                <Button variant="primary" onClick={() => executeMessage(address, message, parameters)}>
                    Execute message
                </Button>
                <Button variant="info" style={{marginLeft:'5px'}} onClick={handleShowModal}>
                    Get Variable Value
                </Button>
            </Form>
            <div className='mt-3' id="canvas-viewer" style={{height: 600, width: '100%', border: '1px solid grey'}}>
                <Button title="Upload BPMN XML file" onClick={() => {
                    UploadBtnClicked(document.getElementById('upload'))
                }}><IconUpload size='40'/></Button>
            </div>
            <Form.Control id='upload' type="file" accept=".bpmn, .xml" onChange={(event) => {
                loadDiagram(event.target.files[0]);
            }} style={{display: 'none'}}/>
        </Container>
    );
}

export default ExecuteMessage;

export function diagramCheck(ids, elements) {
    //ids.map((id)=>console.log('id:'+
    let check = false;
    let boi = [];
    elements.map((element) => {
        boi.push(element.businessObject.id)
    });
    ids.map((id) => {
        if (!boi.includes(id)) {
            check = true
        }
    })
    return check;
}


function UploadBtnClicked(upload) {
    upload.click();
}


async function executeMessage(address, message, parameters) {
    const web3 = getWeb3();
    const account = await getSender(web3);
    console.log(message)
    console.log(parameters)
    const cont = new web3.eth.Contract(TEMPLATE_ABI, address);
    await cont.methods.executeMessage(message, parameters).send({from: account});
}