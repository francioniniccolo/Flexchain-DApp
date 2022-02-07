package it.unicam.cs.Flexchain;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200108838038062001088833981810160405281019062000037919062000096565b80600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506200011b565b600081519050620000908162000101565b92915050565b600060208284031215620000af57620000ae620000fc565b5b6000620000bf848285016200007f565b91505092915050565b6000620000d582620000dc565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600080fd5b6200010c81620000c8565b81146200011857600080fd5b50565b610f5d806200012b6000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c8063893d20e81161005b578063893d20e81461012557806394bf282214610143578063e96f8ba514610161578063f27a6e221461017d57610088565b80630cc4e8d81461008d578063122c3c78146100bd5780631bd95155146100d9578063857ac45d14610109575b600080fd5b6100a760048036038101906100a2919061072b565b61019b565b6040516100b491906109ef565b60405180910390f35b6100d760048036038101906100d291906106b3565b6101d0565b005b6100f360048036038101906100ee919061072b565b6101d4565b6040516101009190610a63565b60405180910390f35b610123600480360381019061011e91906107ec565b610274565b005b61012d6102a6565b60405161013a91906109d4565b60405180910390f35b61014b6102d0565b6040516101589190610a0a565b60405180910390f35b61017b60048036038101906101769190610774565b610362565b005b61018561039f565b6040516101929190610a0a565b60405180910390f35b60006003826040516101ad91906109bd565b908152602001604051809103902060009054906101000a900460ff169050919050565b5050565b6000808290506000809250600090505b815181101561026d57600082828151811061020257610201610e74565b5b602001015160f81c60f81b60f81c905060308160ff161015801561022a575060398160ff1611155b156102595760308161023c9190610c4d565b60ff16600a8561024c9190610bf3565b6102569190610b9d565b93505b50808061026590610d9e565b9150506101e4565b5050919050565b816001908051906020019061028a929190610431565b5080600090805190602001906102a1929190610431565b505050565b6000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6060600180546102df90610d3b565b80601f016020809104026020016040519081016040528092919081815260200182805461030b90610d3b565b80156103585780601f1061032d57610100808354040283529160200191610358565b820191906000526020600020905b81548152906001019060200180831161033b57829003601f168201915b5050505050905090565b7f3be6db30a3513d60905e653d96c44308a41ff4ad025e6874321674c5b1857af18282604051610393929190610a2c565b60405180910390a15050565b6060600080546103ae90610d3b565b80601f01602080910402602001604051908101604052809291908181526020018280546103da90610d3b565b80156104275780601f106103fc57610100808354040283529160200191610427565b820191906000526020600020905b81548152906001019060200180831161040a57829003601f168201915b5050505050905090565b82805461043d90610d3b565b90600052602060002090601f01602090048101928261045f57600085556104a6565b82601f1061047857805160ff19168380011785556104a6565b828001600101855582156104a6579182015b828111156104a557825182559160200191906001019061048a565b5b5090506104b391906104b7565b5090565b5b808211156104d05760008160009055506001016104b8565b5090565b60006104e76104e284610aa3565b610a7e565b9050808382526020820190508285602086028201111561050a57610509610ed7565b5b60005b8581101561053a57816105208882610670565b84526020840193506020830192505060018101905061050d565b5050509392505050565b600061055761055284610acf565b610a7e565b9050808382526020820190508285602086028201111561057a57610579610ed7565b5b60005b858110156105c857813567ffffffffffffffff8111156105a05761059f610ed2565b5b8086016105ad8982610685565b8552602085019450602084019350505060018101905061057d565b5050509392505050565b60006105e56105e084610afb565b610a7e565b90508281526020810184848401111561060157610600610edc565b5b61060c848285610cf9565b509392505050565b600082601f83011261062957610628610ed2565b5b81356106398482602086016104d4565b91505092915050565b600082601f83011261065757610656610ed2565b5b8135610667848260208601610544565b91505092915050565b60008135905061067f81610f10565b92915050565b600082601f83011261069a57610699610ed2565b5b81356106aa8482602086016105d2565b91505092915050565b600080604083850312156106ca576106c9610ee6565b5b600083013567ffffffffffffffff8111156106e8576106e7610ee1565b5b6106f485828601610642565b925050602083013567ffffffffffffffff81111561071557610714610ee1565b5b61072185828601610614565b9150509250929050565b60006020828403121561074157610740610ee6565b5b600082013567ffffffffffffffff81111561075f5761075e610ee1565b5b61076b84828501610685565b91505092915050565b6000806040838503121561078b5761078a610ee6565b5b600083013567ffffffffffffffff8111156107a9576107a8610ee1565b5b6107b585828601610685565b925050602083013567ffffffffffffffff8111156107d6576107d5610ee1565b5b6107e285828601610642565b9150509250929050565b6000806040838503121561080357610802610ee6565b5b600083013567ffffffffffffffff81111561082157610820610ee1565b5b61082d85828601610685565b925050602083013567ffffffffffffffff81111561084e5761084d610ee1565b5b61085a85828601610685565b9150509250929050565b6000610870838361090b565b905092915050565b61088181610c81565b82525050565b600061089282610b3c565b61089c8185610b5f565b9350836020820285016108ae85610b2c565b8060005b858110156108ea57848403895281516108cb8582610864565b94506108d683610b52565b925060208a019950506001810190506108b2565b50829750879550505050505092915050565b61090581610ce7565b82525050565b600061091682610b47565b6109208185610b70565b9350610930818560208601610d08565b61093981610eeb565b840191505092915050565b600061094f82610b47565b6109598185610b81565b9350610969818560208601610d08565b61097281610eeb565b840191505092915050565b600061098882610b47565b6109928185610b92565b93506109a2818560208601610d08565b80840191505092915050565b6109b781610cd0565b82525050565b60006109c9828461097d565b915081905092915050565b60006020820190506109e96000830184610878565b92915050565b6000602082019050610a0460008301846108fc565b92915050565b60006020820190508181036000830152610a248184610944565b905092915050565b60006040820190508181036000830152610a468185610944565b90508181036020830152610a5a8184610887565b90509392505050565b6000602082019050610a7860008301846109ae565b92915050565b6000610a88610a99565b9050610a948282610d6d565b919050565b6000604051905090565b600067ffffffffffffffff821115610abe57610abd610ea3565b5b602082029050602081019050919050565b600067ffffffffffffffff821115610aea57610ae9610ea3565b5b602082029050602081019050919050565b600067ffffffffffffffff821115610b1657610b15610ea3565b5b610b1f82610eeb565b9050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b6000610ba882610cd0565b9150610bb383610cd0565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115610be857610be7610de7565b5b828201905092915050565b6000610bfe82610cd0565b9150610c0983610cd0565b9250817fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0483118215151615610c4257610c41610de7565b5b828202905092915050565b6000610c5882610cda565b9150610c6383610cda565b925082821015610c7657610c75610de7565b5b828203905092915050565b6000610c8c82610cb0565b9050919050565b6000819050919050565b6000819050610cab82610efc565b919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600060ff82169050919050565b6000610cf282610c9d565b9050919050565b82818337600083830152505050565b60005b83811015610d26578082015181840152602081019050610d0b565b83811115610d35576000848401525b50505050565b60006002820490506001821680610d5357607f821691505b60208210811415610d6757610d66610e45565b5b50919050565b610d7682610eeb565b810181811067ffffffffffffffff82111715610d9557610d94610ea3565b5b80604052505050565b6000610da982610cd0565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415610ddc57610ddb610de7565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b60038110610f0d57610f0c610e16565b5b50565b610f1981610c93565b8114610f2457600080fd5b5056fea2646970667358221220c68dd88800970cd14e0b31eb7b6884d6c8af9476451b7c9a7646245405e58f2964736f6c63430008070033\t";

    public static final String FUNC_EXECUTEMESSAGE = "executeMessage";

    public static final String FUNC_GETIDSIPFS = "getIdsIpfs";

    public static final String FUNC_GETMESSAGE = "getMessage";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_GETRULESIPFS = "getRulesIpfs";

    public static final String FUNC_SETRULESANDIDSIPFS = "setRulesAndIdsIpfs";

    public static final String FUNC_SETVARIABLES = "setVariables";

    public static final String FUNC_STRINGTOUINT = "stringToUint";

    public static final Event MESSAGEEXECUTE_EVENT = new Event("messageExecute", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Utf8String>>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> setRulesAndIdsIpfs(String rules_hash, String ids_hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETRULESANDIDSIPFS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(rules_hash), 
                new org.web3j.abi.datatypes.Utf8String(ids_hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setVariables(List<String> names, List<byte[]> values) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETVARIABLES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(names, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.datatypes.generated.Bytes32.class,
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> stringToUint(String s) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STRINGTOUINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(s)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public static RemoteCall<Process> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender)));
        return deployRemoteCall(Process.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Process> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender)));
        return deployRemoteCall(Process.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Process> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender)));
        return deployRemoteCall(Process.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Process> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender)));
        return deployRemoteCall(Process.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class MessageExecuteEventResponse extends BaseEventResponse {
        public String messageId;

        public List<String> inputs;
    }
}
