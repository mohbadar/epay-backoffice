package com.nsia.officems.task_mng.taskboard.dto;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems.task_mng.taskboard.Taskboard;
import com.nsia.officems.task_mng.taskboard.TaskboardService;

public class TaskboardMapper {
    public static Taskboard map(TaskboardDto dto, Taskboard taskboard, DepartmentService departmentService, TaskboardService taskboardService)
    {
        if(taskboard == null)
            taskboard = new Taskboard();
        taskboard.setTitle(dto.getTitle());
        taskboard.setDescription(dto.getDescription());
        return taskboard;
    }
}
