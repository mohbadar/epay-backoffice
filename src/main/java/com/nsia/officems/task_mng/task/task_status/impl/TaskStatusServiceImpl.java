package com.nsia.officems.task_mng.task.task_status.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.task.task_status.TaskStatus;
import com.nsia.officems.task_mng.task.task_status.TaskStatusRepository;
import com.nsia.officems.task_mng.task.task_status.TaskStatusService;
import com.nsia.officems.task_mng.task.task_status.dto.TaskStatusDto;
import com.nsia.officems.task_mng.task.task_status.dto.TaskStatusMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {
    private final TaskStatusRepository taskCommentRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private DataTablesUtil dataTablesUtil;

    public TaskStatusServiceImpl(TaskStatusRepository taskCommentRepository) {
        this.taskCommentRepository = taskCommentRepository;
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " ";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visitor vor", null, joinClause, whereClause, groupByClause, input);
    }

    public TaskStatus findById(Long id) {
        System.out.println(" TaskStatus.findById()" + id);
        Optional<TaskStatus> taskComment = taskCommentRepository.findById(id);
        if (taskComment.isPresent()) {
            System.out.println("TaskStatus: " + taskComment);
            return taskComment.get();
        }
        return null;
    }

    public List<TaskStatus> findByTaskId(Long taskId) {
        return taskCommentRepository.findByTaskId(taskId);
    }

    public boolean delete(long id) {
        Optional<TaskStatus> taskComment = taskCommentRepository.findById(id);
        if (taskComment.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public List<TaskStatus> findAll() {
        return taskCommentRepository.findAll();
    }

    @Override
    public TaskStatus save(TaskStatus taskComment) {
        return taskCommentRepository.save(taskComment);
    }

    @Override
    public TaskStatus create(TaskStatus taskComment) {
        return save(taskComment);
    }

    @Override
    public TaskStatus create(TaskStatusDto taskCommentDto) {
        TaskStatus taskComment = TaskStatusMapper.map(taskCommentDto, new TaskStatus(), taskService);
        taskComment.setCreatedBy(userService.getLoggedInUser());
        return create(taskComment);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, TaskStatus taskComment) {
        return false;
    }


}
