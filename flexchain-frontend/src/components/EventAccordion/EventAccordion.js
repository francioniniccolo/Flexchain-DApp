import React, {useEffect, useState} from "react";
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import {Button} from "react-bootstrap";
import {voteProposal} from "../BlockchainFunctions";

export default function EventAccordion(props) {

   let quorumReached=false;
   let eventProposalCounter=0;
    let c=0;


       function checkQuorum() {
           props.events.forEach((event) => {
               if (event.event == "proposalStatus") {
                   eventProposalCounter++;
                   console.log('increased!');
               }
           })
           if (eventProposalCounter == 2) {
               quorumReached = true;
           }
       }

     if(props.events){ checkQuorum();}

    function eventTypeControl(event){
        console.log('quorum:'+quorumReached);
        console.log('event count:'+eventProposalCounter);
        if(event.event=="messageExecute"){
            return (
                <div>
                    <b>{"Message ID: "+event.returnValues.messageId}</b>
                    <br/>
                    <b>{" \nInputs: "+event.returnValues.inputs}</b>
                </div>); }
        if(event.event=="proposalStatus"&&quorumReached==false){return <Button key={c++} variant="primary" onClick={async ()=>{ await voteProposal(props.address)}}>Vote</Button>}
        if(quorumReached==true){return <p style={{fontWeight:"bold"}}>Quorum raggiunto</p>}
    }

    return(
        <div>
            {props.events.map( (event)=>(<Accordion>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                >
                    <Typography>Event: {event.event}</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography>
                        {eventTypeControl(event)}
                    </Typography>
                </AccordionDetails>
            </Accordion>))}

        </div>
    );

}