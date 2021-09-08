package com.nsia.officems.reception.visit_visitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitVisitorRepository extends JpaRepository<VisitVisitor, VisitVisitorId>  { }