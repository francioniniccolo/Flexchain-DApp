import React from "react";
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

export default function EventAccordion(props) {


    return(
        <div>
            {props.events.map( (event)=>(<Accordion>
                <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                >
                    <Typography>Event: {event.event}({/*event.returnValues[0]*/})</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography>
                        <b>{/*event.returnValues[1]*/}</b>
                    </Typography>
                </AccordionDetails>
            </Accordion>))}

        </div>
    );

}