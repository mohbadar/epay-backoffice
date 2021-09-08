package com.nsia.officems.task_mng.taskboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.task_mng.task.Task;
import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.taskboard.dto.TaskboardDto;
import com.nsia.officems.task_mng.taskboard.taskboard_status.TaskboardStatus;
import com.nsia.officems.task_mng.taskboard.taskboard_status.TaskboardStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task_mng/taskboards")
public class TaskboardController {
    
    @Autowired
    private TaskboardService taskboardService;

    @Autowired
    private TaskboardStatusService taskboardStatusService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @GetMapping("/list")
    public Object getMyTaskboards() throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        List<Taskboard> taskboards = taskboardService.findMyTaskboards();
        List<Taskboard> sharedTaskboards = taskboardService.findSharedTaskboards();
        List<Taskboard> publicTaskboards = taskboardService.findByIsPublic(true);
        data.put("taskboards", taskboards);
        data.put("shared", sharedTaskboards);
        data.put("public", publicTaskboards);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/accessable")
    public Object getTaskboards() throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        List<Taskboard> taskboards = taskboardService.findMyTaskboards();
        // List<Taskboard> sharedTaskboards = taskboardService.findSharedTaskboards();
        // taskboards.addAll(sharedTaskboards);
        data.put("taskboards", taskboards);
        data.put("viewOnly", "");
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/{taskboardId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "taskboardId", required = true) long id) {
        System.out.println("Taskboard " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Taskboard taskboard = taskboardService.findById(id);
        List<TaskboardStatus> taskboardStatus = taskboardStatusService.findByTaskboardId(id);
        List<User> members = taskboard.getMembers();
        data.put("taskboard", taskboard);
        data.put("status", taskboardStatus);
        data.put("members", members);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/{taskboardId}/tasks")
    public ResponseEntity<List<Task>> findTasksByTaskboard(@PathVariable(name = "taskboardId", required = true) long taskboardId) {
        System.out.println("Taskboard " + taskboardId);
        List<Task> tasks = taskService.findByTaskboardId(taskboardId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/{taskboardId}/archived_tasks")
    public Object getArchivedTaskList(@PathVariable(name = "taskboardId", required = true) long taskboardId, @RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside getArchivedTasksList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return taskService.findArchivedByTaskboardId(input, filters, taskboardId);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    // It will return the list of tasks of specific taskboard that does not have parent task
    @GetMapping(value = "/{taskboardId}/orphans")
    public ResponseEntity<List<Task>> findOrphanTasksByTaskboard(@PathVariable(name = "taskboardId", required = true) long taskboardId) {
        System.out.println("Taskboard " + taskboardId);
        List<Task> tasks = taskService.findOrphanByTaskboardId(taskboardId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Taskboard> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody(required = true) TaskboardDto taskboardDto) {
        System.out.println("ID: " + id + " Taskboard: " + taskboardDto);
        return ResponseEntity.ok(taskboardService.update(id, taskboardDto));
    }

    @PreAuthorize("hasAuthority('TASKMNG_TASKBOARD_CREATE')")
    @PostMapping()
    public ResponseEntity<Taskboard> create(@RequestBody String body) throws JSONException, IOException {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(taskboardService.create(body, currentLoginUser));
    }

    @PostMapping(value = "/{taskboardId}/status")
    public ResponseEntity<TaskboardStatus> createStatus(@PathVariable(name = "taskboardId", required = true) long taskboardId, 
        @RequestBody String body) throws JSONException, IOException {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(taskboardStatusService.createStatus(taskboardId, body, currentLoginUser));
    }

    @PostMapping(value = "/{taskboardId}/members")
    public User addMember(@PathVariable(name = "taskboardId", required = true) long taskboardId, 
        @RequestBody String body) throws JSONException, IOException {
        User currentLoginUser = userService.getLoggedInUser();
        return taskboardService.addMember(taskboardId, body, currentLoginUser);
    }

    @DeleteMapping(value = "/{taskboardId}/members/{memberId}")
    public ResponseEntity<Boolean> removeMember(@PathVariable(name = "taskboardId", required = true) long taskboardId, 
        @PathVariable(name = "memberId", required = true) long memberId) throws JSONException, IOException {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(taskboardService.removeMember(taskboardId, memberId));
    }
}