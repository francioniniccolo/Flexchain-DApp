package it.unicam.cs.Flexchain;


import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;
import it.unicam.cs.Flexchain.wrappers.Monitor;
import it.unicam.cs.Flexchain.wrappers.Process;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONArray;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.abi.datatypes.generated.Bytes32;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.codec.binary.StringUtils;
import org.web3j.utils.Numeric;


public class BlockchainUtils {

    DroolsConfig conf = new DroolsConfig();
    Monitor monitor;
    Web3j web3j;
    BigInteger lastEventBlockNumber = BigInteger.valueOf(0L);
    Process contract;
    List<String> messageInputs;

    public BlockchainUtils() {
        web3j = getWeb3j();
        monitor = getMonitor();
    }

    //todo
    public void setContract(String address) {
        contract = Process.load(address, web3j, Credentials.create("111a1712072f1393ce00200d6da6b0c79ab02672987b105b2ac11df411fea86e"), new DefaultGasProvider());
    }

    public String getProcess(String processName) throws Exception {
       // if (monitor.isValid()) {
         String address = monitor.getProcess(processName).send();
       //  System.out.println(address);
         return address;
        //}else{return "Errore!";}
    }

    //todo
    public void subToMessages(String address) throws Exception {
        Process contract = Process.load(address, web3j, Credentials.create("111a1712072f1393ce00200d6da6b0c79ab02672987b105b2ac11df411fea86e"), new DefaultGasProvider());
        BigInteger latestBlock = getLatestBlockNumber();
        System.out.println("Listening from block number: " + latestBlock);

        contract.messageExecuteEventFlowable(DefaultBlockParameter.valueOf(latestBlock), DefaultBlockParameterName.LATEST).
                subscribe((eventResponse) -> {
                    System.out.println("Listening from block number: " + latestBlock);
                    int checkLastEvent = lastEventBlockNumber.compareTo(eventResponse.log.getBlockNumber());
                    if (checkLastEvent == -1) {
                        lastEventBlockNumber = eventResponse.log.getBlockNumber();
                        String messageId = eventResponse.messageId;
                        ArrayList inputs = (ArrayList) eventResponse.inputs;
                        List<String> stringList = new ArrayList<>();
                        for (int i = 0; i < inputs.size(); i++) {
                            byte[] byteValue = ((Utf8String) inputs.get(i)).getValue().getBytes(StandardCharsets.UTF_8);
                            String stringValue = new String(byteValue, StandardCharsets.UTF_8);
                            String splitted []  = stringValue.split(",");
                            stringList.addAll(List.of(splitted));
                           // stringList.add(stringValue);
                            System.out.println("valore in stringa: " + stringValue);
                        }
                        messageInputs=stringList;
                        System.out.println(messageId);
                       // String hash_rules = contract.getRulesIpfs().send();
                       // String hash_ids = contract.getIdsIpfs().send();
                        //HashMap rules = getRulesFromIpfs(hash_rules, hash_ids);

                        // System.out.println(rules.get(messageId));

                        //insertToDroolsFile(rules.get(messageId).toString());


                       /* try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("Interruption finished!");*/
                        fireRules();

                    }
                });
    }

