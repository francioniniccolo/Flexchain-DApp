package it.unicam.cs.Flexchain;


import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class BlockchainUtils {

      Monitor monitor;
      Web3j web3j;
     BigInteger lastEventBlockNumber = BigInteger.valueOf(0L);
      Process contract;
    public BlockchainUtils(){
        web3j=getWeb3j();
        monitor=getMonitor();
    }

    public void setContract(String address){
         contract = Process.load(address,web3j, Credentials.create("286cc41c3a685e6983aa9155d15d94fb1e71bbc1849b614f215f38a363c05916"), new DefaultGasProvider());
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
                        String hash_rules = contract.getRulesIpfs().send();
                        String hash_ids = contract.getIdsIpfs().send();
                        System.out.println(hash_rules);
                       //JSONObject rules = getRulesFromIpfs(hash_rules,hash_ids);
                       HashMap rules = getRulesFromIpfs(hash_rules,hash_ids);
                       String rule="Vuota";
                      // System.out.println(rules.get(messageId));



                       insertToDroolsFile( rules.get(messageId).toString());
                      /*  u.insertRules(blockchainUtil.getRule(messageId));
                        System.out.println(lastEventBlockNumber);
                        blockchainUtil.setMessageInputs(stringList);
                        blockchainUtil.getRule(messageId);
                        //System.out.println(blockchainUtil.getSingleInput(1));
                        executeMessage(blockchainUtil);*/
                    }
                });
    }

    private void insertToDroolsFile(String rule) throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/rules.drl", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(rule);
        bw.newLine();
        bw.close();
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

    public HashMap getRulesFromIpfs(String hash_rules, String hash_ids) throws IOException {
        IPFS ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
        Multihash filePointerToRules = Multihash.fromBase58(hash_rules);
        Multihash filePointerToIds = Multihash.fromBase58(hash_ids);
        byte[] fileContentsRules = ipfs.cat(filePointerToRules);
        byte[] fileContentsIds = ipfs.cat(filePointerToIds);
        String resultRules = new String(fileContentsRules);
        String resultIds = new String(fileContentsIds);
         JSONArray jaRules = new JSONArray(resultRules);
         JSONArray jaIds = new JSONArray(resultIds);
         System.out.println(jaRules.getString(0));
         JSONObject jo = new JSONObject();
        HashMap map = new HashMap();
         for (int i=0;i<jaRules.length();i++){
             map.put(jaIds.get(i).toString(),jaRules.getString(i));
             //jo.append(jaIds.get(i).toString(),jaRules.getString(i));
         }
         return map;

    }

    public void insertRules(String rule) throws Exception {
       /* FileWriter wChor = new FileWriter(getRulesFile());
        BufferedWriter bChor = new BufferedWriter(wChor);



        String data = " ";
		*//* for (String rule: rules) {
            data += rule;
        }*//*
        String initial = "import java.util.List\n" +
                "import java.util.Arrays\n" +
                "import org.example.BlockchainUtils\n\n";
        bChor.write(initial + rule);
        bChor.flush();
        bChor.close();*/

    }

    public BigInteger getState(String variable) throws Exception {
       // BigInteger state = contract.getMessage(variable).send();
       // return state;
        BigInteger state = new BigInteger("0");
        return state;
    }
    public void setVarialesToContract(List<String> types, List<String> variables, List<String> values, String messageId) throws Exception {
        //contract.setVariables(stringVar, stringVal, uintVar, uintVal, boolVar, boolVal).send();
       // contract.setVariables(types, variables, values, messageId).send();
        System.out.println("Variables:"+types+variables+values+messageId);
    }
    public String getSingleInput(int index) {
       // return messageInputs.get(index);
        return "capricciosa";
    }
}
