package com.nsia.officems.task_mng.taskboard.taskboard_status;

import java.util.List;

import com.nsia.officems._identity.authentication.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskboardStatusRepository extends JpaRepository<TaskboardStatus, Long>  {
    public List<TaskboardStatus> findByTaskboardId(Long taskboardId);
}