    public void createDroolsFile() throws Exception {
        String hash_rules = contract.getRulesIpfs().send();
        String hash_ids = contract.getIdsIpfs().send();
        ArrayList<String> rules = getRulesFromIpfs(hash_rules, hash_ids);
        String header = "import it.unicam.cs.Flexchain.BlockchainUtils;\n" +
                "global java.util.List names;\n" +
                "global java.util.List values;\n" +
                "\n" +
                "dialect  \"mvel\"";
        FileWriter fw = new FileWriter("src/main/resources/rules.drl", false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(header);
        for (int i=0;i<rules.size();i++){
            bw.newLine();
            bw.write(rules.get(i));
        }
        bw.newLine();
        bw.close();
    }

    private void insertToDroolsFile(String rule) throws IOException {
        String header = "import it.unicam.cs.Flexchain.BlockchainUtils;\n" +
                "global java.util.List names;\n" +
                "global java.util.List values;\n" +
                "\n" +
                "dialect  \"mvel\"";
        FileWriter fw = new FileWriter("src/main/resources/rules.drl", false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(header);
        bw.newLine();
        bw.write(rule);
        bw.newLine();
        bw.close();
    }

    private void fireRules(){
        try{
            KieContainer kieContainer = conf.getKieContainer();
            KieSession kieSession = kieContainer.newKieSession();
            kieSession.insert(this);
            List names = new ArrayList();
            List values = new ArrayList();
            kieSession.setGlobal( "names", names );
            kieSession.setGlobal( "values", values );
            kieSession.fireAllRules();}catch (Exception e){System.out.println(e.getMessage());}
    }

    //todo
    private Monitor getMonitor() {
        Monitor monitor = Monitor.load("0xE87c98e293460784c5E00f7F99862B6548E09a2c", web3j, Credentials.create("111a1712072f1393ce00200d6da6b0c79ab02672987b105b2ac11df411fea86e"), new DefaultGasProvider());
        return monitor;
    }

    private Web3j getWeb3j() {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
        return web3j;
    }

    public BigInteger getLatestBlockNumber() throws Exception {
        return web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock().getNumber();
    }

    public ArrayList<String> getRulesFromIpfs(String hash_rules, String hash_ids) throws IOException {
        IPFS ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
        Multihash filePointerToRules = Multihash.fromBase58(hash_rules);
        Multihash filePointerToIds = Multihash.fromBase58(hash_ids);
        byte[] fileContentsRules = ipfs.cat(filePointerToRules);
        byte[] fileContentsIds = ipfs.cat(filePointerToIds);
        String resultRules = new String(fileContentsRules);
        String resultIds = new String(fileContentsIds);
        JSONArray jaRules = new JSONArray(resultRules);
        JSONArray jaIds = new JSONArray(resultIds);
        ArrayList<String>list = new ArrayList<>();
        HashMap map = new HashMap();
        for (int i = 0; i < jaRules.length(); i++) {
            map.put(jaIds.get(i).toString(), jaRules.getString(i));
            list.add(jaRules.getString(i));
            //jo.append(jaIds.get(i).toString(),jaRules.getString(i));
        }
        return list;

    }

    public BigInteger getState(String variable) throws Exception {
         BigInteger state = contract.getMessage(variable).send();
         return state;
       // BigInteger state = new BigInteger("0");
       // return state;
    }

    public String getVariableFromContract(String variableName){
        try {
            byte[] variable = contract.getVariable(stringToBytes32(variableName)).send();
            //  for (int i=0;i<variable.length;i++){System.out.print(variable[i]);}
            String s = new String(variable, StandardCharsets.UTF_8);
            System.out.println(removePadding(s));
            return removePadding(s);
        }catch (Exception e){System.out.println(e.getMessage());}
        return variableName;
    }

    public void setVariablesToContract(List<String> names, List<String> values, String messageId) throws Exception {
        System.out.println("Setting variables...");
        List<byte[]> namesBytes = new ArrayList<>();
        List<byte[]> valuesBytes = new ArrayList<>() ;
        Iterator<String> namesIterator = names.iterator();
        Iterator<String> valuesIterator = values.iterator();
        while (namesIterator.hasNext()){namesBytes.add(stringToBytes32(namesIterator.next()));}
        while (valuesIterator.hasNext()){valuesBytes.add(stringToBytes32(valuesIterator.next()));}

        try {
            for(int i=0;i<namesBytes.size();i++){System.out.println("name"+i+": "+bytes32ToString(namesBytes.get(i)));}
            for(int i=0;i<valuesBytes.size();i++){System.out.println("value"+i+": "+bytes32ToString(valuesBytes.get(i)));}
            contract.setVariables(namesBytes, valuesBytes, messageId).send();
            System.out.println("Variables set");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public String getSingleInput(int index) {
         return messageInputs.get(index);
        //return "capricciosa";
    }

    public void setTypes(List<String> types) {
        System.out.println(types);
    }

    public byte[] stringToBytes32(String string) {
        StringBuilder str = new StringBuilder(string);
        int stringLength = string.length();
        int fixedLength = 32;
        for (int i = 0; i < fixedLength - stringLength; i++) {
            str.insert(0, '0');
           // string = string.concat("0");
        }
        //byte[] b = string.getBytes(StandardCharsets.UTF_8);
        byte[] b = str.toString().getBytes(StandardCharsets.UTF_8);
        return b;
    }

    public String bytes32ToString(byte[] bytes) {
        return StringUtils.newStringUsAscii(bytes);
    }


    public String removePadding(String string){
        for(int i=0;i<string.length();i++){
            if(string.charAt(i)==','){ return string.substring(i-1);}
            if(string.charAt(i)!='0'){ return string.substring(i);}
        }
        return "Errore nella rimozione padding";
    }


    //Converte la stringa esadecimale in byte[]
    public Bytes32 encode(String s) {

        String hexString = addPadding(Hex.encodeHexString(s.getBytes()));
        System.out.println(hexString);
        return new Bytes32(Numeric.hexStringToByteArray(hexString));

    }

    //Inserisce 0x + tanti 0 quanti sono necessari per arrivare a 64bit
    public String addPadding(String s) {
        StringBuilder str = new StringBuilder(s);
        int totPad = 64 - s.length();
        for (int i = 0; i < totPad; i++) {
            str.insert(0, '0');
        }
        str.insert(0, "0x");
        return str.toString();
    }

}
