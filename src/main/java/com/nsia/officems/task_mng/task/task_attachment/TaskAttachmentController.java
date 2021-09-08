package com.nsia.officems.task_mng.task.task_attachment;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems.task_mng.task.task_attachment.dto.TaskAttachmentDto;

@RestController
@RequestMapping("/api/task_mng/tasks/attachment")
public class TaskAttachmentController {

    @Autowired
    private TaskAttachmentService taskAttachmentService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    // @Autowired
    // private ObjectMapper objectMapper;

    // @GetMapping()
    // public List<TaskAttachment> findAll() {
    //     return taskAttachmentService.findAll();
    // }

    // @GetMapping(path = { "/{id}" })
    // public TaskAttachment findById(@PathVariable("id") Long id) {
    //     return taskAttachmentService.findById(id);
    // }

    // @PostMapping()
    // public TaskAttachment create(@RequestBody TaskAttachmentDto taskAttachmentDto) {
    //     return taskAttachmentService.create(taskAttachmentDto);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
    //     return ResponseEntity.ok(taskAttachmentService.delete(id));
    // }


    

}