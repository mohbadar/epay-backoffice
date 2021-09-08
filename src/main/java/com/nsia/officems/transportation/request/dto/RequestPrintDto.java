package com.nsia.officems.transportation.request.dto;

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

public class RequestPrintDto {
    private Long id;
	private String pickupLocation;
    private String dropOffLocation;
    private String pickupDate;
    private String pickupTime;
    private String returnTime;
    private String details;
    private String requestingDepartmentName;
    private String employeeName;
    private String driverName;
    private String vehicleDetails;
    private String startKM;
    private String endKM;
    private String logoUri;
    private String language;
}
