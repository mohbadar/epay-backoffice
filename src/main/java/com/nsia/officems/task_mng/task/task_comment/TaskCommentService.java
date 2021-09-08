package com.nsia.officems.task_mng.task.task_comment;

import java.util.List;

import com.nsia.officems.task_mng.task.task_comment.dto.TaskCommentDto;

public interface TaskCommentService {
    public TaskComment save(TaskComment visit);
    public List<TaskComment> findAll();
    public TaskComment findById(Long id);
    public List<TaskComment> findByTaskId(Long taskId);
    public TaskComment create(TaskComment taskComment);
    public TaskComment create(TaskCommentDto taskCommentDto);
    public Boolean delete(Long id);
    public boolean update(Long id, TaskComment taskComment);
}
