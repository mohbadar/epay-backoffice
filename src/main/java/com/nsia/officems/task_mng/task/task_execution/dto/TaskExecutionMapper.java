package com.nsia.officems.task_mng.task.task_execution.dto;

import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.task.task_execution.TaskExecution;

public class TaskExecutionMapper {

    public static TaskExecution map(TaskExecutionDto dto, TaskExecution taskExecution, TaskService taskService)
    {
        if(taskExecution == null)
            taskExecution = new TaskExecution();

        taskExecution.setDetails(dto.getDetails());
        taskExecution.setProgress(dto.getProgress());
        taskExecution.setTask(dto.getTaskId() == null ? null : taskService.findById(dto.getTaskId()));
        return taskExecution;

    }
}
