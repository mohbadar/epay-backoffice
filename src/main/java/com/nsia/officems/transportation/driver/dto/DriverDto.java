package com.nsia.officems.transportation.driver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverDto {

    private Long id;
    private String fullName;
    private String fatherName;
    private String address;
    private String dateOfBirth;
    private String licenseIssueDate;
    private String licenseExpiryDate;
    private String licenseClass;
    private String driverImage;
}
