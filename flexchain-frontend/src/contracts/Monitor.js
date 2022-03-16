//todo
export const MONITOR_ADDRESS="0x5c58fb00F0d6A2069649BbFB43d856d88f82b73b";
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
			},
			{
				"internalType": "uint256",
				"name": "_quorum",
				"type": "uint256"
			}
		],
		"name": "instantiateProcess",
		"outputs": [],
		"stateMutability": "nonpayable",
		"type": "function"
	}
];