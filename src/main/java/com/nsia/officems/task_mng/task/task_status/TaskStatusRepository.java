package com.nsia.officems.task_mng.task.task_status;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long>  {
    List<TaskStatus> findByTaskId(Long taskId);
}