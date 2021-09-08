package com.nsia.officems.task_mng.task.task_attachment.dto;

import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.task.task_attachment.TaskAttachment;

public class TaskAttachmentMapper {

    public static TaskAttachment map(TaskAttachmentDto dto, TaskAttachment taskAttachment, TaskService taskService)
    {
        if(taskAttachment == null)
            taskAttachment = new TaskAttachment();

        // taskAttachment.setDetails(dto.getDetails());
        taskAttachment.setTask(dto.getTaskId() == null ? null : taskService.findById(dto.getTaskId()));
        return taskAttachment;

    }
}
