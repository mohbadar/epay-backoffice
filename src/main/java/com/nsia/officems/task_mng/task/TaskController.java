package com.nsia.officems.task_mng.task;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems.task_mng.task.dto.TaskDto;
import com.nsia.officems.task_mng.task.task_attachment.TaskAttachment;
import com.nsia.officems.task_mng.task.task_attachment.TaskAttachmentService;
import com.nsia.officems.task_mng.task.task_comment.TaskComment;
import com.nsia.officems.task_mng.task.task_comment.TaskCommentService;
import com.nsia.officems.task_mng.task.task_comment.dto.TaskCommentDto;
import com.nsia.officems.task_mng.task.task_execution.TaskExecution;
import com.nsia.officems.task_mng.task.task_execution.TaskExecutionService;
import com.nsia.officems.task_mng.task.task_execution.dto.TaskExecutionDto;
import com.nsia.officems.task_mng.task.task_status.TaskStatus;
import com.nsia.officems.task_mng.task.task_status.TaskStatusService;
import org.springframework.http.MediaType;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/task_mng/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskCommentService taskCommentService;

    @Autowired
    private TaskExecutionService taskExecutionService;

    @Autowired
    private TaskAttachmentService taskAttachmentService;

    @Autowired
    private TaskStatusService taskStatusService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/list")
    public Object getTaskList(@RequestBody String requestBody) throws Exception {
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
            return taskService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "taskId", required = true) long id) {
        System.out.println("task " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Task task = taskService.findById(id);
        List<TaskComment> taskComments = task.getTaskComments();
        List<TaskAttachment> taskAttachments = task.getTaskAttachments();
        List<TaskExecution> taskExecutions = task.getTaskExecutions();
        List<Task> subTasks = taskService.findSubTasks(task.getId());
        data.put("task", task);
        data.put("subTasks", subTasks);
        data.put("taskComments", taskComments);
        data.put("taskAttachments", taskAttachments);
        data.put("taskExecutions", taskExecutions);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/{taskboardId}/tasks")
    public ResponseEntity<Map<String, Object>> findTasksByTaskboard(@PathVariable(name = "taskboardId", required = true) long id) {
        System.out.println("task " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Task task = taskService.findById(id);

        data.put("task", task);
        return ResponseEntity.ok(data);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody(required = true) TaskDto taskDto) {
        System.out.println("ID: " + id + " taskDto: " + taskDto);
        return ResponseEntity.ok(taskService.update(id, taskDto));
    }

    @PreAuthorize("hasAuthority('TASKMNG_TASK_CREATE')")
    @PostMapping()
    public ResponseEntity<Task> create(@RequestBody String body) throws JSONException, IOException {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(taskService.create(body, currentLoginUser));
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Boolean> archive(@PathVariable("id") Long id) {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(taskService.archive(id, currentLoginUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(taskService.delete(id, currentLoginUser));
    }

    @PutMapping(value = "/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable(name = "taskId", required = true) Long taskId,
        @RequestBody(required = true) TaskDto taskDto) {
        System.out.println("ID: " + taskId + " taskDto: " + taskDto);
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, taskDto.getStatus()));
    }

    // --------------- TASK COMMENTS ------------------------

    @GetMapping(value = "/{taskId}/comments")
    public List<TaskComment> findAllCommentsByTaskId(@PathVariable(name = "taskId", required = true) long taskId) {
        System.out.println("task " + taskId);
        return taskCommentService.findByTaskId(taskId);
    }

    @PostMapping(value = "/{taskId}/comments")
    public TaskComment createComment(@PathVariable(name = "taskId", required = true) long taskId, @RequestBody TaskCommentDto taskCommentDto) {
        taskCommentDto.setTaskId(taskId);
        return taskCommentService.create(taskCommentDto);
    }

    @PutMapping(value = "/{taskId}/comments")
    public ResponseEntity<Task> updateComment(@PathVariable(name = "taskId", required = true) Long taskId,
            @RequestBody TaskCommentDto taskCommentDto) {
        System.out.println("ID: " + taskId + " taskComment: " + taskCommentDto);
        // TODO
        return null;
    }

    // --------------- TASK Executions ------------------------
    @GetMapping(value = "/{taskId}/executions")
    public List<TaskExecution> findAllExecutionsByTaskId(@PathVariable(name = "taskId", required = true) long taskId) {
        System.out.println("task " + taskId);
        return taskExecutionService.findByTaskId(taskId);
    }

    @PostMapping(value = "/{taskId}/executions")
    public TaskExecution createExecution(@PathVariable(name = "taskId", required = true) long taskId, @RequestParam(name = "avatar", required = false) MultipartFile file, @RequestParam("data") String data) {
        Gson g = new Gson();
        TaskExecutionDto dto = g.fromJson(data, TaskExecutionDto.class);
        dto.setTaskId(taskId);
        return taskExecutionService.create(dto,file);
    }

    @PutMapping(value = "/{taskId}/executions")
    public ResponseEntity<Task> updateExecution(@PathVariable(name = "taskId", required = true) Long taskId,
            @RequestBody TaskExecutionDto taskExecutionDto) {
        System.out.println("ID: " + taskId + " taskExecutionDto: " + taskExecutionDto);
        // TODO
        return null;
    }

    @GetMapping(value = "/downloadFile/executions/{id}")
    public void downloadExecutionAttachment(@PathVariable(name = "id", required = true) Long id, HttpServletResponse response)
            throws Exception {
        File file = taskExecutionService.downloadAttachment(id);
        fileDownloadUtil.fileDownload(file, response);
    }

    // --------------- Task Attachment ---------------
    @GetMapping(value = "/{taskId}/attachments")
    public List<TaskAttachment> findAllAttachmentsByTaskId(@PathVariable(name = "taskId", required = true) long taskId) {
        System.out.println("task " + taskId);
        return taskAttachmentService.findByTaskId(taskId);
    }

    @PostMapping(value = "/{taskId}/attachments")
    public ResponseEntity<TaskAttachment> createAttachement(@RequestParam(name = "file", required = false) MultipartFile file, @PathVariable(name = "taskId", required = true) long taskId,
            HttpServletRequest request) throws Exception {
        TaskAttachment obj = taskAttachmentService.create(taskId, file);
        return ResponseEntity.ok(obj);
    }

    @GetMapping(value = "/downloadFile/attachments/{id}")
    public void downloadTaskAttachment(@PathVariable(name = "id", required = true) Long id, HttpServletResponse response)
            throws Exception {
        File file = taskAttachmentService.downloadAttachment(id);
        fileDownloadUtil.fileDownload(file, response);
    }

    //  Task Assignee
    @PostMapping(value = "/{taskId}/assignees")
    public User addAssignee(@PathVariable(name = "taskId", required = true) long taskId, 
        @RequestBody String body) throws JSONException, IOException {
        User currentLoginUser = userService.getLoggedInUser();
        return taskService.addAssignee(taskId, body, currentLoginUser);
    }

    @DeleteMapping(value = "/{taskId}/assignees/{assigneeId}")
    public ResponseEntity<Boolean> removeAssignee(@PathVariable(name = "taskId", required = true) long taskId, 
        @PathVariable(name = "assigneeId", required = true) long assigneeId) throws JSONException, IOException {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(taskService.removeAssignee(taskId, assigneeId));
    }

    // Add SubTask
    @PostMapping(value = "/{taskId}/subtask/{subTaskId}")
    public Task addSubTask(@PathVariable(name = "taskId", required = true) long taskId, 
    @PathVariable(name = "subTaskId", required = true) long subTaskId) throws JSONException, IOException {
        return taskService.addSubTask(taskId, subTaskId);
    }

    // --------------- Task Statuses History ---------------
    @GetMapping(value = "/{taskId}/statuses")
    public List<TaskStatus> findAllStatusesByTaskId(@PathVariable(name = "taskId", required = true) long taskId) {
        System.out.println("task " + taskId);
        return taskStatusService.findByTaskId(taskId);
    }

    @PostMapping(value = "/{taskId}/due-date")
    public@ResponseBody ResponseEntity<Task> createDueDate(@PathVariable(name = "taskId", required = true) long taskId, 
    @RequestBody(required = true) String data,
    HttpServletRequest request) throws JSONException, IOException {
        return ResponseEntity.ok(taskService.createDueDate(taskId, data));
    }

    @PostMapping(value = "/{taskId}/completion-date")
    public @ResponseBody ResponseEntity<Task> createCompletionDate(@PathVariable(name = "taskId", required = true) long taskId, 
    @RequestBody(required = true) String data,
    HttpServletRequest request) throws JSONException, IOException {
        return ResponseEntity.ok(taskService.createCompletionDate(taskId, data));
    }
}