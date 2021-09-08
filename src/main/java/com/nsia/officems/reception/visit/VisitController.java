package com.nsia.officems.reception.visit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.Template;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.FileConverterUtil;
import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems._util.HTMLToPDFCreator;
import com.nsia.officems._util.UriCreator;
import com.nsia.officems.reception.vehicle.ReceptionVehicle;
import com.nsia.officems.reception.vehicle.ReceptionVehicleService;
import com.nsia.officems.reception.visit.dto.MyVisitsPrintDto;
import com.nsia.officems.reception.visit.dto.VisitDto;
import com.nsia.officems.reception.visit_vehicle.VisitVehicle;
import com.nsia.officems.reception.visit_visitor.VisitVisitor;
import com.nsia.officems.reception.visitor.Visitor;
import com.nsia.officems.reception.visitor.VisitorService;
import com.nsia.officems.transportation.vehicle.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/reception/visits")
public class VisitController {

    @Autowired
    private UserService userService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private ReceptionVehicleService vehicleService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HTMLToPDFCreator htmlToPDFCreator;

    @Autowired
    private FileConverterUtil fileConverterUtil;

    private final UriCreator _uriCreator;

    public VisitController(UriCreator _uriCreator) {
        this._uriCreator = _uriCreator;
    }

    @PreAuthorize("hasAuthority('RECEP_VISIT_LIST')")
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
            return visitService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('RECEPTION_MODULE')")
    @PostMapping("/my_list")
    public Object getMyVisitList(@RequestBody String requestBody) throws Exception {
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
            return visitService.getMyList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VISIT_VIEW')")
    @GetMapping(value = "/{visitId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "visitId", required = true) long id) {
        System.out.println("Visit " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Visit visit = visitService.findById(id);

        List<Visitor> visitors = visitorService.findByVisitId(id);
        List<ReceptionVehicle> vehicles = vehicleService.findByVisitId(id);
        data.put("visit", visit);
        data.put("visitors", visitors);
        data.put("vehicles", vehicles);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('RECEP_VISIT_EDIT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Visit> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody(required = true) Visit visit) {
        System.out.println("ID: " + id + " Visit: " + visit);
        // TODO
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VISIT_CREATE')")
    @PostMapping()
    public ResponseEntity<Visit> create(@RequestBody String body) throws JSONException, IOException {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(visitService.create(body, currentLoginUser, false));
    }


    @PreAuthorize("hasAuthority('RECEP_VISIT_SCHEDULE_LIST')")
    @PostMapping("/schedules/list")
    public Object getScheduleVisitList(@RequestBody String requestBody) throws Exception {
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
            return visitService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('RECEP_VISIT_SCHEDULE_CREATE')")
    @PostMapping("/schedules")
    public ResponseEntity<Visit> create_schedule(@RequestBody String body) throws JSONException, IOException {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(visitService.createSchedule(body, currentLoginUser));
    }

    @PreAuthorize("hasAuthority('RECEP_VISIT_CREATE')")
    @PostMapping("/excel_upload")
    public ResponseEntity<List<List<String>>> excel_upload(@RequestParam(value = "file", required = true) final MultipartFile file,
        final HttpServletRequest request) throws JSONException, IOException {
        return ResponseEntity.ok(visitService.readExcelAsJson(file));
    }

    @GetMapping("/excel_template")
    public void getExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        File excelFile = visitService.getExcelTemplate();
        if (excelFile.exists()) {
            System.out.println("File Created");
        }
        fileDownloadUtil.fileDownload(excelFile, response);
    }

    @GetMapping(value = "/print/{visitId}/{language}")
    public ResponseEntity<byte[]> printMyVisitsList(@PathVariable(name = "visitId", required = false) Long id,
        @PathVariable(name = "language", required = false) String lang) 
        throws IOException, InterruptedException, JSONException, ParseException {
        System.out.println("id from client*********************: " + id);

        //RequestPrintDto request = requestService.findRequestPrintDtoById(id);
        VisitDto visit = visitService.findById_Dto(id);
        List<Visitor> visitors = visitorService.findByVisitId(id);
        List<ReceptionVehicle> vehicles = vehicleService.findByVisitId(id);
        // load html to handlebar
        TemplateLoader loader = new ClassPathTemplateLoader("/handlebars/Visits", ".html"); //here ScheduledVisits refers to Folder name not file...
        Handlebars handlebars = new Handlebars(loader);

        // Register Equality and String helpers with handlebar for date pattern
        // conversion
        handlebars.registerHelper(ConditionalHelpers.eq.name(), ConditionalHelpers.eq);
        StringHelpers.register(handlebars);

        Template template = handlebars.compile("MyVisits2"); //here Request refers to file name inside Request folder ...

        String logoUrl = "nsia-logo.svg";
        
        // Create dto
        MyVisitsPrintDto myVisitsPrintDto = 
            MyVisitsPrintDto
            .builder()
            .visit(visit)
            .visitors(visitors)
            .vehicles(vehicles)
            .logoUri(_uriCreator.imageUriCreator(logoUrl))
            .language(lang)
            .build();

        String templateString = template.apply(myVisitsPrintDto); //pass dto here to print template ...

        // Generate pdf
        String fileName = "MyVisits2"; 
        File generatedPdf = htmlToPDFCreator.generatePdf(templateString, fileName);

        // convert file to byte[]
        byte[] byteArrayOfFile = fileConverterUtil.fileToByteArray(generatedPdf);

        // Create header for downloading file
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + generatedPdf.getName());

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(byteArrayOfFile);
    }
}