package com.nsia.officems.transportation.request;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.transportation.request.dto.RequestDto;
import com.nsia.officems.transportation.request.dto.RequestPrintDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.nsia.officems._util.FileConverterUtil;
import com.nsia.officems._util.HTMLToPDFCreator;
import com.nsia.officems._util.UriCreator;

import org.springframework.boot.configurationprocessor.json.JSONException;

@RestController
@RequestMapping("/api/transport/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HTMLToPDFCreator htmlToPDFCreator;

    @Autowired
    private FileConverterUtil fileConverterUtil;

    private final UriCreator _uriCreator;

    public RequestController(UriCreator _uriCreator) {
        this._uriCreator = _uriCreator;
    }


    @PreAuthorize("hasAuthority('TRANSPORTATION_MODULE')")
    @PostMapping("/my_list")
    public Object getMyRequestList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getRequestList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return requestService.getMyList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('TRANS_REQUEST_LIST')")
    @PostMapping("/list")
    public Object getRequestList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getRequestList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return requestService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('TRANS_REQUEST_LIST')")
    @PostMapping("/list/pending")
    public Object getPendingRequestList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getRequestList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return requestService.getPendingList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('TRANS_REQUEST_LIST')")
    @PostMapping("/list/closed")
    public Object getClosedRequestList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getRequestList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return requestService.getClosedList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('TRANS_REQUEST_VIEW')")
    @GetMapping(value = "/{requestId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "requestId", required = true) long id) {
        System.out.println("request " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Request request = requestService.findById(id);
        data.put("request", request);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('TRANS_REQUEST_CANCEL')")
    @PutMapping(value = "/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Request> cancelRequest(@PathVariable(name = "id", required = true) Long id, @RequestBody String data, HttpServletRequest httpRequest) throws Exception {
        Request request = requestService.cancelRequest(id);
        return ResponseEntity.ok(request);
    }

    @PreAuthorize("hasAuthority('TRANS_REQUEST_PROCESS')")
    @PutMapping(value = "/{id}/process", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Request> processRequest(@PathVariable(name = "id", required = true) Long id, @RequestBody RequestDto requestDto, HttpServletRequest httpRequest) throws Exception {
        Request request = requestService.processRequest(id, requestDto);
        return ResponseEntity.ok(request);
    }

    @PreAuthorize("hasAuthority('TRANS_REQUEST_EDIT')")
    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestBody String data, HttpServletRequest request) throws Exception {
        Gson g = new Gson();
        RequestDto dto = g.fromJson(data, RequestDto.class);
        return ResponseEntity.ok(requestService.update(id, dto));
    }

    @PreAuthorize("hasAuthority('TRANS_REQUEST_CREATE')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Request> create(@RequestBody RequestDto requestDto, HttpServletRequest request) throws Exception
    {
        return ResponseEntity.ok(requestService.create(requestDto, userService.getLoggedInUser()));
    }

    @PreAuthorize("hasAuthority('TRANS_REQUEST_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(requestService.delete(id));
    }



    // @GetMapping(value = "/{requestboardId}/requests")
    // public ResponseEntity<Map<String, Object>> findRequestsByRequestboard(@PathVariable(name = "requestboardId", required = true) long id) {
    //     System.out.println("request " + id);
    //     Map<String, Object> data = new HashMap<String, Object>();
    //     Request request = requestService.findById(id);

    //     data.put("request", request);
    //     return ResponseEntity.ok(data);
    // }

    // @GetMapping(value = "/list")
    // public List<Request> findAll(){
    //     return requestService.findAll();
    // }

    @GetMapping(value = "/print/{requestId}/{language}")
    public ResponseEntity<byte[]> printVehicleRequest(@PathVariable(name = "requestId", required = false) Long id,
        @PathVariable(name = "language", required = false) String lang) 
        throws IOException, InterruptedException, JSONException, ParseException {
        System.out.println("id from client*********************: " + id);

        RequestPrintDto request = requestService.findRequestPrintDtoById(id);
        // load html to handlebar
        TemplateLoader loader = new ClassPathTemplateLoader("/handlebars/Request", ".html"); //here Request refers to Folder name not file...
        Handlebars handlebars = new Handlebars(loader);

        // Register Equality and String helpers with handlebar for date pattern
        // conversion
        handlebars.registerHelper(ConditionalHelpers.eq.name(), ConditionalHelpers.eq);
        StringHelpers.register(handlebars);

        Template template = handlebars.compile("Request3"); //here Request refers to file name inside Request folder ...

        String logoUrl = "";
        System.out.println(lang);
        if(lang.equals("en")){
            logoUrl = "header3-en.svg";
        }else if(lang.equals("ps")){
            logoUrl = "header3-ps.svg";
        }else if(lang.equals("dr")){
            logoUrl = "header3-dr.svg";
        }
        
        // Create dto
        RequestPrintDto requestPrintDto = 
            RequestPrintDto
            .builder()
            .id(request.getId())
            .pickupLocation(request.getPickupLocation())
            .dropOffLocation(request.getDropOffLocation())
            .pickupDate(request.getPickupDate())
            .pickupTime(request.getPickupTime())
            .returnTime(request.getReturnTime())
            .details(request.getDetails())
            .requestingDepartmentName(request.getRequestingDepartmentName())
            .employeeName(request.getEmployeeName())
            .driverName(request.getDriverName())
            .vehicleDetails(request.getVehicleDetails())
            .startKM(request.getStartKM())
            .endKM(request.getEndKM())
            .logoUri(_uriCreator.imageUriCreator(logoUrl))
            .language(lang)
            .build();

        String templateString = template.apply(requestPrintDto);

        // Generate pdf
        String fileName = "Request3"; 
        File generatedPdf = htmlToPDFCreator.generatePdf(templateString, fileName);

        // convert file to byte[]
        byte[] byteArrayOfFile = fileConverterUtil.fileToByteArray(generatedPdf);

        // Create header for downloading file
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + generatedPdf.getName());

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(byteArrayOfFile);
    }

}