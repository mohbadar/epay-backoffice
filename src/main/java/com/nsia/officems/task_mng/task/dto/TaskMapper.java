package com.nsia.officems.task_mng.task.dto;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems.task_mng.task.Task;
import com.nsia.officems.task_mng.task.TaskService;

public class TaskMapper {
    public static Task map(TaskDto dto, Task task, DepartmentService departmentService, TaskService taskService)
    {
        if(task == null)
            task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        return task;
    }
}
