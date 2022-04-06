//todo
export const MONITOR_ADDRESS="0x25E9A9981682Fc4C6fE4BaECD7b3746aBc7EeD2e";
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