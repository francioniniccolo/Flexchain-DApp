import React from "react";
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import {Button} from "react-bootstrap";
import {voteProposal} from "../BlockchainFunctions";

export default function EventAccordion(props) {

   function eventTypeControl(event){
       if(event.event=="messageExecute"){
           return (
               <div>
                   <b>{"Message ID: "+event.returnValues.messageId}</b>
                   <br/>
                   <b>{" \nInputs: "+event.returnValues.inputs}</b>
           </div>); }
       if(event.event=="proposalStatus"){return <Button variant="primary" onClick={async ()=>{ await voteProposal(props.address)}}>Vote</Button>}
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