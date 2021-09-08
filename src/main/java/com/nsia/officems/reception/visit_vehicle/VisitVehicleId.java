package com.nsia.officems.reception.visit_vehicle;

import lombok.AllArgsConstructor;  
import lombok.Data;  
import lombok.NoArgsConstructor;

import javax.persistence.Column;  
import javax.persistence.Embeddable;  
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VisitVehicleId implements Serializable {  
    @Column(name = "visit_id")
    private Long visitId;

    @Column(name = "vehicle_id")
    private Long vehicleId;
}