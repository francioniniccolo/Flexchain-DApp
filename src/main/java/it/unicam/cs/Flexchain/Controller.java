package it.unicam.cs.Flexchain;

import it.unicam.cs.Flexchain.translator.Translator;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    DroolsConfig conf = new DroolsConfig();
    BlockchainUtils utils = new BlockchainUtils();

    @PostMapping(value = "/messageListener/{address}")
    public void getProcess(@PathVariable String address){
        try {
            utils.subToMessages(address);
            utils.setContract(address);
           //return "address: "+ utils.getProcess("diagram.bpmn");
        }catch (Exception e){System.out.println(e.getMessage());}

    }

    @GetMapping(value="/bytes")
    public void conversion(){
        utils.setContract("0x2f08c3DA5BD30a20D85661f7602391Ff5fD02358");
        utils.getVariable();
    }

    @GetMapping(value="/translate")
    public List<String> translate(){
            Translator t = new Translator();
            File f = new File("src/main/resources/pizzaDelivery.bpmn");
            t.readModel(f);
        try {
           // String rule = t.flowNodeSearch();
            List<String>rule=t.flowNodeSearch();
            System.out.println("Regole generate:" + rule);
            return rule;
        }catch (Exception e){System.out.println(e.getMessage()); return null;}
    }

    //todo
    @PostMapping(value="/translate_post/{fileName}")
    public Object createClient(@RequestBody String file, @PathVariable String fileName) throws URISyntaxException, IOException {
        //Client savedClient = clientRepository.save(client);
       //  return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);

        try {
            File diagram_file = new File("src/main/resources/diagrams/"+fileName);
            FileWriter fw = new FileWriter("src/main/resources/diagrams/"+fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(file);
            bw.newLine();
            bw.close();
            Translator t = new Translator();
            t.readModel(diagram_file);
           // String rule = t.flowNodeSearch();
            List<String>rule=t.flowNodeSearch();
            List<String>ids = t.getIdList();
            System.out.println(rule);
            return rule;
        }catch (Exception e){System.out.println(e.getMessage());return "Errore nella generazione delle regole";}
    }

    //todo
    @PostMapping(value="/translate_post_id/{fileName}")
    public List<String> getIds(@RequestBody String file,@PathVariable String fileName) throws URISyntaxException, IOException {
        //Client savedClient = clientRepository.save(client);
        //  return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);

        try {
            File diagram_file = new File("src/main/resources/diagrams/"+fileName);
            Translator t = new Translator();
            t.readModel(diagram_file);
           // String rule = t.flowNodeSearch();
            List<String>rule=t.flowNodeSearch();
            List<String>ids = t.getIdList();
            return ids;
        }catch (Exception e){System.out.println(e.getMessage());return null;}
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
