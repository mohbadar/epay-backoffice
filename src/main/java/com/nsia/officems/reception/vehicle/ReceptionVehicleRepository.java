package com.nsia.officems.reception.vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionVehicleRepository extends JpaRepository<ReceptionVehicle, Long>  {
    // List<Vehicle> findByVisits_Id(Long visitId);
    List<ReceptionVehicle> findAllByVisitVehicles_visitId(Long visitId);
}