package com.nsia.officems.reception.visitor.impl;

import com.nsia.officems.reception.visit_visitor.VisitVisitor;
import com.nsia.officems.reception.visit_visitor.VisitVisitorId;
import com.nsia.officems.reception.visit_visitor.VisitVisitorRepository;
import com.nsia.officems.reception.visitor.Visitor;
import com.nsia.officems.reception.visitor.VisitorPhoto;
import com.nsia.officems.reception.visitor.VisitorPhotoService;
import com.nsia.officems.reception.visitor.VisitorRepository;
import com.nsia.officems.reception.visitor.VisitorService;

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
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
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
public class VisitorServiceImpl implements VisitorService {
    @Value(value = "${app.upload.reception.visitors}")
    private String visitorPhotosPath;

    private final DateTimeChange changeDate = new DateTimeChange(); 
    
    @Autowired
    private VisitorPhotoService visitorPhotoService;
    
    @Autowired
    private VisitorRepository  visitorRepository;

    @Autowired
    private VisitVisitorRepository  visitVisitorRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public long countALL(){
        return visitorRepository.countALL();
    }
    public long countToday(){
        return visitorRepository.countToday();
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join recep_visit_visitor rvvor on rvvor.visitor_id=vor.id";
        joinClause += " inner join recep_visit v on v.id=rvvor.visit_id";
        joinClause += " inner join public.department d on d.id=v.host_department_id";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visitor vor", null, joinClause, whereClause, groupByClause, input);
    }

    public Visitor findById(Long id) {
        System.out.println(" Visitor.findById()" + id);
        Optional<Visitor> objection = visitorRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Visitor: " + objection);
            return objection.get();
        }
        return null;
    }

    public List<Visitor> findByVisitId(Long visitId) {
        return visitorRepository.findAllByVisitVisitors_visitId(visitId);
    }

    public boolean delete(long id) {
        Optional<Visitor> document = visitorRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Visitor save(Visitor visitor) {
        return this.visitorRepository.save(visitor);
    }

    @Override
    public List<Visitor> saveAll(List<Visitor> visitor) {
        return visitorRepository.saveAll(visitor);
    }

    @Override
    public Visitor processVisitor(Long visitId, Long visitorId, String data) throws JSONException, IOException {
        Visitor visitor = findById(visitorId);
        VisitVisitorId visitVisitorId = new VisitVisitorId(visitId, visitorId);
        VisitVisitor visitVisitor = visitVisitorRepository.getOne(visitVisitorId);

        JSONObject visitorJson = new JSONObject(data);
        visitor = setPhotoAttribute(visitor, visitorJson);
        VisitorPhoto visitorPhoto = visitor.getDefaultPhoto();
        visitor.setDefaultPhoto(null);

        visitorPhoto = createPhoto(visitorPhoto, visitor);
        visitor.setDefaultPhoto(visitorPhoto);
        visitor = save(visitor);

        // Date currentDate = new Date();
        // LocalTime time = LocalTime.now();
        visitVisitor.setEntryDate(visitorJson.get("entryDate") == null? null: changeDate.convertPersianDateToGregorianDate(visitorJson.get("entryDate").toString()));
        String entryTime = visitorJson.get("entryTime") == null? null: visitorJson.get("entryTime").toString();
        if(entryTime != null) {
            // HH: 00 - 23
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.parse(entryTime, timeFormatter);
            visitVisitor.setEntryTime(Time.valueOf(time));
        }
        visitVisitor.setStatus(visitorJson.get("status") == null? null: visitorJson.get("status").toString());
        visitVisitorRepository.save(visitVisitor);
        return visitor;
    }

    @Override
    public Visitor create(Visitor visitor, boolean isSchedule) throws JSONException, IOException {
        VisitorPhoto visitorPhoto = visitor.getDefaultPhoto();
        visitor.setDefaultPhoto(null);
        visitor = save(visitor);

        if(visitorPhoto != null) {
            visitorPhoto = createPhoto(visitorPhoto, visitor);
            visitor.setDefaultPhoto(visitorPhoto);
        }
        
        return save(visitor);
    }

    public VisitorPhoto createPhoto(VisitorPhoto visitorPhoto, Visitor visitor) throws JSONException, IOException {
        // move the visitor photo form temp to correct directory
        Path fileToMovePath = Paths.get(new File(visitorPhoto.getPath()).getAbsolutePath());
        String fileNewName = visitor.getId() + "_" + visitorPhoto.getName();
        String fileNewPath = visitorPhotosPath + File.separator + fileNewName;
        Path targetPath = Paths.get(fileNewPath);
        Files.move(fileToMovePath, targetPath);

        visitorPhoto.setName(fileNewName);
        visitorPhoto.setPath(fileNewPath);
        visitorPhoto.setVisitor(visitor);
        return visitorPhotoService.save(visitorPhoto);
    }

    @Override
    public Visitor setAttributes(Visitor visitor, JSONObject visitorJson) throws JSONException, IOException {
        visitor.setFirstName(visitorJson.get("firstName") == null? null: visitorJson.get("firstName").toString());
        visitor.setLastName(visitorJson.get("lastName") == null? null: visitorJson.get("lastName").toString());
        visitor.setFatherName(visitorJson.get("fatherName") == null? null: visitorJson.get("fatherName").toString());
        visitor.setPhoneNo(visitorJson.get("phone") == null? null: visitorJson.get("phone").toString());
        visitor.setAddress(visitorJson.get("address") == null? null: visitorJson.get("address").toString());
        visitor.setEmail(visitorJson.get("email") == null? null: visitorJson.get("email").toString());
        visitor.setTazkiraNo(visitorJson.get("tazkira") == null? null: visitorJson.get("tazkira").toString());
        visitor.setDesignation(visitorJson.get("designation") == null? null: visitorJson.get("designation").toString());
        visitor.setStatus(true);

        visitor = setPhotoAttribute(visitor, visitorJson);

        return visitor;
    }

    public Visitor setPhotoAttribute(Visitor visitor, JSONObject visitorJson) throws JSONException, IOException {
        String photo = visitorJson.get("photo") == null? null: visitorJson.get("photo").toString();
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

            // List<VisitorPhoto> visitorPhotos = new ArrayList<VisitorPhoto>();
            VisitorPhoto visitorPhoto = new VisitorPhoto();
            visitorPhoto.setName(fileName + fileExtention);
            visitorPhoto.setPath(tempFile.getAbsolutePath());
            visitorPhoto.setMimeType(mimeType);
            visitorPhoto.setIsDefault(true);
            visitorPhoto.setVisitor(visitor);

            // visitorPhotos.add(visitorPhoto);
            // visitor.setVisitorPhotos(visitorPhotos);
            visitor.setDefaultPhoto(visitorPhoto);
        }
        return visitor;
    }
}
