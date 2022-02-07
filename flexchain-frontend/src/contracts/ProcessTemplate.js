export const TEMPLATE_ABI=[
    {
        "inputs": [
            {
                "internalType": "address",
                "name": "sender",
                "type": "address"
            }
        ],
        "stateMutability": "nonpayable",
        "type": "constructor"
    },
    {
        "anonymous": false,
        "inputs": [
            {
                "indexed": false,
                "internalType": "string",
                "name": "messageId",
                "type": "string"
            },
            {
                "indexed": false,
                "internalType": "string[]",
                "name": "inputs",
                "type": "string[]"
            }
        ],
        "name": "messageExecute",
        "type": "event"
    },
    {
        "inputs": [
            {
                "internalType": "string",
                "name": "messageToExecute",
                "type": "string"
            },
            {
                "internalType": "string[]",
                "name": "inputs",
                "type": "string[]"
            }
        ],
        "name": "executeMessage",
        "outputs": [],
        "stateMutability": "nonpayable",
        "type": "function"
    },
    {
        "inputs": [],
        "name": "getIdsIpfs",
        "outputs": [
            {
                "internalType": "string",
                "name": "",
                "type": "string"
            }
        ],
        "stateMutability": "view",
        "type": "function"
    },
    {
        "inputs": [
            {
                "internalType": "string",
                "name": "messageId",
                "type": "string"
            }
        ],
        "name": "getMessage",
        "outputs": [
            {
                "internalType": "enum ProcessTemplate.State",
                "name": "",
                "type": "uint8"
            }
        ],
        "stateMutability": "view",
        "type": "function"
    },
    {
        "inputs": [],
        "name": "getOwner",
        "outputs": [
            {
                "internalType": "address",
                "name": "",
                "type": "address"
            }
        ],
        "stateMutability": "view",
        "type": "function"
    },
    {
        "inputs": [],
        "name": "getRulesIpfs",
        "outputs": [
            {
                "internalType": "string",
                "name": "",
                "type": "string"
            }
        ],
        "stateMutability": "view",
        "type": "function"
    },
    {
        "inputs": [
            {
                "internalType": "string",
                "name": "rules_hash",
                "type": "string"
            },
            {
                "internalType": "string",
                "name": "ids_hash",
                "type": "string"
            }
        ],
        "name": "setRulesAndIdsIpfs",
        "outputs": [],
        "stateMutability": "nonpayable",
        "type": "function"
    },
    {
        "inputs": [
            {
                "internalType": "string[]",
                "name": "names",
                "type": "string[]"
            },
            {
                "internalType": "bytes32[]",
                "name": "values",
                "type": "bytes32[]"
            }
        ],
        "name": "setVariables",
        "outputs": [],
        "stateMutability": "nonpayable",
        "type": "function"
    },
    {
        "inputs": [
            {
                "internalType": "string",
                "name": "s",
                "type": "string"
            }
        ],
        "name": "stringToUint",
        "outputs": [
            {
                "internalType": "uint256",
                "name": "result",
                "type": "uint256"
            }
        ],
        "stateMutability": "pure",
        "type": "function"
    }
];