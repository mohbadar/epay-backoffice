package com.nsia.officems.reception.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionVehiclePhotoRepository extends JpaRepository<ReceptionVehiclePhoto, Long>  {
    
}