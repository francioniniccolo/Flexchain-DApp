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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.web3j.abi.datatypes.generated.Bytes32;

@RestController
public class Controller {
    DroolsConfig conf = new DroolsConfig();
    BlockchainUtils utils = new BlockchainUtils();

    @GetMapping(value = "/web3j")
    public String getProcess(){
        try {
            utils.subToMessages("0x560ffc9a3a582be203a33883a5551730ff719ded");
           return utils.getProcess("diagram.bpmn");
        }catch (Exception e){return e.getMessage();}

    }

    @GetMapping(value="/bytes")
    public void conversion(){
     Bytes32 bytes = utils.stringToBytes32("prova");
     System.out.println(bytes.getValue());
     System.out.println(utils.bytes32ToString(bytes));
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
