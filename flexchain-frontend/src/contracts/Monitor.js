//todo
export const MONITOR_ADDRESS="0xE87c98e293460784c5E00f7F99862B6548E09a2c";
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