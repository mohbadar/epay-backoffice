package com.nsia.officems.task_mng.task.task_comment.dto;

import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.task.task_comment.TaskComment;

public class TaskCommentMapper {

    public static TaskComment map(TaskCommentDto dto, TaskComment taskComment, TaskService taskService)
    {
        if(taskComment == null)
            taskComment = new TaskComment();

        taskComment.setComment(dto.getComment());
        taskComment.setTask(dto.getTaskId() == null ? null : taskService.findById(dto.getTaskId()));
        return taskComment;

    }
}
