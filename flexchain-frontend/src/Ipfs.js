/* src/App.js */
import './App.css'
import { useState } from 'react'
import { create } from 'ipfs-http-client'
import Button from "react-bootstrap/Button";
import {saveRulesToIPFS} from "./components/BlockchainFunctions";


//const client = create('https://ipfs.infura.io:5001/api/v0');

async function save(){
   /* //let ipfs = await create("https://api.pinata.cloud/psa")

    const added = await client.add('vediamo se cambia hash')
    const url = `https://ipfs.infura.io/ipfs/${added.path}`
    console.log(url)*/
}


function Ipfs() {
  //  const [fileUrl, updateFileUrl] = useState(``)
  //  async function onChange(e) {
       /* const file = e.target.files[0]
        try {
            const added = await client.add(file)
             const url = `https://ipfs.infura.io/ipfs/${added.path}`
             updateFileUrl(url)
        } catch (error) {
            console.log('Error uploading file: ', error)
        }*/
  //  }
    
    
    async function retrieveFile() {
     //   const response = await fetch(fileUrl);
      //  console.log(await response.json());
    }

    return (
        <div className="App">
          {/*  <h1>IPFS Example</h1>
            <input
                type="file"
                onChange={onChange}
            />
            {
                fileUrl && (
                   <p>{fileUrl}</p>
                )
            }
            <Button onClick={save}>save</Button>*/}
        </div>

    );
}

export default Ipfs;