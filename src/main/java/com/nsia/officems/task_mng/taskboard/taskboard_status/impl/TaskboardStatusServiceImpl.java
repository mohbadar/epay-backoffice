package com.nsia.officems.task_mng.taskboard.taskboard_status.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.task_mng.taskboard.Taskboard;
import com.nsia.officems.task_mng.taskboard.TaskboardService;
import com.nsia.officems.task_mng.taskboard.taskboard_status.TaskboardStatus;
import com.nsia.officems.task_mng.taskboard.taskboard_status.TaskboardStatusRepository;
import com.nsia.officems.task_mng.taskboard.taskboard_status.TaskboardStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class TaskboardStatusServiceImpl implements TaskboardStatusService {
    @Autowired
    private UserService userService;
    
    @Autowired
    private TaskboardService taskboardService;

    @Autowired
    private TaskboardStatusRepository  taskboardStatusRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public List<TaskboardStatus> findByTaskboardId(Long taskboardId) {
        return this.taskboardStatusRepository.findByTaskboardId(taskboardId);
    }

    @Override
    public TaskboardStatus save(TaskboardStatus obj) {
        return this.taskboardStatusRepository.save(obj);
    }

    @Override
    public TaskboardStatus createStatus(Long taskboardId, String data, User loggedInUser) throws JSONException {
        JSONObject obj = new JSONObject(data);

        Taskboard taskboard = taskboardService.findById(taskboardId);
        TaskboardStatus taskboardStatus = new TaskboardStatus();

        taskboardStatus.setStatus(obj.get("status") == null? null: obj.get("status").toString());
        taskboardStatus.setTaskboard(taskboard);
        return save(taskboardStatus);
    }
}
