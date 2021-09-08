package com.nsia.officems.reception.visitor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long>  {
    // List<Visitor> findByVisits_Id(Long visitId);
    List<Visitor> findAllByVisitVisitors_visitId(Long visitId);

    @Query(value = "select count(*) from public.recep_visitor", nativeQuery = true)
    public long countALL();

    @Query(value = "select count(*) from public.recep_visitor where date(created_at) = CURRENT_DATE", nativeQuery = true)
    public long countToday();
}