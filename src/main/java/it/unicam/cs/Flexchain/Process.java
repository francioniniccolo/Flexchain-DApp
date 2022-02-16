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
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161099c38038061099c8339818101604052810190610032919061008e565b80600660006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050610109565b600081519050610088816100f2565b92915050565b6000602082840312156100a4576100a36100ed565b5b60006100b284828501610079565b91505092915050565b60006100c6826100cd565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600080fd5b6100fb816100bb565b811461010657600080fd5b50565b610884806101186000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c80630cc4e8d8146100675780634bfa80e414610097578063893d20e8146100b357806394bf2822146100d1578063b489ab6d146100ef578063f27a6e221461010b575b600080fd5b610081600480360381019061007c919061046f565b610129565b60405161008e9190610563565b60405180910390f35b6100b160048036038101906100ac919061042f565b61015e565b005b6100bb610170565b6040516100c8919061052d565b60405180910390f35b6100d961019a565b6040516100e69190610548565b60405180910390f35b61010960048036038101906101049190610388565b6101a4565b005b61011361025c565b6040516101209190610548565b60405180910390f35b600060048260405161013b9190610516565b908152602001604051809103902060009054906101000a900460ff169050919050565b81600181905550806000819055505050565b6000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600154905090565b60005b835181101561020f578281815181106101c3576101c261079b565b5b6020026020010151600560008684815181106101e2576101e161079b565b5b60200260200101518152602001908152602001600020819055508080610207906106f4565b9150506101a7565b5060026004826040516102229190610516565b908152602001604051809103902060006101000a81548160ff021916908360028111156102525761025161076c565b5b0217905550505050565b60008054905090565b6000610278610273846105a3565b61057e565b9050808382526020820190508285602086028201111561029b5761029a6107fe565b5b60005b858110156102cb57816102b18882610345565b84526020840193506020830192505060018101905061029e565b5050509392505050565b60006102e86102e3846105cf565b61057e565b90508281526020810184848401111561030457610303610803565b5b61030f848285610681565b509392505050565b600082601f83011261032c5761032b6107f9565b5b813561033c848260208601610265565b91505092915050565b60008135905061035481610837565b92915050565b600082601f83011261036f5761036e6107f9565b5b813561037f8482602086016102d5565b91505092915050565b6000806000606084860312156103a1576103a061080d565b5b600084013567ffffffffffffffff8111156103bf576103be610808565b5b6103cb86828701610317565b935050602084013567ffffffffffffffff8111156103ec576103eb610808565b5b6103f886828701610317565b925050604084013567ffffffffffffffff81111561041957610418610808565b5b6104258682870161035a565b9150509250925092565b600080604083850312156104465761044561080d565b5b600061045485828601610345565b925050602061046585828601610345565b9150509250929050565b6000602082840312156104855761048461080d565b5b600082013567ffffffffffffffff8111156104a3576104a2610808565b5b6104af8482850161035a565b91505092915050565b6104c181610616565b82525050565b6104d081610628565b82525050565b6104df8161066f565b82525050565b60006104f082610600565b6104fa818561060b565b935061050a818560208601610690565b80840191505092915050565b600061052282846104e5565b915081905092915050565b600060208201905061054260008301846104b8565b92915050565b600060208201905061055d60008301846104c7565b92915050565b600060208201905061057860008301846104d6565b92915050565b6000610588610599565b905061059482826106c3565b919050565b6000604051905090565b600067ffffffffffffffff8211156105be576105bd6107ca565b5b602082029050602081019050919050565b600067ffffffffffffffff8211156105ea576105e96107ca565b5b6105f382610812565b9050602081019050919050565b600081519050919050565b600081905092915050565b600061062182610645565b9050919050565b6000819050919050565b600081905061064082610823565b919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600061067a82610632565b9050919050565b82818337600083830152505050565b60005b838110156106ae578082015181840152602081019050610693565b838111156106bd576000848401525b50505050565b6106cc82610812565b810181811067ffffffffffffffff821117156106eb576106ea6107ca565b5b80604052505050565b60006106ff82610665565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8214156107325761073161073d565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b600381106108345761083361076c565b5b50565b61084081610628565b811461084b57600080fd5b5056fea26469706673582212205789fa62e1a848b3214f87413ab4edfc1f89ba4ee4b34f2d0d9154acaf8afd0b64736f6c63430008070033";

    public static final String FUNC_GETIDSIPFS = "getIdsIpfs";

    public static final String FUNC_GETMESSAGE = "getMessage";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_GETRULESIPFS = "getRulesIpfs";

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

    public RemoteFunctionCall<byte[]> getIdsIpfs() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETIDSIPFS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
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

    public RemoteFunctionCall<byte[]> getRulesIpfs() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRULESIPFS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> setRulesAndIdsIpfs(byte[] rules_hash, byte[] ids_hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETRULESANDIDSIPFS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(rules_hash), 
                new org.web3j.abi.datatypes.generated.Bytes32(ids_hash)), 
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
