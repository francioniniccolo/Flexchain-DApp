import raw from 'raw.macro';
import "bpmn-js/dist/assets/diagram-js.css";
import "bpmn-js/dist/assets/bpmn-font/css/bpmn-embedded.css";
import Container from "react-bootstrap/Container";
import ChorJS from 'chor-js/lib/Modeler';
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import React, {useState, useEffect} from "react";
import {RiFileAddLine as IconNew} from 'react-icons/ri'
import {RiDownloadCloudFill as IconDownload} from 'react-icons/ri'
import {RiUploadCloudFill as IconUpload} from 'react-icons/ri'
import {TiTick as TickIcon} from 'react-icons/ti'
import {GoChevronRight as RightIcon} from 'react-icons/go'
import Web3 from "web3";
import propertiesPanelModule from 'bpmn-js-properties-panel';
import propertiesProviderModule from 'bpmn-js-properties-panel/lib/provider/bpmn';
import 'bpmn-js-properties-panel/dist/assets/bpmn-js-properties-panel.css'
import Offcanvas from 'react-bootstrap/Offcanvas'
import {deployProcessTemplate,deployMonitor} from "../BlockchainFunctions";
import Alert from '@mui/material/Alert';





export default function BpmnModeler() {

    const [modeler, setModeler] = useState();
    const[diagramName,setDiagramName]=useState('diagram.bpmn');

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => {
        setShow(true);
    }


    useEffect(() => {
        const model = new ChorJS({
            container: '#canvas',
            propertiesPanel: {
                parent: '#properties-panel'
            },
            additionalModules: [
                propertiesPanelModule,
                propertiesProviderModule
            ],
            keyboard: {
                bindTo: document
            }
        });
        setModeler(model);
        //deployMonitor();
    }, [])

    function attachPanel() {
        const properties = modeler.get('propertiesPanel');
        properties.detach();
        properties.attachTo('#panel');
    }


    return (
        <Container fluid>
            <h5 title='Show properties panel' style={{fontFamily:'Arial', width:'130px'}} className='mt-4' onClick={handleShow} onMouseLeave={(e)=>e.target.style.color='black'} onMouseOver={(e)=>e.target.style.color='grey'}><RightIcon/>Properties</h5>
            <div className='mb-3' id="canvas" style={{height: 600, width: '100%', border: '1px solid grey'}}/>
            <div id="properties-panel" style={{display: 'none'}}></div>

            <Offcanvas show={show} onHide={handleClose} onShow={() => attachPanel()}>
                <Offcanvas.Header closeButton>
                    <Offcanvas.Title>Properties Panel</Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>
                    <div id="panel"></div>
                </Offcanvas.Body>
            </Offcanvas>


            <div style={{textAlign: "left"}}>
                <Button title='Create new diagram' onClick={() => {
                    createNewDiagram(modeler)
                }} style={{}}><IconNew size='40' style={{display: 'inline-block'}}/></Button>

                <Button title="Download BPMN XML file" onClick={() =>downloadFile(modeler)}
                        style={{display: 'inline-block', marginLeft: '30px'}}><IconDownload size='40'/></Button>

                <Button title="Upload BPMN XML file" onClick={() => {
                    UploadBtnClicked(document.getElementById('upload'))
                }} style={{display: 'inline-block', marginLeft: '30px'}}><IconUpload size='40'/></Button>

                <Form.Control id='upload' type="file" accept=".bpmn, .xml" onChange={(event) => {
                    loadDiagram(event.target.files[0], modeler);
                    setDiagramName(event.target.files[0].name);
                }} style={{display: 'none'}}/>

                <Button style={{display: 'inline-block', marginLeft: '30px', height: '54px'}} onClick={() => deployProcessTemplate(diagramName)}>Deploy<TickIcon/></Button>
            </div>

        </Container>

    );

}


function UploadBtnClicked(upload) {
    upload.click();
}

async function createNewDiagram(modeler) {
    const diagram = raw("../../diagrams/emptyDiagram.bpmn");
    await modeler.importXML(diagram);
}

async function downloadFile(modeler) {
   try {
       const result = await modeler.saveXML({format: true});
       const url = 'data:application/bpmn20-xml;charset=UTF-8,' + encodeURIComponent(result.xml);
       const link = document.createElement('a');
       link.download = 'diagram.bpmn';
       link.href = url;
       link.click();
   }catch (e){alert("Caricare un diagramma prima del download")}
}


function loadDiagram(file, modeler) {
    if (file) {
        const reader = new FileReader();
        reader.onload = async () => {
            await modeler.importXML(reader.result);
        }
        reader.readAsText(file);

    }
}


