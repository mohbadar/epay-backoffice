package com.nsia.officems.helpdesk.ticket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.helpdesk.ticket.dto.TicketDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/helpdesk/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/list")
    public Object getVisitList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getVisitList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return ticketService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(value = "/{ticketId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "ticketId", required = true) long id) {
        System.out.println("ticket " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Ticket ticket = ticketService.findById(id);

        data.put("ticket", ticket);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/{ticketboardId}/tickets")
    public ResponseEntity<Map<String, Object>> findTicketsByTicketboard(@PathVariable(name = "ticketboardId", required = true) long id) {
        System.out.println("ticket " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Ticket ticket = ticketService.findById(id);

        data.put("ticket", ticket);
        return ResponseEntity.ok(data);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Ticket> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody(required = true) Ticket ticket) {
        System.out.println("ID: " + id + " ticket: " + ticket);
        // TODO
        return null;
    }

    @PostMapping()
    public Ticket create(@RequestBody TicketDto ticketDto) throws JSONException, IOException {
        return ticketService.create(ticketDto);
    }
}