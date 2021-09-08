package com.nsia.officems.transportation.request.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RequestDto {
    private Long id;
	private String pickupLocation;
    private String dropOffLocation;
    // False: One Side ----  True: Two Side
    private Boolean hasReturn;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String pickupDate;
    private String pickupTime;
    private String returnTime;
    private String details;
    // PENDING - CLOSED
    private String status;
    // COMPLETED - REJECTED - CANCELED
    private String resolution;

    private String processedComment;

    private Long requestingDepartmentId;
    private String requestingDepartmentName;
    private Long driverId;
    private String driverName;
    private Long vehicleId;
    private String vehiclePlateNo;
}
