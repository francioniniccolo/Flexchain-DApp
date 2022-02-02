import * as React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import {getProcessAddress} from "../BlockchainFunctions";


export default function SelectAddress(props) {
    const [address, setAddress] = React.useState('');
    const handleChange = async (event) => {
        setAddress(event.target.value);
        await getProcessAddress(event.target.value).then(res =>props.childToParent(res));
    };


     function getAddress(){return address}

    return (
        <div>
            <FormControl sx={{ m: 1, minWidth: 200 }}>
                <InputLabel id="demo-simple-select-autowidth-label">Select Contract</InputLabel>
                <Select
                    labelId="demo-simple-select-autowidth-label"
                    id="demo-simple-select-autowidth"
                    value={address}
                    onChange={handleChange}
                    autoWidth
                    label="Contract Address"
                >
                       {props.diagramsList.map(

                           (diagram)=>(
                                <MenuItem value={diagram}>
                                 <em>{diagram}</em>
                                </MenuItem>

                            )
                        )}
                </Select>
            </FormControl>
        </div>
    );
}

