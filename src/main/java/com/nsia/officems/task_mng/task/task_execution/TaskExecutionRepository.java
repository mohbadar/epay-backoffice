package com.nsia.officems.task_mng.task.task_execution;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskExecutionRepository extends JpaRepository<TaskExecution, Long>  {
    List<TaskExecution> findByTaskId(Long taskId);
}