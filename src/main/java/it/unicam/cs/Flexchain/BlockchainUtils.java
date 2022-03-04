package it.unicam.cs.Flexchain;


import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;
import it.unicam.cs.Flexchain.wrappers.Monitor;
import it.unicam.cs.Flexchain.wrappers.Process;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
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
        contract = Process.load(address, web3j, Credentials.create("eca855fd79ee07fc071735164321f7e5300ddb0569ce131267460eaffed3c06c"), new DefaultGasProvider());
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
        Process contract = Process.load(address, web3j, Credentials.create("eca855fd79ee07fc071735164321f7e5300ddb0569ce131267460eaffed3c06c"), new DefaultGasProvider());
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
                            stringList.add(stringValue);
                            System.out.println("valore in stringa: " + stringValue);
                        }
                        messageInputs=stringList;
                        System.out.println(messageId);
                        String hash_rules = contract.getRulesIpfs().send();
                        String hash_ids = contract.getIdsIpfs().send();
                        System.out.println(hash_rules);
                        //JSONObject rules = getRulesFromIpfs(hash_rules,hash_ids);
                        HashMap rules = getRulesFromIpfs(hash_rules, hash_ids);
                        String rule = "Vuota";
                        // System.out.println(rules.get(messageId));
                        insertToDroolsFile(rules.get(messageId).toString());
                        KieFileSystem kfs = conf.getKieFileSystem();
                        KieServices ks = KieServices.Factory.get();


                        kfs.write(ResourceFactory.newClassPathResource("rules.drl"));

                        // Add KieFileSystem to KieBuilder
                        KieBuilder kb = ks.newKieBuilder(kfs);


                        kb.buildAll();

                        insertToDroolsFile(rules.get(messageId).toString());
                        fireRules();

                    }
                });
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


    private Monitor getMonitor() {
        Monitor monitor = Monitor.load("0xf21413c7fe6a85670202761f07F683891B803577", web3j, Credentials.create("eca855fd79ee07fc071735164321f7e5300ddb0569ce131267460eaffed3c06c"), new DefaultGasProvider());
        return monitor;
    }

    private Web3j getWeb3j() {
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
        for (int i = 0; i < jaRules.length(); i++) {
            map.put(jaIds.get(i).toString(), jaRules.getString(i));
            //jo.append(jaIds.get(i).toString(),jaRules.getString(i));
        }
        return map;

    }

    public BigInteger getState(String variable) throws Exception {
         BigInteger state = contract.getMessage(variable).send();
         return state;
       // BigInteger state = new BigInteger("0");
       // return state;
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

        int stringLength = string.length();
        int fixedLength = 32;
        for (int i = 0; i < fixedLength - stringLength; i++) {
            string = string.concat("0");
        }
        byte[] b = string.getBytes(StandardCharsets.UTF_8);
        return b;
    }

    public String bytes32ToString(byte[] bytes) {
        return StringUtils.newStringUsAscii(bytes);
    }

    public void getVariable(){
        try {
            byte[] variable = contract.getVariable(stringToBytes32("products")).send();
          //  for (int i=0;i<variable.length;i++){System.out.print(variable[i]);}
            String s = new String(variable, StandardCharsets.UTF_8);
            System.out.println(s);
        }catch (Exception e){System.out.println(e.getMessage());}
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
