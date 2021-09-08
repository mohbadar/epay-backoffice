package com.nsia.officems.task_mng.task.impl;

import com.nsia.officems.task_mng.task.Task;
import com.nsia.officems.task_mng.task.TaskRepository;
import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.task.dto.TaskDto;
import com.nsia.officems.task_mng.taskboard.Taskboard;
import com.nsia.officems.task_mng.taskboard.TaskboardService;
import com.nsia.officems.task_mng.task.dto.TaskMapper;
import com.nsia.officems.task_mng.task.task_status.TaskStatus;
import com.nsia.officems.task_mng.task.task_status.TaskStatusService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.DateTimeChange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskboardService taskboardService;

    @Autowired
    private TaskStatusService taskStatusService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private TaskRepository  taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        User currentLoginUser = userService.getLoggedInUser();
        String joinClause = "inner join public.user_tbl usr on usr.id=tsk.created_by";
        joinClause += " inner join public.taskmng_taskboard board on board.id=tsk.taskboard_id";
        // To have first AND with no error
        String whereClause = " tsk.created_by=" + currentLoginUser.getId() + " " + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.taskmng_task tsk", null, joinClause, whereClause, groupByClause, input);
    }

    public Object findArchivedByTaskboardId(DataTablesInput input, Map<String, String> filters, Long taskboardId) {
        User currentLoginUser = userService.getLoggedInUser();
        String joinClause = "inner join public.user_tbl usr on usr.id=tsk.created_by";
        joinClause += " inner join public.taskmng_taskboard board on board.id=tsk.taskboard_id";
        // To have first AND with no error
        String whereClause = " tsk.taskboard_id=" + taskboardId + " and tsk.archived=true " + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.taskmng_task tsk", null, joinClause, whereClause, groupByClause, input);
    }

    @Override
    public List<Task> findByTaskboardId(Long taskboardId) {
        return taskRepository.findByTaskboardIdAndDeletedFalseAndArchivedFalse(taskboardId);
    }

    @Override
    public List<Task> findOrphanByTaskboardId(Long taskboardId) {
        return taskRepository.findOrphanByTaskboardId(taskboardId);
    }

    @Override
    public List<Task> findSubTasks(Long taskId) {
        return taskRepository.findByParentId(taskId);
    }
    
    @Override
    public Task findById(Long id) {
        System.out.println("Task.findById()" + id);
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            System.out.println("Task: " + task);
            return task.get();
        }
        return null;
    }

    public boolean archive(long id, User user) {
        Task task = findById(id);
        LocalDateTime localDateTime = LocalDateTime.now();
        if (task != null) {
            task.setArchivedBy(user.getId());
            task.setArchivedAt(localDateTime);
            task.setArchived(true);
            save(task);
            return true;
        }
        return false;
    }

    public boolean delete(long id, User user) {
        Task task = findById(id);
        LocalDateTime localDateTime = LocalDateTime.now();
        if (task != null) {
            if(!user.getId().equals(task.getCreatedBy().getId())) {
                throw new AccessDeniedException("Only task owner can delete a task.");
            }
            long minutes = task.getCreatedAt().until(localDateTime, ChronoUnit.MINUTES);
            if(minutes > 60) {
                throw new AccessDeniedException("A task can be deleted within 60 mins after creation.");
            }
            task.setDeletedBy(user.getId());
            task.setDeletedAt(localDateTime);
            task.setDeleted(true);
            save(task);
            return true;
        }
        return false;
    }

    @Override
    public Task save(Task obj) {
        return this.taskRepository.save(obj);
    }

    @Override
    public Task create(String data, User loggedInUser) throws JSONException {
        JSONObject obj = new JSONObject(data);
        Task task = new Task();
        task.setTitle(obj.get("title") == null? null: obj.get("title").toString());
        task.setDescription(obj.get("description") == null? null: obj.get("description").toString());
        task.setTags(obj.get("tags") == null? null: obj.get("tags").toString());
        task.setCreatedBy(loggedInUser);

        List<User> assignedToList = new ArrayList();
        assignedToList.add(loggedInUser);
        task.setAssignedTo(assignedToList);

        Long taskboardId = obj.get("taskboard") == null? null: Long.valueOf(obj.get("taskboard").toString());
        Taskboard taskboard = taskboardService.findById(taskboardId);
        task.setTaskboard(taskboard);
        task = taskRepository.save(task);
        return task;
    }

    @Override
    public Task update(Long id, TaskDto dto) {
        Task task = findById(id);
        User currentLoginUser = userService.getLoggedInUser();

        if(!currentLoginUser.getId().equals(task.getCreatedBy().getId())) {
            throw new AccessDeniedException("Only task owner can edit a task.");
        }

        task = TaskMapper.map(dto, task, departmentService, this);
        return save(task);
    }

    @Override
    public Task updateTaskStatus(Long taskId, String newStatus) {
        User currentLoginUser = userService.getLoggedInUser();
        Task task = findById(taskId);
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setPrvStatus(task.getStatus());

        task.setStatus(newStatus);
        task = save(task);
        
        taskStatus.setNewStatus(newStatus);
        taskStatus.setCreatedBy(currentLoginUser);
        taskStatus.setTask(task);
        taskStatusService.save(taskStatus);
        return task;
    }

    @Override
    public User addAssignee(Long taskId, String data, User loggedInUser) throws JSONException {
        JSONObject obj = new JSONObject(data);
        String userId = (obj.get("userId") == null)? null: obj.get("userId").toString();
        if(userId != null && userId.length() > 0) {
            User newAssignee = userService.findById(Long.valueOf(userId));
            Task task = findById(taskId);
            List<User> taskAssignees = task.getAssignedTo();
            taskAssignees.add(newAssignee);
            task.setAssignedTo(taskAssignees);
            save(task);
            return newAssignee;
        }
        return null;
    }

    @Override
    public Boolean removeAssignee(Long taskId, Long assigneeId) throws JSONException {
        Long removeAssigneeId = Long.valueOf(assigneeId);
        Task task = findById(taskId);
        List<User> taskAssignees = task.getAssignedTo();
        taskAssignees.removeIf(member -> member.getId().equals(removeAssigneeId));
        task.setAssignedTo(taskAssignees);
        save(task);
        return true;
    }

    @Override
    public Task addSubTask(Long taskId, Long subTaskId) {
        if(taskId != null && subTaskId != null) {
            Task task = findById(taskId);
            Task subTask = findById(subTaskId);
            subTask.setParent(task);
            save(subTask);
            return subTask;
        }
        return null;
    }

    @Override
    public long countALL() {
        return taskRepository.countALL();
    }

    @Override
    public long countToday() {
        return taskRepository.countToday();
    }

    @Override
    public List getTaskCountByTaskBoard() {
        return taskRepository.getTaskCountByTaskBoard();
    }

    @Override
    public List getTaskCountByStatus() {
        return taskRepository.getTaskCountByStatus();
    }

    @Override
    public Task createDueDate(long id, String data) throws JSONException {
        Task task = findById(id);
        DateTimeChange changeDate = new DateTimeChange();
        JSONObject obj = new JSONObject(data);
        task.setDueDate(obj.get("dueDate") == null? null: changeDate.convertPersianDateToGregorianDate(obj.get("dueDate").toString()));
        return save(task);
    }

    @Override
    public Task createCompletionDate(long id, String data) throws JSONException {
        Task task = findById(id);
        DateTimeChange changeDate = new DateTimeChange();
        JSONObject obj = new JSONObject(data);
        task.setCompletionDate(obj.get("completionDate") == null? null: changeDate.convertPersianDateToGregorianDate(obj.get("completionDate").toString()));
        return save(task);
    }
}
