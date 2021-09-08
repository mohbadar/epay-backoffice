package com.nsia.officems.reception.visit_vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitVehicleRepository extends JpaRepository<VisitVehicle, VisitVehicleId>  {
    
}