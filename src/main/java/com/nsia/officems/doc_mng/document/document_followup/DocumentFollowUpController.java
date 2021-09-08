package com.nsia.officems.doc_mng.document.document_followup;

import org.springframework.http.MediaType;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.DocFollowUpActivity;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.dto.DocFollowUpActivityDTO;
import com.nsia.officems.doc_mng.document.document_followup.dto.DocumentFollowUpDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/doc_mng/document_followups")
public class DocumentFollowUpController {
    
    @Autowired
    private DocumentFollowUpService service;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;


    @GetMapping()
    public List<DocumentFollowUp> findAll(){
        return service.findAll();
    }

    //@PreAuthorize("hasAuthority('')")
    @GetMapping("/document")
    public List<DocumentFollowUpDto> getFollowupByOrder(@RequestParam("pId") Long pId ) {
        return service.findByDocument_id(pId);
    }

    //@PreAuthorize("hasAuthority('')")
    @GetMapping(path = { "/count" })
        public List getFollowUpCountByType(@RequestParam("pId") Long pId) {
        return service.getFollowUpCountByType(pId);
    }

    //@PreAuthorize("hasAuthority('')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "id", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        DocumentFollowUp objection = service.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/details/{id}")
    public ResponseEntity<DocumentFollowUpDto> getDetails(@PathVariable(name = "id", required = true) long id) {
        System.out.println("Objection " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        DocumentFollowUp objection = service.findById(id);
        data.put("objection", objection);
        return ResponseEntity.ok(service.getDetails(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> update(@PathVariable(name = "id", required = true) Long id, @RequestParam(name = "avatar", required = false) MultipartFile file, @RequestParam("data") String data, HttpServletRequest request) throws Exception { 
        System.out.println("ID: ******* " + id + " Profile: ********" + data);
        Gson g = new Gson();
        DocumentFollowUpDto dto = g.fromJson(data, DocumentFollowUpDto.class);
        return ResponseEntity.ok(service.update(id, dto, file));
        
	}

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<DocFollowUpActivity> create(@RequestParam(name = "attachment", required = false) MultipartFile file, @RequestParam("data") String data, HttpServletRequest request) throws Exception
    {
        Gson g = new Gson();
        DocFollowUpActivityDTO dto = g.fromJson(data, DocFollowUpActivityDTO.class);
        return ResponseEntity.ok(service.create(dto, file));
    }

    //@PreAuthorize("hasAuthority('')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping(value = "/downloadFile/{id}")
    public void downloadAttachment(@PathVariable(name = "id", required = true) Long id,
            HttpServletResponse response) throws Exception {
        File file = service.downloadAttachment(id);
        fileDownloadUtil.fileDownload(file, response);
    }

    @PostMapping("/assign-users/{id}")
    public ResponseEntity<List<DocumentFollowUp>> assignUsers(@RequestParam(value = "dueDate", required = false) String dueDate, @RequestParam("userIds") List<Long> userIds, @PathVariable("id") Long docNo) {
        System.out.println(userIds.toString());
        return ResponseEntity.ok(service.assignUsers(dueDate, userIds, docNo));
    }

    @PostMapping("/assign-user/{id}")
    public ResponseEntity<DocumentFollowUp> assignUser(@RequestParam(value = "dueDate", required = false) String dueDate, @RequestParam("userId") Long userId, @PathVariable("id") Long docNo) {
        return ResponseEntity.ok(service.assignUser(dueDate, userId, docNo));
    }

    @PutMapping("/due-date/{id}")
    public ResponseEntity<Map<String, Object>> updateDueDate(@RequestParam(value = "dueDate") String dueDate, @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.updateDueDate(id, dueDate));
    }
   
}