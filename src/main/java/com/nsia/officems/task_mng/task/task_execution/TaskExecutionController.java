package com.nsia.officems.task_mng.task.task_execution;

import java.io.File;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems.task_mng.task.task_execution.dto.TaskExecutionDto;

@RestController
@RequestMapping("/api/task_mng/tasks/execution")
public class TaskExecutionController {

    @Autowired
    private TaskExecutionService taskExecutionService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    // @Autowired
    // private ObjectMapper objectMapper;

    // @GetMapping()
    // public List<TaskExecution> findAll() {
    //     return taskExecutionService.findAll();
    // }

    // @GetMapping(path = { "/{id}" })
    // public TaskExecution findById(@PathVariable("id") Long id) {
    //     return taskExecutionService.findById(id);
    // }

    // @PostMapping()
    // public TaskExecution create(@RequestBody TaskExecutionDto taskExecutionDto) {
    //     return taskExecutionService.create(taskExecutionDto);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
    //     return ResponseEntity.ok(taskExecutionService.delete(id));
    // }

    

}