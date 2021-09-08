package com.nsia.officems.reception.vehicle;

public interface ReceptionVehiclePhotoService {
    public ReceptionVehiclePhoto save(ReceptionVehiclePhoto vehicle);
    public ReceptionVehiclePhoto findById(Long id);
    public boolean delete(long id);
}
