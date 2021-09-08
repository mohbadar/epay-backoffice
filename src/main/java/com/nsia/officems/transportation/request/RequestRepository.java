package com.nsia.officems.transportation.request;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>  {

    @Query(value = "select count(*) from public.transport_request", nativeQuery = true)
    public long countALL();

    @Query(value = "select count(*) from public.transport_request where date(created_at) = CURRENT_DATE", nativeQuery = true)
    public long countToday();

    @Query("SELECT r.requestingDepartment.nameDr as name , count(*) as count from Request r GROUP BY r.requestingDepartment.nameDr ORDER BY r.requestingDepartment.nameDr ASC")
    List getRequestCountByDepartment();

    @Query("SELECT r.status as name , count(*) as count from Request r GROUP BY r.status ORDER BY r.status ASC")
    List getRequestCountByStatus();



}