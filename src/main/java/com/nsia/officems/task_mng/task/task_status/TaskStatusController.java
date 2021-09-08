package com.nsia.officems.task_mng.task.task_status;

import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems.task_mng.task.task_status.dto.TaskStatusDto;

@RestController
@RequestMapping("/api/task_mng/tasks/")
public class TaskStatusController {

    // @Autowired
    // private TaskStatusService taskCommentService;

    // @Autowired
    // private ObjectMapper objectMapper;

    // @GetMapping()
    // public List<TaskStatus> findAll() {
    //     return taskCommentService.findAll();
    // }

    // @GetMapping(path = { "/{id}" })
    // public TaskStatus findById(@PathVariable("id") Long id) {
    //     return taskCommentService.findById(id);
    // }

    // @PostMapping()
    // public TaskStatus create(@RequestBody TaskStatusDto taskCommentDto) {
    //     return taskCommentService.create(taskCommentDto);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
    //     return ResponseEntity.ok(taskCommentService.delete(id));
    // }

}