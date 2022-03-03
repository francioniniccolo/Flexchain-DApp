package it.unicam.cs.Flexchain.translator;


import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import java.io.File;
import java.util.*;

public class Translator {
    BpmnModelInstance modelInstance;
    List<String> participants;
    List<String> participantsWithoutDuplicates;
    String orCondition = "";
    List<String> rulesList = new ArrayList<>();
    List<String> idList = new ArrayList<>();

    //todo
    public void readModel(File file){
        modelInstance = Bpmn.readModelFromFile(file);
    }

    public void getParticipants(){
        Collection<Participant> parti = modelInstance.getModelElementsByType(Participant.class);
        for (Participant p : parti) {
            participants.add(p.getName());
        }
        participantsWithoutDuplicates = new ArrayList<>(new HashSet<>(participants));
    }


    public List<String> getIdList() {
        return idList;
    }

    public List<String> flowNodeSearch(){
        String rule = "";
        //get all the sequence flow of the model
        for(SequenceFlow flow : modelInstance.getModelElementsByType(SequenceFlow.class)){
            //get the target of the sequence flow, so the element to be processed
            ModelElementInstance node = modelInstance.getModelElementById(flow.getAttributeValue("targetRef"));
            ModelElementInstance source = modelInstance.getModelElementById(flow.getAttributeValue("sourceRef"));
            if (node instanceof ModelElementInstanceImpl && !(node instanceof EndEvent)
                    && !(node instanceof ParallelGateway) && !(node instanceof ExclusiveGateway)
                    && !(node instanceof EventBasedGateway)){
                ChoreographyTask task = new ChoreographyTask((ModelElementInstanceImpl) node, modelInstance);
                String requestId = getRequestId(task);
                String responseId = getResponseId(task);
                //if the request exists check if it is enable and if the previous is completed

                if(!requestId.isEmpty()) {
                    try{
                        if (!(source instanceof StartEvent) && !(source instanceof ParallelGateway)){
                            orCondition = ", ";
                        }
                        String singleRule = "";
                        //get the previous id

                        getPreviousId(flow);

                        //create the rule for the request
                        singleRule += "rule \"" + requestId + "\"\n" +
                                "when\n" +
                                "  b : BlockchainUtils(b.getState(\"" + requestId + "\")==0" + orCondition;
                        //if there is a gateway condition to check add it
                        if (!checkForCondition(flow).isEmpty())
                            singleRule += ", " + checkForCondition(flow) + ")\n";
                        else
                            singleRule += ")\n";
                        singleRule += "then\n" +
                                "" + createThenPart(getMessageName(requestId), requestId) + "\n " +
                                "end\n";
                        idList.add(requestId);
                        rulesList.add(singleRule);
                        rule += singleRule;
                    } catch(Exception e){
                        System.out.println("fake message detected on the request: " + requestId);
                        System.out.println("exception: " + e);

                        rule+="";
                    }
                }
                //if the response exists, check if it is enabled and if the previous request is completed
                if(!responseId.isEmpty()) {
                    try {
                        String singleRule = "";
                        singleRule += "rule \"" + responseId + "\"\n" +
                                "when\n" +
                                "  b : BlockchainUtils(b.getState(\"" + responseId + "\")==0, b.getState(\"" + requestId + "\")==2)\n" +
                                "then\n" +
                                "" + createThenPart(getMessageName(responseId), responseId) + "\n " +
                                "end\n";
                        idList.add(responseId);
                        rulesList.add(singleRule);
                        rule += singleRule;
                    } catch(Exception e){
                        System.out.println("fake message detected on the response: " + responseId);
                        rule+="";
                    }
                }

            }
        }
        return rulesList;
       // return rule;
    }

    public String createThenPart(String messageName, String messageId){

        //assuming message is in format name(x y, x1 y1, x2 y2)
        String replaced1 = messageName.replace(")", "");
        String[] split1 = replaced1.split("\\(");
        //now the list contains ["x y", "x1 y1", "x2 y2"];
        List<String> split2 = Arrays.asList(split1[1].split(","));

       // String listTypes = "    List<String> types = Arrays.asList(new String[]{";
        //String listNames = "    List<String> variables = Arrays.asList(new String[]{";
        String listNames= "    names.add(";
        //String listInputs = "   List<String> values = Arrays.asList(new String[]{";
        String listInputs = "    values.add(";

        for (String param : split2) {
            //now is ["x", "y"]
            String[] params = param.split(" ");
            List<String> buffer = new ArrayList<>();
            //check for removing white spaces that confuse the parser
            for (String c : params) {
                if (!c.isEmpty()) {
                    buffer.add(c);
                }
            }
          /*  //check if the element is the last for the comma after the argument
            if (split2.indexOf(param) == (split2.size() - 1)) {
                listTypes += "\'" + buffer.get(0) + "\'";
            } else {
                listTypes += "\'" + buffer.get(0) + "\',";
            }*/
            //same structure but for creating the second list of param names
            if (split2.indexOf(param) == (split2.size() - 1)) {
                listNames += "\"" + buffer.get(1) + "\"";
            } else {
                listNames += "\"" + buffer.get(1) + "\",";
            }
            //if the element is the last one end otherwise add the comma
            if (split2.indexOf(param) == (split2.size() - 1)) {
                listInputs += "b.getSingleInput(" + split2.indexOf(param) + ")";
            } else {
                listInputs += "b.getSingleInput(" + split2.indexOf(param) + "),";
            }
        }
      //  listTypes += "});\n";
       // listNames += "});\n";
        listNames += ");\n";
       // listInputs += "});\n";
        listInputs += ");\n";
        String setVariables = "    b.setVariablesToContract(names, values, \"" + messageId + "\");";

        return listNames + listInputs + setVariables;

    }

