package com.nsia.officems.task_mng.taskboard;

import java.util.List;

import com.nsia.officems._identity.authentication.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskboardRepository extends JpaRepository<Taskboard, Long>  {
    List<Taskboard> findByCreatedBy(User user);
    public List<Taskboard> findByMembers_Id(Long userId);
    public List<Taskboard> findByIsPublic(Boolean flag);

    @Query(value = "select count(*) from public.taskmng_taskboard", nativeQuery = true)
    public long countALL();

    @Query(value = "select count(*) from public.taskmng_taskboard where date(created_at) = CURRENT_DATE", nativeQuery = true)
    public long countToday();
}