package com.nsia.officems.reception.visit.dto;
import java.util.List;

import com.nsia.officems.reception.vehicle.ReceptionVehicle;
import com.nsia.officems.reception.visit.Visit;
import com.nsia.officems.reception.visit_vehicle.VisitVehicle;
import com.nsia.officems.reception.visit_visitor.VisitVisitor;
import com.nsia.officems.reception.visitor.Visitor;
import com.nsia.officems.transportation.vehicle.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MyVisitsPrintDto {
    private VisitDto visit;
    private List<Visitor> visitors;
    private List<ReceptionVehicle> vehicles;
    private String logoUri;
    private String language;
}