    public String getRequestId(ChoreographyTask task){

        if (task.getRequest() != null) {
            MessageFlow requestMessageFlowRef = task.getRequest();
            MessageFlow requestMessageFlow = modelInstance.getModelElementById(requestMessageFlowRef.getId());
            Message requestMessage = modelInstance
                    .getModelElementById(requestMessageFlow.getAttributeValue("messageRef"));
            return requestMessage.getAttributeValue("id");
        }
        return "";
    }


    public String getResponseId(ChoreographyTask task){
        if (task.getResponse() != null) {
            MessageFlow responseMessageFlowRef = task.getResponse();
            MessageFlow responseMessageFlow = modelInstance.getModelElementById(responseMessageFlowRef.getId());
            Message responseMessage = modelInstance
                    .getModelElementById(responseMessageFlow.getAttributeValue("messageRef"));
            return responseMessage.getAttributeValue("id");
        }
        return "";
    }

    public String getMessageName(String id){
        Message message = modelInstance.getModelElementById(id);
        if (!message.getAttributeValue("name").isEmpty()) {
            return message.getAttributeValue("name");
        }
        return "";

    }

    public String checkForCondition(SequenceFlow flow){
        //recursive search for the latest previous message
        //if the element is a gw I get the source until a task appears
        //then with the task I get the response or request
        String getter = "";
        ModelElementInstance previous = modelInstance.getModelElementById(flow.getAttributeValue("sourceRef"));
        if(previous instanceof ExclusiveGateway && flow.getName() != null){
            //condition in format uint x > 5 || string x == "a" || bool x == true
            String conditionToParse = flow.getName();
            String[] params = conditionToParse.split(" ");
            List<String> buffer = new ArrayList<>();
            //check for removing white spaces that confuse the parser
            for (String c: params) {
                if (!c.isEmpty()) {
                    buffer.add(c);
                }
            }
            //depending on the param type a different getter is created
            if(conditionToParse.contains("uint")){
                getter += "b.getIntFromContract(\""+buffer.get(1)+"\")" +
                        ""+buffer.get(2) + buffer.get(3);
            }else if(conditionToParse.contains("string")){
                //System.out.println("buffer di stringhe: " + buffer);
                getter += "b.getStringFromContract(\""+buffer.get(1)+"\")" +
                        ""+buffer.get(2) + buffer.get(3);
            } else if(conditionToParse.contains("bool")){
                getter += "b.getBoolFromContract(\""+buffer.get(1)+"\")" +
                        ""+buffer.get(2) + buffer.get(3);
            }
        }
        return getter;
    }

    public void getPreviousId(SequenceFlow flow){
        List<String> previousIDs = new ArrayList<>();
        ModelElementInstance node = modelInstance.getModelElementById(flow.getAttributeValue("sourceRef"));
        //check previous if it is a message get its response or request
        if (node instanceof ModelElementInstanceImpl && !(node instanceof EndEvent)
                && !(node instanceof ParallelGateway) && !(node instanceof ExclusiveGateway)
                && !(node instanceof EventBasedGateway)){
            ChoreographyTask task = new ChoreographyTask((ModelElementInstanceImpl) node, modelInstance);
            //check if the request or response is empty or is a fake message
            if(!getResponseId(task).isEmpty() && idList.contains(getResponseId(task))){
                //System.out.println("previous is a response"+ getResponseId(task));
                orCondition += "b.getState(\"" + getResponseId(task) + "\")==2";
            } else if(!getRequestId(task).isEmpty() && idList.contains(getRequestId(task))){
                //System.out.println("previous is a request"+ getRequestId(task));
                orCondition += "b.getState(\"" + getRequestId(task) + "\")==2";
            }
        }
        //if there is a gateway take the incomings flows
        // for each incoming take the source and call again the method
        else if(node instanceof ExclusiveGateway){
            List<Object> inc = Arrays.asList(((ExclusiveGateway) node).getIncoming().toArray());
            for (Object f: inc) {
                //if the element is the latest remove the OR condition
                //cehck if the gw has more inputs so if the it is a join put the incoming msgs in OR
                getPreviousId((SequenceFlow) f);
                if(inc.indexOf(f) != (inc.size() -1))
                    orCondition += " || ";
            }
        }else if(node instanceof ParallelGateway){
            List<Object> inc = Arrays.asList(((ParallelGateway) node).getIncoming().toArray());
            for (Object f: inc) {
                //if the element is the latest remove the OR condition
                //cehck if the gw has more inputs so if it is a join put the incoming msgs in AND
                getPreviousId((SequenceFlow) f);
                if(inc.indexOf(f) != (inc.size() -1))
                    orCondition += " && ";
            }
        }else if(node instanceof EventBasedGateway){
            List<Object> inc = Arrays.asList(((EventBasedGateway) node).getIncoming().toArray());
            for (Object f: inc) {
                //if the element is the latest remove the OR condition
                //cehck if the gw has more inputs so if it is aN EVENT based put the incoming msgs in OR
                getPreviousId((SequenceFlow) f);
                if(inc.indexOf(f) != (inc.size() -1))
                    orCondition += " || ";
            }
        }
    }

}
