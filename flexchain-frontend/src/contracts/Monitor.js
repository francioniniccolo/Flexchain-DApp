export const MONITOR_ADDRESS="0xC658CFAfFc27Ad661966F84EFeD0BF3076883b6e";
export const ABI=[
    {
        "inputs": [],
        "name": "getDiagramNames",
        "outputs": [
            {
                "internalType": "string[]",
                "name": "",
                "type": "string[]"
            }
        ],
        "stateMutability": "view",
        "type": "function"
    },
    {
        "inputs": [
            {
                "internalType": "string",
                "name": "processName",
                "type": "string"
            }
        ],
        "name": "getProcess",
        "outputs": [
            {
                "internalType": "contract ProcessTemplate",
                "name": "",
                "type": "address"
            }
        ],
        "stateMutability": "view",
        "type": "function"
    },
    {
        "inputs": [
            {
                "internalType": "string",
                "name": "processName",
                "type": "string"
            }
        ],
        "name": "getProcessOwner",
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
        "inputs": [
            {
                "internalType": "string",
                "name": "processName",
                "type": "string"
            }
        ],
        "name": "instantiateProcess",
        "outputs": [],
        "stateMutability": "nonpayable",
        "type": "function"
    }
];