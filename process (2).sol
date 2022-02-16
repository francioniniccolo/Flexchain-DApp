//SPDX-License-Identifier: GPL-3.0 
pragma solidity  0.8.7;

contract ProcessMonitor{

    //mapping che associa il nome del processo (es. pizzaDelivery) al contratto ProcessTemplate
    mapping(bytes32 => address) processes;
    bytes32[] diagramNames;
    
    
    function instantiateProcess(bytes32 processName) public{
       //Deployment del processo ed inserimento nel mapping
        processes[processName] = address(new ProcessTemplate(msg.sender));
          bool flag=false;
        for(uint i=0; i<diagramNames.length;i++){
            if(processName==diagramNames[i]){flag=true;}
        }
       if(flag==false){diagramNames.push(processName);}
    }

    function getProcess(bytes32 processName) public view returns (address) {
        return  processes[processName];
    } 

    function getDiagramNames() public view returns (bytes32[] memory){
        return diagramNames;
    } 
    
}


pragma solidity  0.8.7;

contract ProcessTemplate{
    
    enum State { ENABLED, DISABLED, COMPLETED }
    
    event messageExecute(string messageId, string[] inputs);


    bytes32 ids_ipfs;
    bytes32 rules_ipfs;

    string[]  IDs;

    mapping(string => string) rules;
    mapping(string => State)  elements;
    mapping(bytes32 => bytes32) allValues;

    
    address private owner;
    
    constructor(address sender){
        owner = sender;
    }

      function getOwner() public view returns (address) {
        return owner;
    }
    
    function getMessage(string memory messageId) public view returns (State){
        return elements[messageId];
    }


  function setRulesAndIdsIpfs(bytes32 rules_hash, bytes32 ids_hash)public{
        rules_ipfs = rules_hash;
        ids_ipfs = ids_hash;
    }

      function getRulesIpfs() public view returns (bytes32) {
        
        return rules_ipfs;
    }

      
    function getIdsIpfs() public view returns (bytes32) {
        return ids_ipfs;
    }


    function setVariables(bytes32[] memory names, bytes32[] memory values, string memory messageID) public{
        for(uint i = 0; i < names.length; i++){
            allValues[names[i]] = values[i];
        }
        elements[messageID] = State.COMPLETED;
    }

   

}