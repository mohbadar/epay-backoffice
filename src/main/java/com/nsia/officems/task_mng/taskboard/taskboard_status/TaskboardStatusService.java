package com.nsia.officems.task_mng.taskboard.taskboard_status;

import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface TaskboardStatusService {
    public TaskboardStatus save(TaskboardStatus obj);
    public TaskboardStatus createStatus(Long taskboardId, String data, User loggedInUser) throws JSONException;
    public List<TaskboardStatus> findByTaskboardId(Long taskboardId);
}
