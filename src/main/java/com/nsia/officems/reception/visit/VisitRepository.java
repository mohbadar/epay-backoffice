package com.nsia.officems.reception.visit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long>  {
    @Query(value = "select count(*) from public.recep_visit", nativeQuery = true)
    public long countALL();

    @Query(value = "select count(*) from public.recep_visit where date(created_at) = CURRENT_DATE", nativeQuery = true)
    public long countToday();

    @Query("SELECT s.department.nameDr as name , count(*) as count from Visit s GROUP BY s.department.nameDr ORDER BY s.department.nameDr ASC") 
    List getVisitCountByDepartment();

    @Query("SELECT s.type as name , count(*) as count from Visit s GROUP BY s.type ORDER BY s.type ASC") 
    List getVisitCountByType();
}