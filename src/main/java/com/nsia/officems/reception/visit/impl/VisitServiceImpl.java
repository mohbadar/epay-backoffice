package com.nsia.officems.reception.visit.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.reception.vehicle.ReceptionVehicle;
import com.nsia.officems.reception.vehicle.ReceptionVehiclePhotoService;
import com.nsia.officems.reception.vehicle.ReceptionVehicleRepository;
import com.nsia.officems.reception.vehicle.ReceptionVehicleService;
import com.nsia.officems.reception.visit.Visit;
import com.nsia.officems.reception.visit.VisitRepository;
import com.nsia.officems.reception.visit.VisitService;
import com.nsia.officems.reception.visit.dto.VisitDto;
import com.nsia.officems.reception.visit.dto.VisitMapper;
import com.nsia.officems.reception.visit_vehicle.VisitVehicle;
import com.nsia.officems.reception.visit_vehicle.VisitVehicleRepository;
import com.nsia.officems.reception.visit_visitor.VisitVisitor;
import com.nsia.officems.reception.visit_visitor.VisitVisitorRepository;
import com.nsia.officems.reception.visitor.Visitor;
import com.nsia.officems.reception.visitor.VisitorPhotoService;
import com.nsia.officems.reception.visitor.VisitorRepository;
import com.nsia.officems.reception.visitor.VisitorService;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VisitServiceImpl implements VisitService {
    @Value(value = "${app.upload.reception.visitors}")
    private String visitorPhotosPath;

    @Value(value = "${app.upload.reception.vehicles}")
    private String vehiclePhotosPath;

    private final DateTimeChange changeDate = new DateTimeChange(); 
    
    @Autowired
    private VisitRepository  visitRepository;

    @Autowired
    private VisitVisitorRepository  visitVisitorRepository;

    @Autowired
    private VisitVehicleRepository  visitVehicleRepository;

    @Autowired
    private VisitorRepository  visitorRepository;

    @Autowired
    private ReceptionVehicleRepository  vehicleRepository;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private ReceptionVehicleService vehicleService;

    @Autowired
    private VisitorPhotoService visitorPhotoService;

    @Autowired
    private ReceptionVehiclePhotoService vehiclePhotoService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public long countALL(){
        return visitRepository.countALL();
    }
    public long countToday(){
        return visitRepository.countToday();
    }
    public List getVisitCountByDepartment(){
        return visitRepository.getVisitCountByDepartment();
    }
    public List getVisitCountByType(){
        return visitRepository.getVisitCountByType();
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "inner join public.department d on d.id=v.host_department_id";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visit v", null, joinClause, whereClause, groupByClause, input);
    }

    public Object getMyList(DataTablesInput input, Map<String, String> filters) {
        User currentLoginUser = userService.getLoggedInUser();
        String joinClause = "inner join public.department d on d.id=v.host_department_id";
        // To have first AND with no error
        String whereClause = " v.created_by=" + currentLoginUser.getId() + " " + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visit v", null, joinClause, whereClause, groupByClause, input);
    }

    public Visit findById(Long id) {
        System.out.println(" Visit.findById()" + id);
        Optional<Visit> objection = visitRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Visit: " + objection);
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Visit> document = visitRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit createSchedule(String data, User loggedInUser) throws JSONException, IOException {
        return create(data, loggedInUser, true);
    }

    @Override
    public Visit create(String data, User loggedInUser, boolean isSchedule) throws JSONException, IOException {
        User currentLoginUser = loggedInUser;
        String CLOSED = "CLOSED";
        String COMPLETED = "COMPLETED";
        String SCHEDULED = "SCHEDULED";
        String WAITING = "WAITING";

        JSONObject visitJson = new JSONObject(data);
        Visit newVisit = new Visit();
        newVisit.setCreatedBy(currentLoginUser);
        newVisit.setScheduled(false);

        newVisit = setAttributes(newVisit, visitJson);
        newVisit.setStatus(CLOSED);
        newVisit.setResolution(COMPLETED);
        if(isSchedule) {
            newVisit.setScheduled(true);
            newVisit.setStatus(SCHEDULED);
            newVisit.setResolution(WAITING);
        }

        JSONArray visitorsJson = visitJson.getJSONArray("visitors");
        List<Visitor> newVisitors = new ArrayList<Visitor>();
        for (int i = 0; i < visitorsJson.length(); i++) {
            JSONObject visitorJson = (JSONObject) visitorsJson.get(i);
            Visitor visitor = new Visitor();
            visitor.setCreatedBy(newVisit.getCreatedBy());
            visitor = visitorService.setAttributes(visitor, visitorJson);
            newVisitors.add(visitor);
        }

        JSONArray vehiclesJson = visitJson.getJSONArray("vehicles");
        List<ReceptionVehicle> newVehicles = new ArrayList<ReceptionVehicle>();
        for (int i = 0; i < vehiclesJson.length(); i++) {
            JSONObject vehicleJson = (JSONObject) vehiclesJson.get(i);
            ReceptionVehicle vehicle = new ReceptionVehicle();
            vehicle.setCreatedBy(newVisit.getCreatedBy());
            vehicle = vehicleService.setAttributes(vehicle, vehicleJson);
            newVehicles.add(vehicle);
        }

        // Save the objects in Database
        newVisit = visitRepository.save(newVisit);

        List<VisitVisitor> visitVisitors = new ArrayList<VisitVisitor>();
        for (Visitor visitor : newVisitors) {
            VisitVisitor visitVisitor = new VisitVisitor(newVisit, visitorService.create(visitor, isSchedule), null, null, null);
            if(isSchedule) {
                visitVisitor.setStatus(SCHEDULED);
            } else {
                visitVisitor.setStatus(CLOSED);
                visitVisitor.setEntryDate(newVisit.getVisitDate());
                visitVisitor.setEntryTime(newVisit.getVisitTime());
            }
            visitVisitors.add(visitVisitor);
        }

        List<VisitVehicle> visitVehicles = new ArrayList<VisitVehicle>();
        for (ReceptionVehicle vehicle : newVehicles) {
            VisitVehicle visitVehicle = new VisitVehicle(newVisit, vehicleService.create(vehicle, isSchedule), null, null, null);
            if(isSchedule) {
                visitVehicle.setStatus(SCHEDULED);
            } else {
                visitVehicle.setStatus(CLOSED);
                visitVehicle.setEntryDate(newVisit.getVisitDate());
                visitVehicle.setEntryTime(newVisit.getVisitTime());
            }
            visitVehicles.add(visitVehicle);
        }

        visitVisitorRepository.saveAll(visitVisitors);
        visitVehicleRepository.saveAll(visitVehicles);

        return newVisit;
    }

    public Visit setAttributes(Visit visit, JSONObject visitJson) throws JSONException, IOException {
        visit.setHost(visitJson.get("host") == null? null: visitJson.get("host").toString());
        visit.setSubject(visitJson.get("visitSubject") == null? null: visitJson.get("visitSubject").toString());
        visit.setSource(visitJson.get("visitSource") == null? null: visitJson.get("visitSource").toString());
        visit.setCategory(visitJson.get("visitCategory") == null? null: visitJson.get("visitCategory").toString());
        visit.setType(visitJson.get("visitType") == null? null: visitJson.get("visitType").toString());
        
        visit.setVisitDate(visitJson.get("visitDate") == null? null: changeDate.convertPersianDateToGregorianDate(visitJson.get("visitDate").toString()));
        // if(visitDate != null) {
        //     LocalDate date = LocalDate.parse(visitDate);
        //     visit.setVisitDate(Date.valueOf(date));
        // }

        String visitTime = visitJson.get("visitTime") == null? null: visitJson.get("visitTime").toString();
        if(visitTime != null) {
            // HH: 00 - 23
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.parse(visitTime, timeFormatter);
            visit.setVisitTime(Time.valueOf(time));
        }

        Long hostDepartment = visitJson.getLong("hostDepartment");
        Department department = departmentService.findById(hostDepartment);
        visit.setDepartment(department);
        
        visit.setRemarks(visitJson.get("remarks") == null? null: visitJson.get("remarks").toString());
        return visit;
    }

    public List<List<String>> readExcelAsJson(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            File tempFile = null;
            try {
                final byte[] fileBytes = file.getBytes();
                
                String tempFileName = UUID.randomUUID().toString();
                tempFile = File.createTempFile(tempFileName, file.getOriginalFilename());
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(fileBytes);
                fos.close();
            } catch (final Exception e) {
                e.printStackTrace();
                return null;
            }

            if(tempFile != null) {
                FileInputStream inputStream = new FileInputStream(tempFile);
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                XSSFSheet worksheet = workbook.getSheetAt(0);

                List<List<String>> sheetDataTable = new ArrayList<List<String>>();

                // Get the first and last sheet row number.
                int firstRowNum = worksheet.getFirstRowNum();
                int lastRowNum = worksheet.getLastRowNum();
                if(lastRowNum > 0) {
                    // Loop in sheet rows.
                    for(int i=firstRowNum; i<lastRowNum + 1; i++) {
                        // Get current row object.
                        Row row = worksheet.getRow(i);

                        // Get first and last cell number.
                        int firstCellNum = row.getFirstCellNum();
                        int lastCellNum = row.getLastCellNum();

                        // Create a String list to save column data in a row.
                        List<String> rowDataList = new ArrayList<String>();

                        // Loop in the row cells.
                        for(int j = firstCellNum; j < lastCellNum; j++) {
                            // Get current cell.
                            Cell cell = row.getCell(j);
                            // Get cell type.
                            CellType cellType = cell.getCellType();

                            if(cellType.equals(CellType.NUMERIC)) {
                                double numberValue = cell.getNumericCellValue();
                                // BigDecimal is used to avoid double value is counted use Scientific counting method.
                                // For example the original double variable value is 12345678, but jdk translated the value to 1.2345678E7.
                                String stringCellValue = BigDecimal.valueOf(numberValue).toPlainString();
                                rowDataList.add(stringCellValue);
                            } else if(cellType.equals(CellType.STRING)) {
                                String cellValue = cell.getStringCellValue();
                                rowDataList.add(cellValue);
                            } else if(cellType.equals(CellType.BOOLEAN)) {
                                boolean numberValue = cell.getBooleanCellValue();
                                String stringCellValue = String.valueOf(numberValue);
                                rowDataList.add(stringCellValue);
                            } else if(cellType.equals(CellType.BLANK)) {
                                rowDataList.add("");
                            }
                        }
                        // Add current row data list in the return list.
                        sheetDataTable.add(rowDataList);
                    }
                    return sheetDataTable;
                }
            }
        }
        return null;
    }

    @Override
	public File getExcelTemplate() throws IOException {
		File excelFile = File.createTempFile("excel_template", ".xlsx");
		FileOutputStream outputStream = new FileOutputStream(excelFile);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet workSheet = workbook.createSheet("visit");
		
		List<String> excelColumns = new ArrayList<String>();
        excelColumns.add("firstName");
        excelColumns.add("lastName");
        excelColumns.add("fatherName");
        excelColumns.add("phone");
        excelColumns.add("email");
        excelColumns.add("tazkira");
        excelColumns.add("address");
        excelColumns.add("designation");

        Row headerRow = workSheet.createRow(0);

		int colIndex = 0;
		for (String col : excelColumns) {
			Cell columnCell = headerRow.createCell(colIndex);
			columnCell.setCellValue(col);
			colIndex++;
		}

		workbook.write(outputStream);
        workbook.close();
		outputStream.flush();
		outputStream.close();
		System.out.println(excelFile.getAbsolutePath());
		return excelFile;
	}

    public VisitDto findById_Dto(Long id){
        Visit visit = findById(id);
        VisitDto visitDto = new VisitDto();

        VisitMapper.MapVisitToVisitDto(visit, visitDto, departmentService);
        return visitDto;
    }
}
