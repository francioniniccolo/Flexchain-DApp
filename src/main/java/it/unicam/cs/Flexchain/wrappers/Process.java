package it.unicam.cs.Flexchain.wrappers;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Process extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162001769380380620017698339818101604052810190620000379190620000b8565b81600a60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600560020181905550505062000176565b6000815190506200009b8162000142565b92915050565b600081519050620000b2816200015c565b92915050565b60008060408385031215620000d257620000d16200013d565b5b6000620000e2858286016200008a565b9250506020620000f585828601620000a1565b9150509250929050565b60006200010c8262000113565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600080fd5b6200014d81620000ff565b81146200015957600080fd5b50565b620001678162000133565b81146200017357600080fd5b50565b6115e380620001866000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806394bf28221161007157806394bf282214610150578063a3ec138d1461016e578063b489ab6d1461019f578063e96f8ba5146101bb578063f27a6e22146101d7578063f4d0c2b8146101f5576100a9565b80630cc4e8d8146100ae5780635d145416146100de57806365e481e2146100fa578063857ac45d14610116578063893d20e814610132575b600080fd5b6100c860048036038101906100c39190610c63565b610225565b6040516100d59190611089565b60405180910390f35b6100f860048036038101906100f39190610c09565b61025a565b005b610114600480360381019061010f9190610d24565b610491565b005b610130600480360381019061012b9190610d24565b6105b9565b005b61013a6105eb565b604051610147919061102a565b60405180910390f35b610158610615565b60405161016591906110a4565b60405180910390f35b61018860048036038101906101839190610b35565b6106a7565b604051610196929190611045565b60405180910390f35b6101b960048036038101906101b49190610b62565b6106e5565b005b6101d560048036038101906101d09190610cac565b61079d565b005b6101df6107da565b6040516101ec91906110a4565b60405180910390f35b61020f600480360381019061020a9190610c36565b61086c565b60405161021c919061106e565b60405180910390f35b60006003826040516102379190611013565b908152602001604051809103902060009054906101000a900460ff169050919050565b6040518060400160405280600115158152602001821515815250600960003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548160ff02191690831515021790555060208201518160000160016101000a81548160ff02191690831515021790555090505060011515811515141561031f5760056003016000815480929190610319906113e9565b91905055505b600560020154600560030154141561048e577f814dc6df136c5f9407476334aab3fde68ad032cc01721d1c460fea18e4abd3e8600560405161036191906110fd565b60405180910390a161048d6005600001805461037c9061136c565b80601f01602080910402602001604051908101604052809291908181526020018280546103a89061136c565b80156103f55780601f106103ca576101008083540402835291602001916103f5565b820191906000526020600020905b8154815290600101906020018083116103d857829003601f168201915b50505050506005600101805461040a9061136c565b80601f01602080910402602001604051908101604052809291908181526020018280546104369061136c565b80156104835780601f1061045857610100808354040283529160200191610483565b820191906000526020600020905b81548152906001019060200180831161046657829003601f168201915b50505050506105b9565b5b50565b604051806040016040528060011515815260200160011515815250600960003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548160ff02191690831515021790555060208201518160000160016101000a81548160ff0219169083151502179055509050508160056000019080519060200190610547929190610889565b508060056001019080519060200190610561929190610889565b5060016005600301546105749190611253565b6005600301819055507f814dc6df136c5f9407476334aab3fde68ad032cc01721d1c460fea18e4abd3e860056040516105ad91906110fd565b60405180910390a15050565b81600190805190602001906105cf929190610889565b5080600090805190602001906105e6929190610889565b505050565b6000600a60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6060600180546106249061136c565b80601f01602080910402602001604051908101604052809291908181526020018280546106509061136c565b801561069d5780601f106106725761010080835404028352916020019161069d565b820191906000526020600020905b81548152906001019060200180831161068057829003601f168201915b5050505050905090565b60096020528060005260406000206000915090508060000160009054906101000a900460ff16908060000160019054906101000a900460ff16905082565b60005b835181101561075057828181518110610704576107036114bf565b5b602002602001015160046000868481518110610723576107226114bf565b5b60200260200101518152602001908152602001600020819055508080610748906113e9565b9150506106e8565b5060026003826040516107639190611013565b908152602001604051809103902060006101000a81548160ff0219169083600281111561079357610792611461565b5b0217905550505050565b7f3be6db30a3513d60905e653d96c44308a41ff4ad025e6874321674c5b1857af182826040516107ce9291906110c6565b60405180910390a15050565b6060600080546107e99061136c565b80601f01602080910402602001604051908101604052809291908181526020018280546108159061136c565b80156108625780601f1061083757610100808354040283529160200191610862565b820191906000526020600020905b81548152906001019060200180831161084557829003601f168201915b5050505050905090565b600060046000838152602001908152602001600020549050919050565b8280546108959061136c565b90600052602060002090601f0160209004810192826108b757600085556108fe565b82601f106108d057805160ff19168380011785556108fe565b828001600101855582156108fe579182015b828111156108fd5782518255916020019190600101906108e2565b5b50905061090b919061090f565b5090565b5b80821115610928576000816000905550600101610910565b5090565b600061093f61093a84611144565b61111f565b9050808382526020820190508285602086028201111561096257610961611522565b5b60005b8581101561099257816109788882610af2565b845260208401935060208301925050600181019050610965565b5050509392505050565b60006109af6109aa84611170565b61111f565b905080838252602082019050828560208602820111156109d2576109d1611522565b5b60005b85811015610a2057813567ffffffffffffffff8111156109f8576109f761151d565b5b808601610a058982610b07565b855260208501945060208401935050506001810190506109d5565b5050509392505050565b6000610a3d610a388461119c565b61111f565b905082815260208101848484011115610a5957610a58611527565b5b610a6484828561132a565b509392505050565b600081359050610a7b81611568565b92915050565b600082601f830112610a9657610a9561151d565b5b8135610aa684826020860161092c565b91505092915050565b600082601f830112610ac457610ac361151d565b5b8135610ad484826020860161099c565b91505092915050565b600081359050610aec8161157f565b92915050565b600081359050610b0181611596565b92915050565b600082601f830112610b1c57610b1b61151d565b5b8135610b2c848260208601610a2a565b91505092915050565b600060208284031215610b4b57610b4a611531565b5b6000610b5984828501610a6c565b91505092915050565b600080600060608486031215610b7b57610b7a611531565b5b600084013567ffffffffffffffff811115610b9957610b9861152c565b5b610ba586828701610a81565b935050602084013567ffffffffffffffff811115610bc657610bc561152c565b5b610bd286828701610a81565b925050604084013567ffffffffffffffff811115610bf357610bf261152c565b5b610bff86828701610b07565b9150509250925092565b600060208284031215610c1f57610c1e611531565b5b6000610c2d84828501610add565b91505092915050565b600060208284031215610c4c57610c4b611531565b5b6000610c5a84828501610af2565b91505092915050565b600060208284031215610c7957610c78611531565b5b600082013567ffffffffffffffff811115610c9757610c9661152c565b5b610ca384828501610b07565b91505092915050565b60008060408385031215610cc357610cc2611531565b5b600083013567ffffffffffffffff811115610ce157610ce061152c565b5b610ced85828601610b07565b925050602083013567ffffffffffffffff811115610d0e57610d0d61152c565b5b610d1a85828601610aaf565b9150509250929050565b60008060408385031215610d3b57610d3a611531565b5b600083013567ffffffffffffffff811115610d5957610d5861152c565b5b610d6585828601610b07565b925050602083013567ffffffffffffffff811115610d8657610d8561152c565b5b610d9285828601610b07565b9150509250929050565b6000610da88383610e61565b905092915050565b610db9816112b3565b82525050565b6000610dca826111f2565b610dd48185611215565b935083602082028501610de6856111cd565b8060005b85811015610e225784840389528151610e038582610d9c565b9450610e0e83611208565b925060208a01995050600181019050610dea565b50829750879550505050505092915050565b610e3d816112c5565b82525050565b610e4c816112d1565b82525050565b610e5b81611318565b82525050565b6000610e6c826111fd565b610e768185611226565b9350610e86818560208601611339565b610e8f81611536565b840191505092915050565b6000610ea5826111fd565b610eaf8185611237565b9350610ebf818560208601611339565b610ec881611536565b840191505092915050565b6000610ede826111fd565b610ee88185611248565b9350610ef8818560208601611339565b80840191505092915050565b60008154610f118161136c565b610f1b8186611226565b94506001821660008114610f365760018114610f4857610f7b565b60ff1983168652602086019350610f7b565b610f51856111dd565b60005b83811015610f7357815481890152600182019150602081019050610f54565b808801955050505b50505092915050565b60006080830160008084018583036000870152610fa18382610f04565b925050600184018583036020870152610fba8382610f04565b92505060028401549050610fcd8161139e565b610fda6040870182611004565b5060038401549050610feb8161139e565b610ff86060870182611004565b50819250505092915050565b61100d8161130e565b82525050565b600061101f8284610ed3565b915081905092915050565b600060208201905061103f6000830184610db0565b92915050565b600060408201905061105a6000830185610e34565b6110676020830184610e34565b9392505050565b60006020820190506110836000830184610e43565b92915050565b600060208201905061109e6000830184610e52565b92915050565b600060208201905081810360008301526110be8184610e9a565b905092915050565b600060408201905081810360008301526110e08185610e9a565b905081810360208301526110f48184610dbf565b90509392505050565b600060208201905081810360008301526111178184610f84565b905092915050565b600061112961113a565b905061113582826113b8565b919050565b6000604051905090565b600067ffffffffffffffff82111561115f5761115e6114ee565b5b602082029050602081019050919050565b600067ffffffffffffffff82111561118b5761118a6114ee565b5b602082029050602081019050919050565b600067ffffffffffffffff8211156111b7576111b66114ee565b5b6111c082611536565b9050602081019050919050565b6000819050602082019050919050565b60008190508160005260206000209050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b600061125e8261130e565b91506112698361130e565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111561129e5761129d611432565b5b828201905092915050565b6000819050919050565b60006112be826112ee565b9050919050565b60008115159050919050565b6000819050919050565b60008190506112e982611554565b919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000611323826112db565b9050919050565b82818337600083830152505050565b60005b8381101561135757808201518184015260208101905061133c565b83811115611366576000848401525b50505050565b6000600282049050600182168061138457607f821691505b6020821081141561139857611397611490565b5b50919050565b60006113b16113ac83611547565b6112a9565b9050919050565b6113c182611536565b810181811067ffffffffffffffff821117156113e0576113df6114ee565b5b80604052505050565b60006113f48261130e565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82141561142757611426611432565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b60008160001c9050919050565b6003811061156557611564611461565b5b50565b611571816112b3565b811461157c57600080fd5b50565b611588816112c5565b811461159357600080fd5b50565b61159f816112d1565b81146115aa57600080fd5b5056fea2646970667358221220b47b4efd4f44b18f8db3e7adb711df88af305dbee6eca111691b242d075aff3064736f6c63430008070033";

    public static final String FUNC_CREATEPROPOSAL = "createProposal";

    public static final String FUNC_EXECUTEMESSAGE = "executeMessage";

    public static final String FUNC_GETIDSIPFS = "getIdsIpfs";

    public static final String FUNC_GETMESSAGE = "getMessage";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_GETRULESIPFS = "getRulesIpfs";

    public static final String FUNC_GETVARIABLE = "getVariable";

    public static final String FUNC_SETRULESANDIDSIPFS = "setRulesAndIdsIpfs";

    public static final String FUNC_SETVARIABLES = "setVariables";

    public static final String FUNC_VOTEPROPOSAL = "voteProposal";

    public static final String FUNC_VOTERS = "voters";

    public static final Event MESSAGEEXECUTE_EVENT = new Event("messageExecute", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Utf8String>>() {}));
    ;

    public static final Event PROPOSALSTATUS_EVENT = new Event("proposalStatus", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Proposal>() {}));
    ;

    @Deprecated
    protected Process(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Process(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Process(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Process(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<MessageExecuteEventResponse> getMessageExecuteEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MESSAGEEXECUTE_EVENT, transactionReceipt);
        ArrayList<MessageExecuteEventResponse> responses = new ArrayList<MessageExecuteEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MessageExecuteEventResponse typedResponse = new MessageExecuteEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.messageId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.inputs = (List<String>) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MessageExecuteEventResponse> messageExecuteEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MessageExecuteEventResponse>() {
            @Override
            public MessageExecuteEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MESSAGEEXECUTE_EVENT, log);
                MessageExecuteEventResponse typedResponse = new MessageExecuteEventResponse();
                typedResponse.log = log;
                typedResponse.messageId = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.inputs = (List<String>) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MessageExecuteEventResponse> messageExecuteEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MESSAGEEXECUTE_EVENT));
        return messageExecuteEventFlowable(filter);
    }

    public List<ProposalStatusEventResponse> getProposalStatusEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSALSTATUS_EVENT, transactionReceipt);
        ArrayList<ProposalStatusEventResponse> responses = new ArrayList<ProposalStatusEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalStatusEventResponse typedResponse = new ProposalStatusEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.p = (Proposal) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalStatusEventResponse> proposalStatusEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalStatusEventResponse>() {
            @Override
            public ProposalStatusEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSALSTATUS_EVENT, log);
                ProposalStatusEventResponse typedResponse = new ProposalStatusEventResponse();
                typedResponse.log = log;
                typedResponse.p = (Proposal) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalStatusEventResponse> proposalStatusEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALSTATUS_EVENT));
        return proposalStatusEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createProposal(String _proposalHashRules, String _proposalHashIds) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEPROPOSAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_proposalHashRules), 
                new org.web3j.abi.datatypes.Utf8String(_proposalHashIds)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> executeMessage(String messageToExecute, List<String> inputs) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_EXECUTEMESSAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(messageToExecute), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(inputs, org.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getIdsIpfs() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETIDSIPFS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getMessage(String messageId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMESSAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(messageId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getRulesIpfs() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRULESIPFS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> getVariable(byte[] name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETVARIABLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> setRulesAndIdsIpfs(String rules_hash, String ids_hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETRULESANDIDSIPFS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(rules_hash), 
                new org.web3j.abi.datatypes.Utf8String(ids_hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setVariables(List<byte[]> names, List<byte[]> values, String messageID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETVARIABLES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.datatypes.generated.Bytes32.class,
                        org.web3j.abi.Utils.typeMap(names, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.datatypes.generated.Bytes32.class,
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new org.web3j.abi.datatypes.Utf8String(messageID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> voteProposal(Boolean _vote) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTEPROPOSAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(_vote)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<Boolean, Boolean>> voters(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple2<Boolean, Boolean>>(function,
                new Callable<Tuple2<Boolean, Boolean>>() {
                    @Override
                    public Tuple2<Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<Boolean, Boolean>(
                                (Boolean) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue());
                    }
                });
    }

    @Deprecated
    public static Process load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Process(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Process load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Process(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Process load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Process(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Process load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Process(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Process> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String sender, BigInteger _quorum) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.generated.Uint256(_quorum)));
        return deployRemoteCall(Process.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Process> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String sender, BigInteger _quorum) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.generated.Uint256(_quorum)));
        return deployRemoteCall(Process.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Process> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String sender, BigInteger _quorum) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.generated.Uint256(_quorum)));
        return deployRemoteCall(Process.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Process> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String sender, BigInteger _quorum) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.generated.Uint256(_quorum)));
        return deployRemoteCall(Process.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class Proposal extends DynamicStruct {
        public String proposedHashRules;

        public String proposedHashIds;

        public BigInteger quorum;

        public BigInteger actualVotes;

        public Proposal(String proposedHashRules, String proposedHashIds, BigInteger quorum, BigInteger actualVotes) {
            super(new org.web3j.abi.datatypes.Utf8String(proposedHashRules),new org.web3j.abi.datatypes.Utf8String(proposedHashIds),new org.web3j.abi.datatypes.generated.Uint256(quorum),new org.web3j.abi.datatypes.generated.Uint256(actualVotes));
            this.proposedHashRules = proposedHashRules;
            this.proposedHashIds = proposedHashIds;
            this.quorum = quorum;
            this.actualVotes = actualVotes;
        }

        public Proposal(Utf8String proposedHashRules, Utf8String proposedHashIds, Uint256 quorum, Uint256 actualVotes) {
            super(proposedHashRules,proposedHashIds,quorum,actualVotes);
            this.proposedHashRules = proposedHashRules.getValue();
            this.proposedHashIds = proposedHashIds.getValue();
            this.quorum = quorum.getValue();
            this.actualVotes = actualVotes.getValue();
        }
    }

    public static class MessageExecuteEventResponse extends BaseEventResponse {
        public String messageId;

        public List<String> inputs;
    }

    public static class ProposalStatusEventResponse extends BaseEventResponse {
        public Proposal p;
    }
}
