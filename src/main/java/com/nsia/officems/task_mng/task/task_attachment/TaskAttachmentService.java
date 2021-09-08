package com.nsia.officems.task_mng.task.task_attachment;

import java.io.File;
import java.util.List;

import com.nsia.officems.task_mng.task.task_attachment.dto.TaskAttachmentDto;

import org.springframework.web.multipart.MultipartFile;

public interface TaskAttachmentService {
    public TaskAttachment save(TaskAttachment visit);
    public List<TaskAttachment> findAll();
    public TaskAttachment findById(Long id);
    public List<TaskAttachment> findByTaskId(Long taskId);
    public TaskAttachment create(long taskId, MultipartFile file);
    public TaskAttachment create(TaskAttachmentDto taskAttachmentDto);
    public Boolean delete(Long id);
    public boolean update(Long id, TaskAttachment taskAttachment);
    public File downloadAttachment(Long id) throws Exception;
}
