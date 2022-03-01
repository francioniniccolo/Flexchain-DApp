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
import org.web3j.abi.datatypes.generated.Bytes32;
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
    public static final String BINARY = "608060405234801561001057600080fd5b50604051620010143803806200101483398181016040528101906100349190610090565b80600660006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505061010b565b60008151905061008a816100f4565b92915050565b6000602082840312156100a6576100a56100ef565b5b60006100b48482850161007b565b91505092915050565b60006100c8826100cf565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600080fd5b6100fd816100bd565b811461010857600080fd5b50565b610ef9806200011b6000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c8063b489ab6d1161005b578063b489ab6d14610115578063e96f8ba514610131578063f27a6e221461014d578063f4d0c2b81461016b57610088565b80630cc4e8d81461008d578063857ac45d146100bd578063893d20e8146100d957806394bf2822146100f7575b600080fd5b6100a760048036038101906100a291906107b8565b61019b565b6040516100b49190610a97565b60405180910390f35b6100d760048036038101906100d29190610879565b6101d0565b005b6100e1610202565b6040516100ee9190610a61565b60405180910390f35b6100ff61022c565b60405161010c9190610ab2565b60405180910390f35b61012f600480360381019061012a91906106e4565b6102be565b005b61014b60048036038101906101469190610801565b610376565b005b6101556103b3565b6040516101629190610ab2565b60405180910390f35b6101856004803603810190610180919061078b565b610445565b6040516101929190610a7c565b60405180910390f35b60006004826040516101ad9190610a4a565b908152602001604051809103902060009054906101000a900460ff169050919050565b81600190805190602001906101e6929190610462565b5080600090805190602001906101fd929190610462565b505050565b6000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60606001805461023b90610cd7565b80601f016020809104026020016040519081016040528092919081815260200182805461026790610cd7565b80156102b45780601f10610289576101008083540402835291602001916102b4565b820191906000526020600020905b81548152906001019060200180831161029757829003601f168201915b5050505050905090565b60005b8351811015610329578281815181106102dd576102dc610e10565b5b6020026020010151600560008684815181106102fc576102fb610e10565b5b6020026020010151815260200190815260200160002081905550808061032190610d3a565b9150506102c1565b50600260048260405161033c9190610a4a565b908152602001604051809103902060006101000a81548160ff0219169083600281111561036c5761036b610db2565b5b0217905550505050565b7f3be6db30a3513d60905e653d96c44308a41ff4ad025e6874321674c5b1857af182826040516103a7929190610ad4565b60405180910390a15050565b6060600080546103c290610cd7565b80601f01602080910402602001604051908101604052809291908181526020018280546103ee90610cd7565b801561043b5780601f106104105761010080835404028352916020019161043b565b820191906000526020600020905b81548152906001019060200180831161041e57829003601f168201915b5050505050905090565b600060056000838152602001908152602001600020549050919050565b82805461046e90610cd7565b90600052602060002090601f01602090048101928261049057600085556104d7565b82601f106104a957805160ff19168380011785556104d7565b828001600101855582156104d7579182015b828111156104d65782518255916020019190600101906104bb565b5b5090506104e491906104e8565b5090565b5b808211156105015760008160009055506001016104e9565b5090565b600061051861051384610b30565b610b0b565b9050808382526020820190508285602086028201111561053b5761053a610e73565b5b60005b8581101561056b578161055188826106a1565b84526020840193506020830192505060018101905061053e565b5050509392505050565b600061058861058384610b5c565b610b0b565b905080838252602082019050828560208602820111156105ab576105aa610e73565b5b60005b858110156105f957813567ffffffffffffffff8111156105d1576105d0610e6e565b5b8086016105de89826106b6565b855260208501945060208401935050506001810190506105ae565b5050509392505050565b600061061661061184610b88565b610b0b565b90508281526020810184848401111561063257610631610e78565b5b61063d848285610c95565b509392505050565b600082601f83011261065a57610659610e6e565b5b813561066a848260208601610505565b91505092915050565b600082601f83011261068857610687610e6e565b5b8135610698848260208601610575565b91505092915050565b6000813590506106b081610eac565b92915050565b600082601f8301126106cb576106ca610e6e565b5b81356106db848260208601610603565b91505092915050565b6000806000606084860312156106fd576106fc610e82565b5b600084013567ffffffffffffffff81111561071b5761071a610e7d565b5b61072786828701610645565b935050602084013567ffffffffffffffff81111561074857610747610e7d565b5b61075486828701610645565b925050604084013567ffffffffffffffff81111561077557610774610e7d565b5b610781868287016106b6565b9150509250925092565b6000602082840312156107a1576107a0610e82565b5b60006107af848285016106a1565b91505092915050565b6000602082840312156107ce576107cd610e82565b5b600082013567ffffffffffffffff8111156107ec576107eb610e7d565b5b6107f8848285016106b6565b91505092915050565b6000806040838503121561081857610817610e82565b5b600083013567ffffffffffffffff81111561083657610835610e7d565b5b610842858286016106b6565b925050602083013567ffffffffffffffff81111561086357610862610e7d565b5b61086f85828601610673565b9150509250929050565b600080604083850312156108905761088f610e82565b5b600083013567ffffffffffffffff8111156108ae576108ad610e7d565b5b6108ba858286016106b6565b925050602083013567ffffffffffffffff8111156108db576108da610e7d565b5b6108e7858286016106b6565b9150509250929050565b60006108fd83836109a7565b905092915050565b61090e81610c2a565b82525050565b600061091f82610bc9565b6109298185610bec565b93508360208202850161093b85610bb9565b8060005b85811015610977578484038952815161095885826108f1565b945061096383610bdf565b925060208a0199505060018101905061093f565b50829750879550505050505092915050565b61099281610c3c565b82525050565b6109a181610c83565b82525050565b60006109b282610bd4565b6109bc8185610bfd565b93506109cc818560208601610ca4565b6109d581610e87565b840191505092915050565b60006109eb82610bd4565b6109f58185610c0e565b9350610a05818560208601610ca4565b610a0e81610e87565b840191505092915050565b6000610a2482610bd4565b610a2e8185610c1f565b9350610a3e818560208601610ca4565b80840191505092915050565b6000610a568284610a19565b915081905092915050565b6000602082019050610a766000830184610905565b92915050565b6000602082019050610a916000830184610989565b92915050565b6000602082019050610aac6000830184610998565b92915050565b60006020820190508181036000830152610acc81846109e0565b905092915050565b60006040820190508181036000830152610aee81856109e0565b90508181036020830152610b028184610914565b90509392505050565b6000610b15610b26565b9050610b218282610d09565b919050565b6000604051905090565b600067ffffffffffffffff821115610b4b57610b4a610e3f565b5b602082029050602081019050919050565b600067ffffffffffffffff821115610b7757610b76610e3f565b5b602082029050602081019050919050565b600067ffffffffffffffff821115610ba357610ba2610e3f565b5b610bac82610e87565b9050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b6000610c3582610c59565b9050919050565b6000819050919050565b6000819050610c5482610e98565b919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000610c8e82610c46565b9050919050565b82818337600083830152505050565b60005b83811015610cc2578082015181840152602081019050610ca7565b83811115610cd1576000848401525b50505050565b60006002820490506001821680610cef57607f821691505b60208210811415610d0357610d02610de1565b5b50919050565b610d1282610e87565b810181811067ffffffffffffffff82111715610d3157610d30610e3f565b5b80604052505050565b6000610d4582610c79565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff821415610d7857610d77610d83565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b60038110610ea957610ea8610db2565b5b50565b610eb581610c3c565b8114610ec057600080fd5b5056fea26469706673582212201682fa1cf8fe56334eb9932bcd39397c0bc2e012ec90a13fc7c2c208732fb8b464736f6c63430008070033";

    public static final String FUNC_EXECUTEMESSAGE = "executeMessage";

    public static final String FUNC_GETIDSIPFS = "getIdsIpfs";

    public static final String FUNC_GETMESSAGE = "getMessage";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_GETRULESIPFS = "getRulesIpfs";

    public static final String FUNC_GETVARIABLE = "getVariable";

    public static final String FUNC_SETRULESANDIDSIPFS = "setRulesAndIdsIpfs";

    public static final String FUNC_SETVARIABLES = "setVariables";

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
