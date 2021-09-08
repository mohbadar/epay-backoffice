package com.nsia.officems.task_mng.task.task_execution;

import java.io.File;
import java.util.List;

import com.nsia.officems.task_mng.task.task_execution.dto.TaskExecutionDto;

import org.springframework.web.multipart.MultipartFile;

public interface TaskExecutionService {
    public TaskExecution save(TaskExecution visit);
    public List<TaskExecution> findAll();
    public TaskExecution findById(Long id);
    public List<TaskExecution> findByTaskId(Long taskId);
    public TaskExecution create(TaskExecution taskExecution);
    public TaskExecution create(TaskExecutionDto taskExecutionDto);
    public TaskExecution create(TaskExecutionDto taskExecutionDto, MultipartFile file);
    public Boolean delete(Long id);
    public boolean update(Long id, TaskExecution taskExecution);
    public File downloadAttachment(Long id) throws Exception;
}
