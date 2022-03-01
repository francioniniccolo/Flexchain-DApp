package it.unicam.cs.Flexchain;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

@RestController
public class Controller {
    DroolsConfig conf = new DroolsConfig();
    BlockchainUtils utils = new BlockchainUtils();

    @GetMapping(value = "/web3j")
    public String getProcess(){
        try {
            utils.subToMessages("0x35DA54D6d124eC462988492Def458BE53C754Cd3");
            utils.setContract("0x35DA54D6d124eC462988492Def458BE53C754Cd3");
           return utils.getProcess("diagram.bpmn");
        }catch (Exception e){return e.getMessage();}

    }

    @GetMapping(value="/bytes")
    public void conversion(){
       Web3j web3j=Web3j.build(new HttpService("http://localhost:7545"));
       DefaultGasProvider d = new DefaultGasProvider();
       System.out.println(d.getGasLimit());
       Process contract =Process.load("0x305e7f75b99C7238e532959E5A43FDC501779379",web3j, Credentials.create("e53693981918f8e49cacbaf17351335d97ebcb06469c43e6df04adcc6650ae1b"), d);

        String stringa = "stringa";
        int stringLength = stringa.length();
        int fixedLength = 32;
        for (int i = 0; i<fixedLength-stringLength;i++) {
            stringa = stringa.concat("0");
            System.out.println(i);
        }

        byte[] b = stringa.getBytes(StandardCharsets.UTF_8);
        //byte[] b = {61,62,63,64,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00};
        System.out.println(b.length);
        List<byte[]>types = Arrays.asList(b);
        List<byte[]>types2 = Arrays.asList(b);
        try {
           System.out.println(contract.setVariables(types, types2, "ciao").send().getGasUsed());
        }catch (Exception e){System.out.println(e.getMessage());}
    }


    @GetMapping(value = "/fire")
    public void fireRules()  {
        try{
        KieContainer kieContainer = conf.getKieContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(utils);
        List names = new ArrayList();
        List values = new ArrayList();
        kieSession.setGlobal( "names", names );
        kieSession.setGlobal( "values", values );
        kieSession.fireAllRules();}catch (Exception e){System.out.println(e.getMessage());}
    }

    @GetMapping(value = "/generate-rules")
    public String getRules(){
        String rules =
        "rule \"Message_1pam53q\"\n" +
                "when\n" +
                "   b : BlockchainUtils(b.getState(\"Message_1pam53q\")==0)\n" +
                "then\n" +
                "    List<String> types = Arrays.asList(new String[]{\"string\"});\n" +
                "    List<String> variables = Arrays.asList(new String[]{\"receipt_id\"});\n" +
                "   List<String> values = Arrays.asList(new String[]{b.getSingleInput(0)});\n" +
                "b.setVarialesToContract(types, variables, values, \"Message_1pam53q\");\n" +
                " end"+"$"+
               "rule \"Message_1mi4idx\"\n" +
                "when\n" +
                "   b : BlockchainUtils(b.getState(\"Message_1mi4idx\")==0, b.getState(\"Message_1pam53q\")==2)\n" +
                "then\n" +
                "    List<String> types = Arrays.asList(new String[]{\"string\",\"string\"});\n" +
                "    List<String> variables = Arrays.asList(new String[]{\"date\",\"ticketID\"});\n" +
                "   List<String> values = Arrays.asList(new String[]{b.getSingleInput(0),b.getSingleInput(1)});\n" +
                "b.setVarialesToContract(types, variables, values, \"Message_1mi4idx\");\n" +
                " end"+"$"+
               "rule \"Message_1rnq4x3\"\n" +
                "when\n" +
                "   b : BlockchainUtils(b.getState(\"Message_1rnq4x3\")==0, b.getState(\"Message_1mi4idx\")==2)\n" +
                "then\n" +
                "    List<String> types = Arrays.asList(new String[]{\"string\"});\n" +
                "    List<String> variables = Arrays.asList(new String[]{\"mod\"});\n" +
                "   List<String> values = Arrays.asList(new String[]{b.getSingleInput(0)});\n" +
                "b.setVarialesToContract(types, variables, values, \"Message_1rnq4x3\");\n" +
                " end";
        return rules;
    }

    @GetMapping(value = "/add-rules")
    public ArrayList<String> addRules(){
        ArrayList<String> rules = new ArrayList<>();
        rules.add("rule 'Message_2k5dvy9'\n" +
                "when\n" +
                "   b : utils.BlockchainUtils(b.getState('Message_2k5dvy9')==0)\n" +
                "then\n" +
                "    List<String> types = Arrays.asList(new String[]{'string'});\n" +
                "    List<String> variables = Arrays.asList(new String[]{'receipt_id'});\n" +
                "   List<String> values = Arrays.asList(new String[]{b.getSingleInput(0)});\n" +
                "b.setVarialesToContract(types, variables, values, 'Message_2k5dvy9');\n" +
                " end");
        rules.add("rule 'Message_1xt9tbc'\n" +
                "when\n" +
                "   b : utils.BlockchainUtils(b.getState('Message_1xt9tbc')==0, b.getState('Message_2k5dvy9')==2)\n" +
                "then\n" +
                "    List<String> types = Arrays.asList(new String[]{'string','string'});\n" +
                "    List<String> variables = Arrays.asList(new String[]{'date','ticketID'});\n" +
                "   List<String> values = Arrays.asList(new String[]{b.getSingleInput(0),b.getSingleInput(1)});\n" +
                "b.setVarialesToContract(types, variables, values, 'Message_1xt9tbc');\n" +
                " end");
        return rules;
    }

    @GetMapping(value = "/add-rules-id")
    public ArrayList<String> addRulesIds(){
        ArrayList<String> ids = new ArrayList<>();
        ids.add("Message_2k5dvy9");
        ids.add("Message_1xt9tbc");
        return ids;
    }

    @GetMapping(value = "/generate-rules-id")
    public String getIds(){
        String ids ="Message_1pam53q,Message_1mi4idx,Message_1rnq4x3";
        return ids;
    }

    @GetMapping(value = "/delete-rules")
    public ArrayList<String> deleteRules(){
        ArrayList<String> ids = new ArrayList<>();
        ids.add("Message_0xt8tbc");
        ids.add("Message_0lfr0fv");
        return ids;
    }


}
