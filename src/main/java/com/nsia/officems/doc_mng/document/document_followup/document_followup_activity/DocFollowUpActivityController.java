package com.nsia.officems.doc_mng.document.document_followup.document_followup_activity;

import com.google.gson.Gson;
import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems.doc_mng.document.document_followup.document_followup_activity.dto.DocFollowUpActivityDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@RequestMapping("/api/doc_mng/doc-followup-activities")
public class DocFollowUpActivityController {

    @Autowired
    DocActivityFollowUpService docActivityFollowUpService;

    @Autowired
    FileDownloadUtil fileDownloadUtil;

    @GetMapping("/{id}")
    public ResponseEntity<DocFollowUpActivityDTO> getDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(docActivityFollowUpService.getDetails(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DocFollowUpActivity> create(@RequestParam(name = "attachment", required = false) MultipartFile file, @RequestParam("data") String data, HttpServletRequest request) throws Exception
    {
        Gson g = new Gson();
        DocFollowUpActivityDTO dto = g.fromJson(data, DocFollowUpActivityDTO.class);
        return ResponseEntity.ok(docActivityFollowUpService.create(dto, file));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DocFollowUpActivityDTO> update(@PathVariable("id") Long id, @RequestParam(name = "attachment", required = false) MultipartFile file, @RequestParam("data") String data, HttpServletRequest request) throws Exception
    {
        Gson g = new Gson();
        DocFollowUpActivityDTO dto = g.fromJson(data, DocFollowUpActivityDTO.class);
        return ResponseEntity.ok(docActivityFollowUpService.update(dto, id, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(docActivityFollowUpService.deleteDocFollowUpActivity(id));
    }

    @GetMapping(value = "/download-file/{id}")
    public void downloadAttachment(@PathVariable(name = "id", required = true) Long id,
                                   HttpServletResponse response) throws Exception {
        File file = docActivityFollowUpService.downloadAttachment(id);
        fileDownloadUtil.fileDownload(file, response);
    }

    @PutMapping(value = "/finalize/{id}")
    public ResponseEntity<DocFollowUpActivityDTO> finalizeActivity(@PathVariable(name = "id", required = true) Long id) {
        return ResponseEntity.ok(docActivityFollowUpService.finalizeActivity(id));
    }
}
