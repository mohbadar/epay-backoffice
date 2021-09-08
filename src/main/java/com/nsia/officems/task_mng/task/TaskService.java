package com.nsia.officems.task_mng.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.task_mng.task.dto.TaskDto;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface TaskService {
    public Task save(Task obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Object findArchivedByTaskboardId(DataTablesInput input, Map<String, String> filters, Long taskboardId);
    public List<Task> findByTaskboardId(Long taskboardId);
    public List<Task> findOrphanByTaskboardId(Long taskboardId);
    public List<Task> findSubTasks(Long taskId);
    public Task findById(Long id);
    public boolean archive(long id, User user);
    public boolean delete(long id, User user);
    public Task create(String data, User loggedInUser) throws JSONException;
    public Task update(Long id, TaskDto dto);
    public Task updateTaskStatus(Long taskId, String newStatus);
    public User addAssignee(Long taskId, String data, User loggedInUser) throws JSONException;
    public Boolean removeAssignee(Long taskId, Long assigneeId) throws JSONException;
    public Task addSubTask(Long taskId, Long subTaskId);
    public long countALL();
    public long countToday();
    public List getTaskCountByTaskBoard();
    public List getTaskCountByStatus();

    public Task createDueDate(long id, String data) throws JSONException;
    public Task createCompletionDate(long id, String data) throws JSONException;

}
