package com.nsia.officems.doc_mng.receivable_document;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._identity.authentication.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doc_mng/receivable")
public class ReceivableDocumentController {

    @Autowired
    private ReceivableDocumentService receivableDocumentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/documents/list")
    public Object getDocumentsList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside GetFileList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return receivableDocumentService.getMyDocumentsList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    // @PostMapping("/executions/list")
    // public Object getExecutionsList(@RequestBody String requestBody) throws Exception {
    //     JSONObject json = new JSONObject(requestBody);

    //     DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
    //     System.out.println("requestBody: " + input);

    //     System.out.println("Filter inside GetFileList " + json.get("filters"));
    //     Map<String, String> filters = new HashMap<>();
    //     if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
    //         System.out.println(json.get("filters"));
    //         filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
    //     } else {
    //         filters = null;
    //     }
    //     try {
    //         return receivableDocumentService.getMyExecutionsList(input, filters);
    //     } catch (Exception e) {
    //         System.out.println("Exception occured" + e.getLocalizedMessage());
    //     }
    //     return null;
    // }

}