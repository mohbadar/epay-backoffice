package com.nsia.officems.transportation.vehicle.dto;

import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.transportation.driver.Driver;
import com.nsia.officems.transportation.driver.dto.DriverDto;
import com.nsia.officems.transportation.vehicle.Vehicle;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class VehicleMapper {

    public static VehicleDto map(String data) throws JSONException {
        JSONObject requestJson = new JSONObject(data);
        DateTimeChange changeDate = new DateTimeChange();
        VehicleDto dto = new VehicleDto();
        dto.setVehicleClass(requestJson.get("vehicleClass") == null? null: requestJson.get("vehicleClass").toString());
        dto.setManufacturer(requestJson.get("manufacturer") == null? null: requestJson.get("manufacturer").toString());
        dto.setModel(requestJson.get("model")==null? null: requestJson.get("model").toString());
        dto.setType(requestJson.get("type")==null? null: requestJson.get("type").toString());
        dto.setYear(requestJson.get("year")==null? null: requestJson.get("year").toString());
        dto.setColour(requestJson.get("colour")==null? null: requestJson.get("colour").toString());
        dto.setPlateNo(requestJson.get("plateNo")==null? null: requestJson.get("plateNo").toString());
        return dto;
    }

    public static Vehicle map(VehicleDto dto, Vehicle vehicle)
    {
        if(vehicle == null)
            vehicle = new Vehicle();
            vehicle.setId(dto.getId());
            vehicle.setVehicleClass(dto.getVehicleClass());
            vehicle.setManufacturer(dto.getManufacturer());
            vehicle.setModel(dto.getModel());
            vehicle.setType(dto.getType());
            vehicle.setYear(dto.getYear());
            vehicle.setColour(dto.getColour());
            vehicle.setPlateNo(dto.getPlateNo());

        return vehicle;
    }

    public static VehicleDto map(Vehicle vehicle){

        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setVehicleClass(vehicle.getVehicleClass());
        dto.setManufacturer(vehicle.getManufacturer());
        dto.setModel(vehicle.getModel());
        dto.setType(vehicle.getType());
        dto.setYear(vehicle.getYear());
        dto.setColour(vehicle.getColour());
        dto.setPlateNo(vehicle.getPlateNo());
        return dto;

    }
}
