package com.nsia.officems.transportation.driver.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.transportation.driver.Driver;
import com.nsia.officems.transportation.request.Request;
import com.nsia.officems.transportation.request.dto.RequestDto;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Date;

public class DriverMapper {
    /*
    public static DriverDto map(String data) throws JSONException {
        JSONObject requestJson = new JSONObject(data);
        DateTimeChange changeDate = new DateTimeChange();
        DriverDto dto = new DriverDto();
        dto.setFullName(requestJson.get("fullName") == null? null: requestJson.get("fullName").toString());
        dto.setFullName(requestJson.get("fatherName") == null? null: requestJson.get("fatherName").toString());
        dto.setAddress(requestJson.get("address") == null? null: requestJson.get("address").toString());
        dto.setDateOfBirth(requestJson.get("dateOfBirth") == null? null: changeDate.convertPersianDateToGregorianDate(requestJson.get("dateOfBirth").toString()));
        dto.setLicenseIssueDate(requestJson.get("licenseIssueDate")==null?null: changeDate.convertPersianDateToGregorianDate(requestJson.get("licenseIssueDate").toString()));
        dto.setLicenseExpiryDate(requestJson.get("licenseExpiryDate")==null?null: changeDate.convertPersianDateToGregorianDate(requestJson.get("licenseExpiryDate").toString()));
        dto.setLicenseClass(requestJson.get("licenseClass")==null?null: requestJson.get("licenseClass").toString());
        dto.setDriverImage(requestJson.get("driverImage")==null?null: requestJson.get("driverImage").toString());
        return dto;
    }
    */




    public static Driver map(DriverDto dto, Driver driver)
    {
        DateTimeChange changeDate = new DateTimeChange();
        if(driver == null)
            driver = new Driver();
            driver.setId(dto.getId());
            driver.setFullName(dto.getFullName());
            driver.setAddress(dto.getAddress());

            String dateOfBirth=dto.getDateOfBirth();
            if(dateOfBirth != null) {
                driver.setDateOfBirth(changeDate.convertPersianDateToGregorianDate(dateOfBirth));
            }

            String licenseIssueDate=dto.getLicenseIssueDate();
            if(licenseIssueDate!=null){
                driver.setLicenseIssueDate(changeDate.convertPersianDateToGregorianDate(dateOfBirth));
            }

            String licesneExpiryDate=dto.getLicenseExpiryDate();
            if(licesneExpiryDate!=null){
                driver.setLicenseExpiryDate(changeDate.convertPersianDateToGregorianDate(licesneExpiryDate));
            }
            driver.setLicenseClass(dto.getLicenseClass());
            driver.setDriverImage(dto.getDriverImage());
        return driver;
    }

    /*
    public static DriverDto map(Driver driver)
    {
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setFullName(driver.getFullName());
        driverDto.setAddress(driver.getAddress());
        driverDto.setDateOfBirth(driver.getDateOfBirth());
        driverDto.setLicenseIssueDate(driver.getLicenseIssueDate());
        driverDto.setLicenseExpiryDate(driver.getLicenseExpiryDate());
        driverDto.setLicenseClass(driver.getLicenseClass());
        driverDto.setDriverImage(driver.getDriverImage());
        return driverDto;
    }
    */



}
