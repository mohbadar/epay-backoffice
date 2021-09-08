package com.nsia.officems.task_mng.task.task_status;

import java.util.List;

import com.nsia.officems.task_mng.task.task_status.dto.TaskStatusDto;

public interface TaskStatusService {
    public TaskStatus save(TaskStatus visit);
    public List<TaskStatus> findAll();
    public TaskStatus findById(Long id);
    public List<TaskStatus> findByTaskId(Long taskId);
    public TaskStatus create(TaskStatus taskComment);
    public TaskStatus create(TaskStatusDto taskCommentDto);
    public Boolean delete(Long id);
    public boolean update(Long id, TaskStatus taskComment);
}
