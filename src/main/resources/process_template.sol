//SPDX-License-Identifier: GPL-3.0
pragma solidity  0.8.7;

contract ProcessMonitor{

    mapping(string =>ProcessTemplate) processes;
    string[] diagramNames;

    function instantiateProcess(string memory processName, uint _quorum,bool isNew) public{
        //Deployment del processo ed inserimento nel mapping
        processes[processName] = new ProcessTemplate(msg.sender, _quorum);
        if(isNew==true){diagramNames.push(processName);}
    }

    function getProcess(string  memory processName) public view returns (ProcessTemplate) {
        return  processes[processName];
    }

    function getDiagramNames() public view returns (string[] memory){
        return diagramNames;
    }



}


pragma solidity  0.8.7;

contract ProcessTemplate{

    enum State { ENABLED, DISABLED, COMPLETED }

    event messageExecute(string messageId, string[] inputs);

    string ids_ipfs;
    string rules_ipfs;

    string[]  IDs;

    mapping(string => State)  elements;
    mapping(bytes32 => bytes32) allValues;

    event proposalStatus(Proposal p);



    struct Voter{
        bool voted;
        bool vote;
    }
    struct Proposal{
        string proposedHashRules;
        string proposedHashIds;
        uint quorum;
        uint actualVotes;
    }
    Proposal p;


    mapping(address => Voter) public voters;



    address private owner;

    constructor(address sender, uint _quorum){
        owner = sender;
        p.quorum = _quorum;

    }

    function getOwner() public view returns (address) {
        return owner;
    }

    function getMessage(string memory messageId) public view returns (State){
        return elements[messageId];
    }


    function setRulesAndIdsIpfs(string memory rules_hash, string memory ids_hash)public{
        rules_ipfs = rules_hash;
        ids_ipfs = ids_hash;
    }

    function getRulesIpfs() public view returns (string memory) {
        return rules_ipfs;
    }

    function getIdsIpfs() public view returns (string memory) {
        return ids_ipfs;
    }


    function setVariables(bytes32[] memory names, bytes32[] memory values, string memory messageID) public{
        for(uint i = 0; i < names.length; i++){
            allValues[names[i]] = values[i];
        }
        elements[messageID] = State.COMPLETED;
    }

    //the user updating the rules votes for the proposal and propagates the new hash with the quorum required
    function createProposal(string memory _proposalHashRules, string memory _proposalHashIds) public{
        voters[msg.sender] = Voter(true, true);
        p.proposedHashRules = _proposalHashRules;
        p.proposedHashIds = _proposalHashIds;
        p.actualVotes = p.actualVotes + 1;
        emit proposalStatus(p);
    }

    function voteProposal(bool _vote) public{
        //require(voters[msg.sender].voted != true);
        voters[msg.sender] = Voter(true, _vote);
        if(_vote == true){
            p.actualVotes++;
        }
        if(p.actualVotes == p.quorum){
            emit proposalStatus(p);
            setRulesAndIdsIpfs(p.proposedHashRules,p.proposedHashIds);
        }
    }

    function executeMessage(string memory messageToExecute, string[] memory inputs) public {
        // elements[messageToExecute] = State.ENABLED;
        emit messageExecute(messageToExecute, inputs);

    }


    function getVariable(bytes32 name)public view returns (bytes32 ){
        return allValues[name];
    }

}