package com.nsia.officems.task_mng.task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>  {
    List<Task> findByTaskboardIdAndDeletedFalse(Long taskboardId);
    List<Task> findByTaskboardIdAndDeletedFalseAndArchivedFalse(Long taskboardId);
    List<Task> findByParentId(Long taskId);

    @Query("SELECT new com.nsia.officems.task_mng.task.Task(t.id, t.title, t.description, t.status, t.createdBy) from Task t where t.parent is null and t.taskboard.id=?1 and t.deleted=false and t.archived=false")
    List<Task> findOrphanByTaskboardId(Long taskboardId);

    @Query(value = "select count(*) from public.taskmng_task", nativeQuery = true)
    public long countALL();

    @Query(value = "select count(*) from public.taskmng_task where date(created_at) = CURRENT_DATE", nativeQuery = true)
    public long countToday();

    @Query("SELECT t.taskboard.title as name , count(*) as count from Task t GROUP BY t.taskboard.title ORDER BY t.taskboard.title ASC")
    List getTaskCountByTaskBoard();

    @Query("SELECT t.status as name , count(*) as count from Task t GROUP BY t.status ORDER BY t.status ASC")
    List getTaskCountByStatus();



}