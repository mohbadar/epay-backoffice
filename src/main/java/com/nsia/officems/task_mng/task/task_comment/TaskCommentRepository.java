package com.nsia.officems.task_mng.task.task_comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment, Long>  {
    List<TaskComment> findByTaskId(Long taskId);
}