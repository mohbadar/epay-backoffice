package com.nsia.officems.reception.visit_vehicle;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems.reception.vehicle.ReceptionVehicle;
import com.nsia.officems.reception.visit.Visit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author mohbadar
 */

@Getter
@Setter
@NoArgsConstructor
@Table(name = "recep_visit_vehicle")
@Entity(name = "VisitVehicle")
public class VisitVehicle {
    @EmbeddedId
    private VisitVehicleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("visitId")
    @JsonIgnore
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vehicleId")
    @JoinColumn(name = "vehicle_id")
    @JsonIgnore
    private ReceptionVehicle vehicle;

    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "entry_time")
    private Time entryTime;

    // DONE - CANCELED - REJECTED
    @Column
    private String status;

    public VisitVehicle(Visit visit, ReceptionVehicle vehicle, Date entryDate, Time entryTime, String status) {
        this.id = new VisitVehicleId(visit.getId(), vehicle.getId());
        this.visit = visit;
        this.vehicle = vehicle;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.status = status;
    }
}
