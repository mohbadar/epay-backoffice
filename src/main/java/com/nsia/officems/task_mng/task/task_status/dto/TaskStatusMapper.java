package com.nsia.officems.task_mng.task.task_status.dto;

import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.task.task_status.TaskStatus;

public class TaskStatusMapper {

    public static TaskStatus map(TaskStatusDto dto, TaskStatus taskComment, TaskService taskService)
    {
        if(taskComment == null)
            taskComment = new TaskStatus();

        taskComment.setTask(dto.getTaskId() == null ? null : taskService.findById(dto.getTaskId()));
        return taskComment;

    }
}
