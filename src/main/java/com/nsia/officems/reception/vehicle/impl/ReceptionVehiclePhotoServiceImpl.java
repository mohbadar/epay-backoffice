package com.nsia.officems.reception.vehicle.impl;

import com.nsia.officems.reception.vehicle.ReceptionVehicle;
import com.nsia.officems.reception.vehicle.ReceptionVehiclePhoto;
import com.nsia.officems.reception.vehicle.ReceptionVehiclePhotoRepository;
import com.nsia.officems.reception.vehicle.ReceptionVehiclePhotoService;
import com.nsia.officems.reception.vehicle.ReceptionVehicle;
import com.nsia.officems.reception.vehicle.ReceptionVehicleService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceptionVehiclePhotoServiceImpl implements ReceptionVehiclePhotoService {
    @Autowired
    private ReceptionVehiclePhotoRepository  vehiclePhotoRepository;

    public ReceptionVehiclePhoto findById(Long id) {
        System.out.println("vehiclePhotoRepository.findById()" + id);
        Optional<ReceptionVehiclePhoto> objection = vehiclePhotoRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("vehicle: " + objection);
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<ReceptionVehiclePhoto> document = vehiclePhotoRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public ReceptionVehiclePhoto save(ReceptionVehiclePhoto vehiclePhoto) {
        return this.vehiclePhotoRepository.save(vehiclePhoto);
    }
}
