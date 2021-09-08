package com.nsia.officems.task_mng.task.task_attachment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long>  {
    List<TaskAttachment> findByTaskId(Long taskId);
}