import Web3 from "web3";
import {ABI,MONITOR_ADDRESS} from '../contracts/Monitor'
import {TEMPLATE_ABI} from "../contracts/ProcessTemplate";
import { create } from 'ipfs-http-client'

const web3 = getWeb3();

export async function getProcessTemplateABI(){
    const response = await fetch('https://8a0e6be3-45af-4b45-9b82-2c5a99bd5d40.mock.pstmn.io/process');
    const abi =  await response.json() ;
    return abi.abi;
}


export async function deployProcessTemplate(contractName) {
    const account = await getSender(web3);
    const contract = new web3.eth.Contract(ABI,MONITOR_ADDRESS);
    await contract.methods.instantiateProcess(contractName).send({from:account});

    const serverRules= await fetch("/generate-rules");
    const serverRulesIds= await fetch("/generate-rules-id");
    const rul = (await serverRules.text()).split('$')
   const id = (await serverRulesIds.text()).split(',')
     const hash_rules= await saveRulesToIPFS(JSON.stringify(rul));
     const hash_ids=  await saveIdsToIPFS(JSON.stringify(id));

     console.log(hash_rules)
     console.log(hash_ids)

    const address = await getProcessAddress(contractName);
    await setRulesHash(hash_rules,hash_ids,address);

     console.log(await getIds(address));
    console.log(await getRules(address));

   /* const client = create('https://ipfs.infura.io:5001/api/v0');
    const file = new File(rul,"rules.json");
    try {
        const added = await client.add(file)
        const url = `https://ipfs.infura.io/ipfs/${added.path}`
        console.log(url);
    } catch (error) {
        console.log('Error uploading file: ', error)
    }*/
}

export function getWeb3() {
    return new Web3(Web3.givenProvider || "ws://localhost:8545");
}

export async function getSender(web3) {
    const accounts = await web3.eth.requestAccounts();
    return accounts[0];
}

export function getContract(web3, abi) {
    return new web3.eth.Contract(abi);
}

 export async function getDiagramNames(){
    const contract = new web3.eth.Contract(ABI,MONITOR_ADDRESS);
    const diagramsList = await contract.methods.getDiagramNames().call();
    return diagramsList;
}

export async function getProcessAddress(diagramName){
    const contract = new web3.eth.Contract(ABI,MONITOR_ADDRESS);
    const address = await contract.methods.getProcess(diagramName).call();
    return address;
}


export async function saveRulesToIPFS(rules){
    const client = create('https://ipfs.infura.io:5001/api/v0');
    const added = await client.add(rules)
    const url = added.path
    return url
}

export async function saveIdsToIPFS(ids){
    const client = create('https://ipfs.infura.io:5001/api/v0');
    const added = await client.add(ids)
    const url = added.path
    return url;
}

export async function setRulesHash(rules_hash,ids_hash,address){
    const account = await getSender(web3);
    const contract = new web3.eth.Contract(TEMPLATE_ABI,address);
    await contract.methods.setRulesAndIdsIpfs(rules_hash,ids_hash).send({from: account});
}

export async function getRules(address){
    const contract = new web3.eth.Contract(TEMPLATE_ABI,address);
    const hash = await contract.methods.getRulesIpfs().call();
    const url = new URL('https://ipfs.infura.io/ipfs/'+hash);
    const rules = await fetch(url);
    return await rules.json();
}

export async function getIds(address){
    const contract = new web3.eth.Contract(TEMPLATE_ABI,address);
    const hash = await contract.methods.getIdsIpfs().call();
    const url = new URL('https://ipfs.infura.io/ipfs/'+hash);
    const ids = await fetch(url);
    return await ids.json();
}



/*export async function deploy(name, abi, bytecode) {
    const account = await getSender(web3);
    const contract = getContract(web3, abi);
    const cont = await contract.deploy({data: bytecode,arguments:[account,name]}).send({from: account});
    const address = cont.options.address;
    const jsonData = {"address": address, "abi": abi};
    await setRules(address);

}*/

/*export async function setRules(address){
    const account = await getSender(web3);
    const contract = new web3.eth.Contract(TEMPLATE_ABI,address);
   // const ids = await fetch('https://8a0e6be3-45af-4b45-9b82-2c5a99bd5d40.mock.pstmn.io/generate-rules-id2');
   // const rules = await fetch('https://8a0e6be3-45af-4b45-9b82-2c5a99bd5d40.mock.pstmn.io/generate-rules2');
    const serverRules= await fetch("/generate-rules");
    const serverRulesIds= await fetch("/generate-rules-id");
    const id = (await serverRulesIds.text()).split(',');
    const rul = (await serverRules.text()).split('$');
   await contract.methods.setRules(id,rul).send({from: account});
}*/

/*export async function addRules(address){
    const account = await getSender(web3);
    const contract = new web3.eth.Contract(TEMPLATE_ABI,address);
    const ids = await fetch('https://8a0e6be3-45af-4b45-9b82-2c5a99bd5d40.mock.pstmn.io/add-rules-id');
    const rules = await fetch('https://8a0e6be3-45af-4b45-9b82-2c5a99bd5d40.mock.pstmn.io/add-rules');
    const id = (await ids.text()).split(',');
    const rul = (await rules.text()).split('$');
    const hash_rules= await saveRulesToIPFS(JSON.stringify(rul));
    const hash_ids= await saveIdsToIPFS(JSON.stringify(id));
    await setRulesHash(hash_rules,hash_ids,address);
}*/

/*export async function deleteRules(address){
    const account = await getSender(web3);
    const contract = new web3.eth.Contract(TEMPLATE_ABI,address);
    const ids = await fetch('https://8a0e6be3-45af-4b45-9b82-2c5a99bd5d40.mock.pstmn.io/delete-rules');
    const id = (await ids.text()).split(',');
    console.log(id);
    await contract.methods.deleteRules(id).send({from: account});
}*/
