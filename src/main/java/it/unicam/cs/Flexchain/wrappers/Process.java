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
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620017ca380380620017ca8339818101604052810190620000379190620000b8565b81600a60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600560020181905550505062000176565b6000815190506200009b8162000142565b92915050565b600081519050620000b2816200015c565b92915050565b60008060408385031215620000d257620000d16200013d565b5b6000620000e2858286016200008a565b9250506020620000f585828601620000a1565b9150509250929050565b60006200010c8262000113565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600080fd5b6200014d81620000ff565b81146200015957600080fd5b50565b620001678162000133565b81146200017357600080fd5b50565b61164480620001866000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806394bf28221161007157806394bf282214610150578063a3ec138d1461016e578063b489ab6d1461019f578063e96f8ba5146101bb578063f27a6e22146101d7578063f4d0c2b8146101f5576100a9565b80630cc4e8d8146100ae5780635d145416146100de57806365e481e2146100fa578063857ac45d14610116578063893d20e814610132575b600080fd5b6100c860048036038101906100c39190610cc4565b610225565b6040516100d591906110ea565b60405180910390f35b6100f860048036038101906100f39190610c6a565b61025a565b005b610114600480360381019061010f9190610d85565b6104f2565b005b610130600480360381019061012b9190610d85565b61061a565b005b61013a61064c565b604051610147919061108b565b60405180910390f35b610158610676565b6040516101659190611105565b60405180910390f35b61018860048036038101906101839190610b96565b610708565b6040516101969291906110a6565b60405180910390f35b6101b960048036038101906101b49190610bc3565b610746565b005b6101d560048036038101906101d09190610d0d565b6107fe565b005b6101df61083b565b6040516101ec9190611105565b60405180910390f35b61020f600480360381019061020a9190610c97565b6108cd565b60405161021c91906110cf565b60405180910390f35b60006003826040516102379190611074565b908152602001604051809103902060009054906101000a900460ff169050919050565b60011515600960003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16151514156102bb57600080fd5b6040518060400160405280600115158152602001821515815250600960003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548160ff02191690831515021790555060208201518160000160016101000a81548160ff021916908315150217905550905050600115158115151415610380576005600301600081548092919061037a9061144a565b91905055505b60056002015460056003015414156104ef577f814dc6df136c5f9407476334aab3fde68ad032cc01721d1c460fea18e4abd3e860056040516103c2919061115e565b60405180910390a16104ee600560000180546103dd906113cd565b80601f0160208091040260200160405190810160405280929190818152602001828054610409906113cd565b80156104565780601f1061042b57610100808354040283529160200191610456565b820191906000526020600020905b81548152906001019060200180831161043957829003601f168201915b50505050506005600101805461046b906113cd565b80601f0160208091040260200160405190810160405280929190818152602001828054610497906113cd565b80156104e45780601f106104b9576101008083540402835291602001916104e4565b820191906000526020600020905b8154815290600101906020018083116104c757829003601f168201915b505050505061061a565b5b50565b604051806040016040528060011515815260200160011515815250600960003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548160ff02191690831515021790555060208201518160000160016101000a81548160ff02191690831515021790555090505081600560000190805190602001906105a89291906108ea565b5080600560010190805190602001906105c29291906108ea565b5060016005600301546105d591906112b4565b6005600301819055507f814dc6df136c5f9407476334aab3fde68ad032cc01721d1c460fea18e4abd3e8600560405161060e919061115e565b60405180910390a15050565b81600190805190602001906106309291906108ea565b5080600090805190602001906106479291906108ea565b505050565b6000600a60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b606060018054610685906113cd565b80601f01602080910402602001604051908101604052809291908181526020018280546106b1906113cd565b80156106fe5780601f106106d3576101008083540402835291602001916106fe565b820191906000526020600020905b8154815290600101906020018083116106e157829003601f168201915b5050505050905090565b60096020528060005260406000206000915090508060000160009054906101000a900460ff16908060000160019054906101000a900460ff16905082565b60005b83518110156107b15782818151811061076557610764611520565b5b60200260200101516004600086848151811061078457610783611520565b5b602002602001015181526020019081526020016000208190555080806107a99061144a565b915050610749565b5060026003826040516107c49190611074565b908152602001604051809103902060006101000a81548160ff021916908360028111156107f4576107f36114c2565b5b0217905550505050565b7f3be6db30a3513d60905e653d96c44308a41ff4ad025e6874321674c5b1857af1828260405161082f929190611127565b60405180910390a15050565b60606000805461084a906113cd565b80601f0160208091040260200160405190810160405280929190818152602001828054610876906113cd565b80156108c35780601f10610898576101008083540402835291602001916108c3565b820191906000526020600020905b8154815290600101906020018083116108a657829003601f168201915b5050505050905090565b600060046000838152602001908152602001600020549050919050565b8280546108f6906113cd565b90600052602060002090601f016020900481019282610918576000855561095f565b82601f1061093157805160ff191683800117855561095f565b8280016001018555821561095f579182015b8281111561095e578251825591602001919060010190610943565b5b50905061096c9190610970565b5090565b5b80821115610989576000816000905550600101610971565b5090565b60006109a061099b846111a5565b611180565b905080838252602082019050828560208602820111156109c3576109c2611583565b5b60005b858110156109f357816109d98882610b53565b8452602084019350602083019250506001810190506109c6565b5050509392505050565b6000610a10610a0b846111d1565b611180565b90508083825260208201905082856020860282011115610a3357610a32611583565b5b60005b85811015610a8157813567ffffffffffffffff811115610a5957610a5861157e565b5b808601610a668982610b68565b85526020850194506020840193505050600181019050610a36565b5050509392505050565b6000610a9e610a99846111fd565b611180565b905082815260208101848484011115610aba57610ab9611588565b5b610ac584828561138b565b509392505050565b600081359050610adc816115c9565b92915050565b600082601f830112610af757610af661157e565b5b8135610b0784826020860161098d565b91505092915050565b600082601f830112610b2557610b2461157e565b5b8135610b358482602086016109fd565b91505092915050565b600081359050610b4d816115e0565b92915050565b600081359050610b62816115f7565b92915050565b600082601f830112610b7d57610b7c61157e565b5b8135610b8d848260208601610a8b565b91505092915050565b600060208284031215610bac57610bab611592565b5b6000610bba84828501610acd565b91505092915050565b600080600060608486031215610bdc57610bdb611592565b5b600084013567ffffffffffffffff811115610bfa57610bf961158d565b5b610c0686828701610ae2565b935050602084013567ffffffffffffffff811115610c2757610c2661158d565b5b610c3386828701610ae2565b925050604084013567ffffffffffffffff811115610c5457610c5361158d565b5b610c6086828701610b68565b9150509250925092565b600060208284031215610c8057610c7f611592565b5b6000610c8e84828501610b3e565b91505092915050565b600060208284031215610cad57610cac611592565b5b6000610cbb84828501610b53565b91505092915050565b600060208284031215610cda57610cd9611592565b5b600082013567ffffffffffffffff811115610cf857610cf761158d565b5b610d0484828501610b68565b91505092915050565b60008060408385031215610d2457610d23611592565b5b600083013567ffffffffffffffff811115610d4257610d4161158d565b5b610d4e85828601610b68565b925050602083013567ffffffffffffffff811115610d6f57610d6e61158d565b5b610d7b85828601610b10565b9150509250929050565b60008060408385031215610d9c57610d9b611592565b5b600083013567ffffffffffffffff811115610dba57610db961158d565b5b610dc685828601610b68565b925050602083013567ffffffffffffffff811115610de757610de661158d565b5b610df385828601610b68565b9150509250929050565b6000610e098383610ec2565b905092915050565b610e1a81611314565b82525050565b6000610e2b82611253565b610e358185611276565b935083602082028501610e478561122e565b8060005b85811015610e835784840389528151610e648582610dfd565b9450610e6f83611269565b925060208a01995050600181019050610e4b565b50829750879550505050505092915050565b610e9e81611326565b82525050565b610ead81611332565b82525050565b610ebc81611379565b82525050565b6000610ecd8261125e565b610ed78185611287565b9350610ee781856020860161139a565b610ef081611597565b840191505092915050565b6000610f068261125e565b610f108185611298565b9350610f2081856020860161139a565b610f2981611597565b840191505092915050565b6000610f3f8261125e565b610f4981856112a9565b9350610f5981856020860161139a565b80840191505092915050565b60008154610f72816113cd565b610f7c8186611287565b94506001821660008114610f975760018114610fa957610fdc565b60ff1983168652602086019350610fdc565b610fb28561123e565b60005b83811015610fd457815481890152600182019150602081019050610fb5565b808801955050505b50505092915050565b600060808301600080840185830360008701526110028382610f65565b92505060018401858303602087015261101b8382610f65565b9250506002840154905061102e816113ff565b61103b6040870182611065565b506003840154905061104c816113ff565b6110596060870182611065565b50819250505092915050565b61106e8161136f565b82525050565b60006110808284610f34565b915081905092915050565b60006020820190506110a06000830184610e11565b92915050565b60006040820190506110bb6000830185610e95565b6110c86020830184610e95565b9392505050565b60006020820190506110e46000830184610ea4565b92915050565b60006020820190506110ff6000830184610eb3565b92915050565b6000602082019050818103600083015261111f8184610efb565b905092915050565b600060408201905081810360008301526111418185610efb565b905081810360208301526111558184610e20565b90509392505050565b600060208201905081810360008301526111788184610fe5565b905092915050565b600061118a61119b565b90506111968282611419565b919050565b6000604051905090565b600067ffffffffffffffff8211156111c0576111bf61154f565b5b602082029050602081019050919050565b600067ffffffffffffffff8211156111ec576111eb61154f565b5b602082029050602081019050919050565b600067ffffffffffffffff8211156112185761121761154f565b5b61122182611597565b9050602081019050919050565b6000819050602082019050919050565b60008190508160005260206000209050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b60006112bf8261136f565b91506112ca8361136f565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff038211156112ff576112fe611493565b5b828201905092915050565b6000819050919050565b600061131f8261134f565b9050919050565b60008115159050919050565b6000819050919050565b600081905061134a826115b5565b919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b60006113848261133c565b9050919050565b82818337600083830152505050565b60005b838110156113b857808201518184015260208101905061139d565b838111156113c7576000848401525b50505050565b600060028204905060018216806113e557607f821691505b602082108114156113f9576113f86114f1565b5b50919050565b600061141261140d836115a8565b61130a565b9050919050565b61142282611597565b810181811067ffffffffffffffff821117156114415761144061154f565b5b80604052505050565b60006114558261136f565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82141561148857611487611493565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b60008160001c9050919050565b600381106115c6576115c56114c2565b5b50565b6115d281611314565b81146115dd57600080fd5b50565b6115e981611326565b81146115f457600080fd5b50565b61160081611332565b811461160b57600080fd5b5056fea26469706673582212205e6ae32dd9f3d28268b571f91c9f0b7dcc921cb06abc951f3eccd660f2a477e964736f6c63430008070033";

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
