export const MONITOR_ADDRESS="0x2430Dd4e80DE0d8269f9842dE35C2653d64Be460";
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