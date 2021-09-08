package com.nsia.officems.task_mng.task.task_comment;

import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems.task_mng.task.task_comment.dto.TaskCommentDto;

@RestController
@RequestMapping("/api/task_mng/tasks/")
public class TaskCommentController {

    // @Autowired
    // private TaskCommentService taskCommentService;

    // @Autowired
    // private ObjectMapper objectMapper;

    // @GetMapping()
    // public List<TaskComment> findAll() {
    //     return taskCommentService.findAll();
    // }

    // @GetMapping(path = { "/{id}" })
    // public TaskComment findById(@PathVariable("id") Long id) {
    //     return taskCommentService.findById(id);
    // }

    // @PostMapping()
    // public TaskComment create(@RequestBody TaskCommentDto taskCommentDto) {
    //     return taskCommentService.create(taskCommentDto);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
    //     return ResponseEntity.ok(taskCommentService.delete(id));
    // }

}