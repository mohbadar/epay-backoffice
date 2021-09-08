package com.nsia.officems.transportation.request.dto;
import java.lang.StackWalker.Option;
import java.util.Date;
import java.util.Optional;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.transportation.driver.DriverService;
import com.nsia.officems.transportation.request.Request;
import com.nsia.officems.transportation.vehicle.VehicleService;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class RequestMapper {
    // public static RequestDto map(String data) throws JSONException {
    //     JSONObject requestJson = new JSONObject(data);
    //     DateTimeChange changeDate = new DateTimeChange();
    //     RequestDto dto = new RequestDto();
    //     dto.setSource(requestJson.get("source") == null? null: requestJson.get("source").toString());
    //     dto.setDestination(requestJson.get("destination") == null? null: requestJson.get("destination").toString());
    //     dto.setPassengersInfo(requestJson.get("passengersInfo") == null? null: requestJson.get("passengersInfo").toString());
    //     dto.setRequestDate(requestJson.get("requestDate") == null? null: changeDate.convertPersianDateToGregorianDate(requestJson.get("requestDate").toString()));
    //     String requestTime = requestJson.get("requestTime") == null? null: requestJson.get("requestTime").toString();
    //     dto.setRequestTime(requestTime);
    //     dto.setDetails(requestJson.get("details") == null? null: requestJson.get("details").toString());
    //     dto.setPickupDate(requestJson.get("pickupDate") == null? null: changeDate.convertPersianDateToGregorianDate(requestJson.get("pickupDate").toString()));
    //     String pickupTime = requestJson.get("pickupTime") == null? null: requestJson.get("pickupTime").toString();
    //     dto.setPickupTime(pickupTime);
    //     dto.setReturneeDate(requestJson.get("returneeDate") == null? null: changeDate.convertPersianDateToGregorianDate(requestJson.get("returneeDate").toString()));
    //     String returneeTime = requestJson.get("returneeTime") == null? null: requestJson.get("returneeTime").toString();
    //     dto.setReturneeTime(returneeTime);

        // Long requestDepartment = requestJson.getLong("departmentId");
        // dto.setDepartmentId(requestDepartment);

        // if(requestJson.has("driverId") && !requestJson.isNull("driverId")){
        //     Long requestDriver = requestJson.getLong("driverId");
        //     dto.setDriverId(requestDriver);
        // }

        // if(requestJson.has("vehicleId") && !requestJson.isNull("vehicleId")){
        //     Long requestVehicle = requestJson.getLong("vehicleId");
        //     dto.setVehicleId(requestVehicle);
        // }



    //     return dto;
    // }

    public static Request map(RequestDto dto, Request request, DepartmentService departmentService, DriverService driverService, VehicleService vehicleService)
    {
        DateTimeChange changeDate = new DateTimeChange();

        if(request == null)
            request = new Request();
        request.setId(dto.getId());
        request.setPickupLocation(dto.getPickupLocation());
        request.setDropOffLocation(dto.getDropOffLocation());
        request.setHasReturn(dto.getHasReturn());

        String pickupDate = dto.getPickupDate();
        if(pickupDate != null) {
            request.setPickupDate(changeDate.convertPersianDateToGregorianDate(pickupDate));
        }
        request.setPickupTime(dto.getPickupTime());
        request.setReturnTime(dto.getReturnTime());

        request.setDetails(dto.getDetails());
        request.setStatus(dto.getStatus());
        request.setResolution(dto.getResolution());

        Long requestingDep = dto.getRequestingDepartmentId();
        if(requestingDep != null) {
            request.setRequestingDepartment(departmentService.findById(requestingDep));
        }

        Long driver = dto.getDriverId();
        if(driver != null) {
            request.setDriver(driverService.findById(driver));
        }

        Long vehicle = dto.getVehicleId();
        if(vehicle != null) {
            request.setVehicle(vehicleService.findById(vehicle));
        }
        
        return request;
    }

    // public static RequestDto map(Request request)
    // {
    //     RequestDto requestDto = new RequestDto();
    //     requestDto.setId(request.getId());
    //     requestDto.setSource(request.getSource());
    //     requestDto.setDestination(request.getDestination());
    //     requestDto.setPassengersInfo(request.getPassengersInfo());
    //     requestDto.setRequestDate(request.getRequestDate());
    //     requestDto.setRequestTime(request.getRequestTime());
    //     // requestDto.setDepartmentId(request.getApprovedBy().getId());
    //     requestDto.setStatus(request.getStatus());
    //     // requestDto.setDepartmentName(request.getApprovedBy().getNameDr());
    //     requestDto.setDetails(request.getDetails());
    //     requestDto.setPickupDate(request.getPickupDate());
    //     requestDto.setPickupTime(request.getPickupTime());
    //     requestDto.setDriverId(request.getDriver().getId());
    //     // requestDto.setDriverName(request.getDriver().getFullName());
    //     // requestDto.setVehicleId(request.getVehicle().getId());
    //     requestDto.setVehiclePlateNo(request.getVehicle().getPlateNo());
    //     requestDto.setReturneeDate(request.getReturneeDate());
    //     requestDto.setReturneeTime(request.getReturneeTime());
    //     return requestDto;
    // }

    public static RequestPrintDto MapRequestToRequestPrintDto (RequestPrintDto requestPrintDto, Request request,
        VehicleService vehicleService, DriverService driverService,   
        DepartmentService departmentService, UserService userservice)
        {
            DateTimeChange changeDate = new DateTimeChange();
            try {
                requestPrintDto.setId(request.getId());
                requestPrintDto.setPickupLocation(request.getPickupLocation());
                requestPrintDto.setDropOffLocation(request.getDropOffLocation());

                //requestPrintDto.setPickupDate(request.getPickupDate().toString());
                requestPrintDto.setPickupDate(request.getPickupDate() == null ? null
                : changeDate.convertGregorianDateToPersianDate(request.getPickupDate()));

                requestPrintDto.setPickupTime(request.getPickupTime());
                requestPrintDto.setReturnTime(request.getReturnTime());
                requestPrintDto.setDetails(request.getDetails());
                requestPrintDto.setRequestingDepartmentName(departmentService.findById(request.getRequestingDepartment().getId()).getNameDr());
                requestPrintDto.setEmployeeName(userservice.findById(request.getCreatedBy().getId()).getName()); //employee name ...
                requestPrintDto.setDriverName(driverService.findById(request.getDriver().getId()).getFullName());

                String vehicleDetails = 
                    vehicleService.findById(request.getVehicle().getId()).getModel() +" - "+ 
                    vehicleService.findById(request.getVehicle().getId()).getType() +" - "+
                    vehicleService.findById(request.getVehicle().getId()).getPlateNo() +" - "+
                    vehicleService.findById(request.getVehicle().getId()).getColour();
                    
                requestPrintDto.setVehicleDetails(vehicleDetails);
                requestPrintDto.setStartKM("");
                requestPrintDto.setEndKM("");

                return requestPrintDto;
            } catch (Exception e) {
                System.out.println("Exception occured in mapping");
                return null;
            }
        }



}
