package com.nsia.officems.edu_publication.profile;

import java.io.File;
import java.io.IOException;
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
import com.nsia.officems._util.FileConverterUtil;
import com.nsia.officems._util.HTMLToPDFCreator;
import com.nsia.officems._util.UriCreator;
import com.nsia.officems.edu_publication.profile.dto.TeacherProfileDto;
import com.nsia.officems.edu_publication.profile.dto.TeacherProfilePrintDto;
import com.nsia.officems.edu_publication.profile_education.Education;
import com.nsia.officems.edu_publication.profile_education.EducationService;
import com.nsia.officems.edu_publication.profile_job.ProfileJob;
import com.nsia.officems.edu_publication.profile_job.ProfileJobService;
import com.nsia.officems.edu_publication.profile_job.Dto.ProfileJobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/edu-publication/teacher-profile")

public class TeacherProfileController {

    @Autowired
    private TeacherProfileService profileService;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HTMLToPDFCreator _htmlToPDFCreator;
    @Autowired
    private FileConverterUtil _fileConverterUtil;

    @Autowired
    private UriCreator _uriCreator;

    @Autowired
    private EducationService educationService;

    @Autowired
    private ProfileJobService profileJobService2;

    @PostMapping("/list")
    public Object getList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return profileService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TeacherProfile> findById(@PathVariable(name = "id", required = true) long id) {
        return ResponseEntity.ok(profileService.findById(id));
    }

    @GetMapping(value = "/{id}/details")
    public ResponseEntity<Map<String, Object>> findDetailsById(@PathVariable(name = "id", required = true) long id) {
        return ResponseEntity.ok(profileService.findDetailsById(id));
    }

    @PutMapping(value = "/{id}/edit")
    public ResponseEntity<Map<String, Object>> update(@PathVariable("id") Long id,
            @RequestParam(name = "personalInfo") String personalInfo,
            @RequestParam(name = "academicInfo") String academicInfo) throws JSONException, IOException {
        if (personalInfo != null) {
            Map<String, Object> savedProfile = new HashMap<>();
            Gson g = new Gson();
            TeacherProfileDto profileDto = g.fromJson(personalInfo, TeacherProfileDto.class);

            savedProfile = profileService.update(profileDto, id);
            ProfileJobDto profileJobDto = g.fromJson(academicInfo, ProfileJobDto.class);
            profileJobDto.setProfile(Long.parseLong(savedProfile.get("updatedId").toString()));
            profileJobService.update(profileJobDto.getId(), profileJobDto);
            return ResponseEntity.ok(savedProfile);
        }

        return null;

    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> create(@RequestParam(name = "personalInfo") String personalInfo,
            @RequestParam(name = "academicInfo") String academicInfo) throws JSONException, IOException {
        if (personalInfo != null) {
            Map<String, Object> savedProfile = new HashMap<>();
            Gson g = new Gson();
            TeacherProfileDto profileDto = g.fromJson(personalInfo, TeacherProfileDto.class);

            savedProfile = profileService.create(profileDto);
            ProfileJobDto profileJobDto = g.fromJson(academicInfo, ProfileJobDto.class);
            profileJobDto.setProfile(Long.parseLong(savedProfile.get("createdId").toString()));
            profileJobService.create(profileJobDto);
            return ResponseEntity.ok(savedProfile);
        }

        return null;

    }

    @GetMapping("/{id}/print-summary")
    public ResponseEntity<byte[]> printSummary(@PathVariable(name = "id") Long id) throws IOException, InterruptedException {

        TeacherProfile teacherProfile = profileService.findById(id);
        List<Education> education = educationService.findByProfile_id(id);
        List<ProfileJob> profileJobs = profileJobService.findByProfile_id(id);
        if (teacherProfile != null) {

            TeacherProfilePrintDto teacherProfilePrintDto = TeacherProfilePrintDto.builder().profile(teacherProfile)
                    .education(education)
                    .profileJobs(profileJobs)
                    .build();

            // load html to handlebar
            TemplateLoader loader = new ClassPathTemplateLoader("/handlebars/eduPublication/profile", ".html");
            Handlebars handlebars = new Handlebars(loader);

            // Register Equality and String helpers with handlebar for date pattern
            // conversion
            handlebars.registerHelper(ConditionalHelpers.eq.name(), ConditionalHelpers.eq);
            StringHelpers.register(handlebars);

            Template template = handlebars.compile("profile");
            String templateString = template.apply(teacherProfilePrintDto);

            // Generate pdf
            String fileName = "profile";
            File generatedPdf = _htmlToPDFCreator.generatePdf(templateString, fileName);
            // convert file to byte[]
            byte[] byteArrayOfFile = _fileConverterUtil.fileToByteArray(generatedPdf);

            // Create header for downloading file
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + generatedPdf.getName());
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(byteArrayOfFile);

        }

        return ResponseEntity.ok(null);
    }
}
