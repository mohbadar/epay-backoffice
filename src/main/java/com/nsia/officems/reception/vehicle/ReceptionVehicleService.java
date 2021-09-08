package com.nsia.officems.reception.vehicle;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface ReceptionVehicleService {
    public ReceptionVehicle save(ReceptionVehicle vehicle);
    public List<ReceptionVehicle> saveAll(List<ReceptionVehicle> vehicles);
    public ReceptionVehicle processVehicle(Long visitId, Long vehicleId, String data) throws JSONException, IOException ;
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public ReceptionVehicle findById(Long id);
    public List<ReceptionVehicle> findByVisitId(Long vehicleId);
    public boolean delete(long id);
    public ReceptionVehicle create(ReceptionVehicle vehicle, boolean isSchedule) throws JSONException, IOException;
    public ReceptionVehicle setAttributes(ReceptionVehicle vehicle, JSONObject vehicleJson) throws JSONException, IOException;
}
