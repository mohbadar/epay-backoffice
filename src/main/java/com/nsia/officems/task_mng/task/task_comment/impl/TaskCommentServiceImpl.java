package com.nsia.officems.task_mng.task.task_comment.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.task.task_comment.TaskComment;
import com.nsia.officems.task_mng.task.task_comment.TaskCommentRepository;
import com.nsia.officems.task_mng.task.task_comment.TaskCommentService;
import com.nsia.officems.task_mng.task.task_comment.dto.TaskCommentDto;
import com.nsia.officems.task_mng.task.task_comment.dto.TaskCommentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class TaskCommentServiceImpl implements TaskCommentService {
    private final TaskCommentRepository taskCommentRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private DataTablesUtil dataTablesUtil;

    public TaskCommentServiceImpl(TaskCommentRepository taskCommentRepository) {
        this.taskCommentRepository = taskCommentRepository;
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " ";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visitor vor", null, joinClause, whereClause, groupByClause, input);
    }

    public TaskComment findById(Long id) {
        System.out.println(" TaskComment.findById()" + id);
        Optional<TaskComment> taskComment = taskCommentRepository.findById(id);
        if (taskComment.isPresent()) {
            System.out.println("TaskComment: " + taskComment);
            return taskComment.get();
        }
        return null;
    }

    public List<TaskComment> findByTaskId(Long taskId) {
        return taskCommentRepository.findByTaskId(taskId);
    }

    public boolean delete(long id) {
        Optional<TaskComment> taskComment = taskCommentRepository.findById(id);
        if (taskComment.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public List<TaskComment> findAll() {
        return taskCommentRepository.findAll();
    }

    @Override
    public TaskComment save(TaskComment taskComment) {
        return taskCommentRepository.save(taskComment);
    }

    @Override
    public TaskComment create(TaskComment taskComment) {
        return save(taskComment);
    }

    @Override
    public TaskComment create(TaskCommentDto taskCommentDto) {
        TaskComment taskComment = TaskCommentMapper.map(taskCommentDto, new TaskComment(), taskService);
        taskComment.setCreatedBy(userService.getLoggedInUser());
        return create(taskComment);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, TaskComment taskComment) {
        return false;
    }


}
