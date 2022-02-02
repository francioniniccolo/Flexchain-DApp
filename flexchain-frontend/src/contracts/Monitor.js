export const MONITOR_ADDRESS="0x0947A2A7Cb8117A066Ba259BFACF3e470eA2CB50";
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
]