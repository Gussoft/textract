package com.gussoft.bigsignerclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.List;

public class BSCStatus {
    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");
        try {
            zipfile();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private static void single(){
        String json = "[{\"id\":\"289c\",\"documentName\":\"requestBody459382159029273022asRaw\"}]";
        String json2 = "[{\"id\":\"289c\",\"documentName\":\"requestBody459382159029273022asRaw\"}]";
        json = json.replace("[", "").replace("]","");
        //json = json.replace();
        System.out.println("json = " + json);
        DocumentFileMapper mapper = new Gson().fromJson(json, DocumentFileMapper.class);

        System.out.println("Finish " + mapper.getId());
    }

    private static void zipfile() throws JsonProcessingException {
        String json = "[{\"id\":\"28a5\",\"documentName\":\"4FIRMAS.pdf\"}, {\"id\":\"28a6\",\"documentName\":\"demo.pdf\"}, {\"id\":\"28a7\",\"documentName\":\"DEMO3.pdf\"}, {\"id\":\"28a8\",\"documentName\":\"demoHo.pdf\"}, {\"id\":\"28a9\",\"documentName\":\"demoS.pdf\"}, {\"id\":\"28aa\",\"documentName\":\"3page.pdf\"}]";
        String json2 = "[{\"id\":\"289c\",\"documentName\":\"requestBody459382159029273022asRaw\"}]";
        //json = json.replace("[", "").replace("]","");
        //json = json.replace();
        System.out.println("json = " + json);
        ObjectMapper mapper = new ObjectMapper();
        DocumentFileMapper fileMapper;
        List<DocumentFileMapper> mapperList = mapper.readValue(json, new TypeReference<List<DocumentFileMapper>>() {
        });
/*        for (DocumentFileMapper doc : mapperList){
            fileMapper = new DocumentFileMapper();
            fileMapper.setId(doc.getId());
            fileMapper.setDocumentName(doc.getDocumentName());
            mapperList.add(fileMapper);
        }
*/

        mapperList.forEach(list ->{
            System.out.println("id: " + list.getId() + " - DocumentName: " + list.getDocumentName());
        });
    }
}
