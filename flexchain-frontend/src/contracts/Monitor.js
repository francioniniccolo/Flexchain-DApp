export const MONITOR_ADDRESS="0x3300BD9210CB019bDD75d749Ee4193462dBB3E94";
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