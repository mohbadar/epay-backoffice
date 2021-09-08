package com.nsia.officems.reception.vehicle.impl;

import com.nsia.officems.reception.vehicle.ReceptionVehicle;
import com.nsia.officems.reception.vehicle.ReceptionVehiclePhoto;
import com.nsia.officems.reception.vehicle.ReceptionVehiclePhotoService;
import com.nsia.officems.reception.vehicle.ReceptionVehicleRepository;
import com.nsia.officems.reception.vehicle.ReceptionVehicleService;
import com.nsia.officems.reception.visit_vehicle.VisitVehicle;
import com.nsia.officems.reception.visit_vehicle.VisitVehicleId;
import com.nsia.officems.reception.visit_vehicle.VisitVehicleRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.DateTimeChange;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class ReceptionVehicleServiceImpl implements ReceptionVehicleService {
    @Value(value = "${app.upload.reception.vehicles}")
    private String vehiclePhotosPath;
    
    private final DateTimeChange changeDate = new DateTimeChange(); 
    
    @Autowired
    private ReceptionVehiclePhotoService vehiclePhotoService;
    
    @Autowired
    private ReceptionVehicleRepository  vehicleRepository;

    @Autowired
    private VisitVehicleRepository  visitVehicleRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join recep_visit_vehicle rvveh on rvveh.vehicle_id=veh.id";
        joinClause += " inner join recep_visit v on v.id=rvveh.visit_id";
        joinClause += " inner join public.department d on d.id=v.host_department_id";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_vehicle veh", null, joinClause, whereClause, groupByClause, input);
    }

    public ReceptionVehicle findById(Long id) {
        System.out.println(" Vehicle.findById()" + id);
        Optional<ReceptionVehicle> objection = vehicleRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Vehicle: " + objection);
            return objection.get();
        }
        return null;
    }

    public List<ReceptionVehicle> findByVisitId(Long visitId) {
        return vehicleRepository.findAllByVisitVehicles_visitId(visitId);
    }

    public boolean delete(long id) {
        Optional<ReceptionVehicle> document = vehicleRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public ReceptionVehicle save(ReceptionVehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    @Override
    public List<ReceptionVehicle> saveAll(List<ReceptionVehicle> vehicles) {
        return vehicleRepository.saveAll(vehicles);
    }

    @Override
    public ReceptionVehicle processVehicle(Long visitId, Long vehicleId, String data) throws JSONException, IOException {
        ReceptionVehicle vehicle = findById(vehicleId);
        VisitVehicleId visitVehicleId = new VisitVehicleId(visitId, vehicleId);
        VisitVehicle visitVehicle = visitVehicleRepository.getOne(visitVehicleId);

        JSONObject vehicleJson = new JSONObject(data);
        vehicle = setPhotoAttribute(vehicle, vehicleJson);
        ReceptionVehiclePhoto vehiclePhoto = vehicle.getVehiclePhoto();
        vehicle.setVehiclePhoto(null);

        vehiclePhoto = createPhoto(vehiclePhoto, vehicle);
        vehicle.setVehiclePhoto(vehiclePhoto);
        vehicle = save(vehicle);

        // Date currentDate = new Date();
        // LocalTime time = LocalTime.now();
        visitVehicle.setEntryDate(vehicleJson.get("entryDate") == null? null: changeDate.convertPersianDateToGregorianDate(vehicleJson.get("entryDate").toString()));
        String entryTime = vehicleJson.get("entryTime") == null? null: vehicleJson.get("entryTime").toString();
        if(entryTime != null) {
            // HH: 00 - 23
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.parse(entryTime, timeFormatter);
            visitVehicle.setEntryTime(Time.valueOf(time));
        }
        visitVehicle.setStatus(vehicleJson.get("status") == null? null: vehicleJson.get("status").toString());
        visitVehicleRepository.save(visitVehicle);
        return vehicle;
    }

    @Override
    public ReceptionVehicle create(ReceptionVehicle vehicle, boolean isSchedule) throws JSONException, IOException {
        ReceptionVehiclePhoto vehiclePhoto = vehicle.getVehiclePhoto();
        vehicle.setVehiclePhoto(null);
        vehicle = save(vehicle);

        if(vehiclePhoto != null) {
            vehiclePhoto = createPhoto(vehiclePhoto, vehicle);
            vehicle.setVehiclePhoto(vehiclePhoto);
        }

        return save(vehicle);
    }

    public ReceptionVehiclePhoto createPhoto(ReceptionVehiclePhoto vehiclePhoto, ReceptionVehicle vehicle) throws JSONException, IOException {
        // move the vehicle photo form temp to correct directory
        Path fileToMovePath = Paths.get(new File(vehiclePhoto.getPath()).getAbsolutePath());
        String fileNewName = vehicle.getId() + "_" + vehiclePhoto.getName();
        String fileNewPath = vehiclePhotosPath + File.separator + fileNewName;
        Path targetPath = Paths.get(fileNewPath);
        Files.move(fileToMovePath, targetPath);

        vehiclePhoto.setName(fileNewName);
        vehiclePhoto.setPath(fileNewPath);
        vehiclePhoto.setVehicle(vehicle);
        return vehiclePhotoService.save(vehiclePhoto);
    }

    @Override
    public ReceptionVehicle setAttributes(ReceptionVehicle vehicle, JSONObject vehicleJson) throws JSONException, IOException {
        vehicle.setDriverName(vehicleJson.get("driverName") == null? null: vehicleJson.get("driverName").toString());
        vehicle.setColor(vehicleJson.get("vehicleColor") == null? null: vehicleJson.get("vehicleColor").toString());
        vehicle.setModal(vehicleJson.get("vehicleModal") == null? null: vehicleJson.get("vehicleModal").toString());
        vehicle.setPlateNo(vehicleJson.get("vehiclePlateNo") == null? null: vehicleJson.get("vehiclePlateNo").toString());
        vehicle.setStatus(true);

        vehicle = setPhotoAttribute(vehicle, vehicleJson);

        return vehicle;
    }

    public ReceptionVehicle setPhotoAttribute(ReceptionVehicle vehicle, JSONObject vehicleJson) throws JSONException, IOException {
        String photo = vehicleJson.get("photo") == null? null: vehicleJson.get("photo").toString();
        if(photo != null && !photo.equals("")) {
            String fileExtention = "";
            if (StringUtils.isBlank(photo)) {
                throw new JSONException("Photo is not selected");
            } else if (photo.indexOf("data:image/png;") != -1) {
                photo = photo.replace("data:image/png;base64,", "");
                fileExtention = ".png";
            } else if (photo.indexOf("data:image/jpeg;") != -1) {
                photo = photo.replace("data:image/jpeg;base64,", "");
                fileExtention = ".jpeg";
            } else {
                throw new JSONException("Wrong Photo type is selected");
            }

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            //Formatted LocalDateTime : 2021-03-14T17:45:55.9483536
            String fileName = localDateTime.format(formatter);
            fileName = fileName.replace(":", "_");

            // String tempFileName = "";
            String mimeType = "";
            File tempFile = null;
            try {
                // tempFileName = UUID.randomUUID().toString();
                tempFile = File.createTempFile(fileName, fileExtention);
                FileOutputStream fos = new FileOutputStream(tempFile);

                byte[] fileBytes = Base64.getDecoder().decode(photo);
                fos.write(fileBytes);
                fos.close();

                mimeType = Files.probeContentType(tempFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Failed to Upload the photo");
            }

            // List<VehiclePhoto> vehiclePhotos = new ArrayList<VehiclePhoto>();
            ReceptionVehiclePhoto vehiclePhoto = new ReceptionVehiclePhoto();
            vehiclePhoto.setName(fileName + fileExtention);
            vehiclePhoto.setPath(tempFile.getAbsolutePath());
            vehiclePhoto.setMimeType(mimeType);
            vehiclePhoto.setIsDefault(true);
            vehiclePhoto.setVehicle(vehicle);

            // vehiclePhotos.add(vehiclePhoto);
            // vehicle.setVehiclePhotos(vehiclePhotos);
            vehicle.setVehiclePhoto(vehiclePhoto);
        }
        return vehicle;
    }
}
