package it.unicam.cs.Flexchain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class prova {
    //private String pathToDrl = "src"+File.separator+"main"+File.separator+"resources"+File.separator+
    //		"org"+File.separator+"example"+File.separator+"rules"+File.separator+"Sample.drl";

    static File ruleFile=null;

    private String getRulesFile() {
        if(ruleFile==null) {
            try {
                ruleFile= File.createTempFile("current", "Rules");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("PATH:"+ruleFile.getAbsolutePath());
        return ruleFile.getAbsolutePath();
    }

    public void insertRule(String rule) throws Exception {
        File file = new File(getRulesFile());
        String data = " ";
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            data += myReader.nextLine() + "\n";
        }
        rule.replace("\t\t\t\t\t", "\n");
        data += rule + "\n";
        //data += "end";

        myReader.close();

        BufferedWriter writ = new BufferedWriter(new FileWriter(getRulesFile()));
        writ.write(data);
        writ.close();

    }

    public void insertRules(String rule) throws Exception {
        FileWriter wChor = new FileWriter(getRulesFile());
        BufferedWriter bChor = new BufferedWriter(wChor);



        String data = " ";
		/* for (String rule: rules) {
            data += rule;
        }*/
        String initial = "import java.util.List\n" +
                "import java.util.Arrays\n" +
                "import org.example.BlockchainUtils\n\n";
        bChor.write(initial + rule);
        bChor.flush();
        bChor.close();

    }

    public void modifyRule(String rule, String idToChange) throws Exception {
        File file = new File(getRulesFile());
        String data = " ";
        Scanner myReader = new Scanner(file);
        int delete = 0;
        while (myReader.hasNextLine()) {
            String buffer = myReader.nextLine();
            if(delete == 1 && !(buffer.contains("end"))) {
                data += " ";
            } else if(delete == 1 && buffer.contains("end")) {
                delete = 0;
                data += " ";
            }else if(delete == 0 && (buffer.contains(idToChange))) {
                delete = 1;
                data += " ";
            } else {
                //System.out.println(myReader.nextLine());
                data += buffer + "\n";
            }

        }
        myReader.close();
        //System.out.println(data);
        data += rule + "\n";
        data += "end";
        BufferedWriter writ = new BufferedWriter(new FileWriter(getRulesFile()));
        writ.write(data);
        writ.close();
    }


    public void removeRule(String ruleID) throws Exception {
        File file = new File(getRulesFile());
        String data = " ";
        Scanner myReader = new Scanner(file);
        int delete = 0;
        while (myReader.hasNextLine()) {
            String buffer = myReader.nextLine();
            if(delete == 1 && !(buffer.contains("end"))) {
                data += " ";
            } else if(delete == 1 && buffer.contains("end")) {
                delete = 0;
                data += " ";
            }else if(delete == 0 && (buffer.contains(ruleID))) {
                delete = 1;
                data += " ";
            } else {
                data += buffer + "\n";
            }

        }
        myReader.close();

        BufferedWriter writ = new BufferedWriter(new FileWriter(getRulesFile()));
        writ.write(data);
        writ.close();
    }




}
