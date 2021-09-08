package com.nsia.officems.transportation.vehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleDto {

    private Long id;
    private String vehicleClass;
    private String manufacturer;
    private String model;
    private String type;
    private String year;
    private String colour;
    private String plateNo;
}
