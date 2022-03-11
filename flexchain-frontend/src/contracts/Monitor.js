//todo
export const MONITOR_ADDRESS="0x4D24942083BebbF3f2eF5b8228d62acd2c9152Fe";
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