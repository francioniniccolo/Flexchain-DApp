export const MONITOR_ADDRESS="0x4bb33c267A38019fF20b5C09Fa1362F19Ab2efE9";
//export const MONITOR_ADDRESS="0x849Bfe25F0f6F55Fd96f8fB0949EcDF9831DBFB7";
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
			},
			{
				"internalType": "uint256",
				"name": "_quorum",
				"type": "uint256"
			},
			{
				"internalType": "bool",
				"name": "isNew",
				"type": "bool"
			}
		],
		"name": "instantiateProcess",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	}
];
