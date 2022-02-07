package it.unicam.cs.Flexchain;


import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.json.JSONArray;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class BlockchainUtils {

      Monitor monitor;
      Web3j web3j;
     BigInteger lastEventBlockNumber = BigInteger.valueOf(0L);

    public BlockchainUtils(){
        web3j=getWeb3j();
        monitor=getMonitor();
        try {
            this.subToMessages("0xcbb8bc3658643ae7f3F20117c16CCBAA967d3603");
        }catch (Exception e){System.out.println(e.getMessage());}

    }

    public String getProcess(String processName) throws Exception {
       String address="";
        if(monitor.isValid()){
            address = monitor.getProcess(processName).send();
        }
        return address;
    }

    public void subToMessages(String address) throws Exception {
        Process contract = Process.load(address,web3j, Credentials.create("286cc41c3a685e6983aa9155d15d94fb1e71bbc1849b614f215f38a363c05916"), new DefaultGasProvider());
        BigInteger latestBlock = getLatestBlockNumber();
        System.out.println("Listening from block number: " + latestBlock);

        contract.messageExecuteEventFlowable(DefaultBlockParameter.valueOf(latestBlock), DefaultBlockParameterName.LATEST).
                subscribe( (eventResponse) -> {
                    System.out.println("Listening from block number: " + latestBlock);
                    int checkLastEvent = lastEventBlockNumber.compareTo(eventResponse.log.getBlockNumber());
                    if( checkLastEvent == -1) {
                        lastEventBlockNumber = eventResponse.log.getBlockNumber();
                        String messageId = eventResponse.messageId;
                        ArrayList inputs = (ArrayList) eventResponse.inputs;
                        List<String> stringList = new ArrayList<>();
                        for(int i = 0; i < inputs.size(); i++) {
                            byte[] byteValue = ((Utf8String) inputs.get(i)).getValue().getBytes(StandardCharsets.UTF_8);
                            String stringValue = new String(byteValue, StandardCharsets.UTF_8);
                            stringList.add(stringValue);
                            System.out.println("valore in stringa: " + stringValue);
                        }
                        System.out.println(messageId);
                        String hash = contract.getRulesIpfs().send();
                        System.out.println(hash);
                      getRulesFromIpfs(hash);
                      /*  u.insertRules(blockchainUtil.getRule(messageId));
                        System.out.println(lastEventBlockNumber);
                        blockchainUtil.setMessageInputs(stringList);
                        blockchainUtil.getRule(messageId);
                        //System.out.println(blockchainUtil.getSingleInput(1));
                        executeMessage(blockchainUtil);*/
                    }
                });
    }

    private Monitor getMonitor() {
        Monitor monitor = Monitor.load("0xC658CFAfFc27Ad661966F84EFeD0BF3076883b6e", web3j, Credentials.create("286cc41c3a685e6983aa9155d15d94fb1e71bbc1849b614f215f38a363c05916"), new DefaultGasProvider());
        return monitor;
    }

    private Web3j getWeb3j(){
        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
        return web3j;
    }
    public BigInteger getLatestBlockNumber() throws Exception {
        return web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock().getNumber();
    }

    public void getRulesFromIpfs(String hash) throws IOException {
        IPFS ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
        //IPFS ipfs = new IPFS("https://ipfs.infura.io:5001/api/v0");
        Multihash filePointer = Multihash.fromBase58(hash);
        byte[] fileContents = ipfs.cat(filePointer);
        String result = new String(fileContents);
        JSONArray ja = new JSONArray(result);
        System.out.println(ja);
       /* NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper("hello.txt", "G'day world! IPFS rocks!".getBytes());
        MerkleNode addResult = ipfs.add(file).get(0);
        System.out.println("hash:"+addResult.hash.toBase58());
        Multihash fp = Multihash.fromBase58( addResult.hash.toBase58());
        byte[] fc= ipfs.cat(fp);
        System.out.println(new String(fc));*/
    }
}
