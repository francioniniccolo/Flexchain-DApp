import * as React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import {TEMPLATE_ABI} from "../../contracts/ProcessTemplate";
import {getDiagramNames, getWeb3} from "../BlockchainFunctions";
import {useEffect, useState} from "react";
import Viewer from "chor-js/lib/NavigatedViewer";

export default function SelectMessage(props) {

    const [message, setMessage] = React.useState('');
    const [messageStatus, setMessageStatus] = React.useState([]);

    const handleChange = (event) => {
        setMessage(event.target.value);
        props.getMessage(event.target.value);
    };

    /* useEffect(()=>{
         setMessageStatus(props.idsStatus)
     },[props.idsStatus])*/
    useEffect(() => {
        console.log(props.idsStatus);
        console.log(props.idsList);
    }, [props.idsStatus, props.idsList])


    function Greeting(props) {
        const status = props.status;
        const id = props.id;
        if (status) {
            return <MenuItem disabled={true} key={id} value={id}>{id}</MenuItem>;
        }
        return <MenuItem disabled={false} key={id} value={id}>{id}</MenuItem>;
    }


    return (
        <div>
            <FormControl sx={{m: 1, minWidth: 150}}>
                <InputLabel id="demo-simple-select-autowidth-label">Message ID</InputLabel>
                <Select
                    labelId="demo-simple-select-autowidth-label"
                    id="demo-simple-select-autowidth"
                    value={message}
                    onChange={handleChange}
                    autoWidth
                    label="Message ID"
                >
                    {props.idsList.map(
                        (id, index) => (
                            <MenuItem disabled={props.idsStatus[index]} key={id} value={id}>{id}</MenuItem>
                        )
                        )}


                </Select>
            </FormControl>
        </div>
    );
}
