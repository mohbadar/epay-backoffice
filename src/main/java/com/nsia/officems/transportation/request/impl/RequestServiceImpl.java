package com.nsia.officems.transportation.request.impl;

import com.google.gson.Gson;
import com.nsia.officems._admin.department.DepartmentRepository;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.transportation.driver.Driver;
import com.nsia.officems.transportation.driver.DriverRepository;
import com.nsia.officems.transportation.driver.DriverService;
import com.nsia.officems.transportation.request.Request;
import com.nsia.officems.transportation.request.RequestRepository;
import com.nsia.officems.transportation.request.RequestService;
import com.nsia.officems.transportation.request.dto.RequestDto;
import com.nsia.officems.transportation.request.dto.RequestMapper;
import com.nsia.officems.transportation.request.dto.RequestPrintDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.transportation.vehicle.Vehicle;
import com.nsia.officems.transportation.vehicle.VehicleRepository;
import com.nsia.officems.transportation.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
/**
 *
 * @author mohbadar
 *
 */
@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    @Autowired
    private DriverService driverService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DriverRepository driverRepository;

    private final DateTimeChange changeDate = new DateTimeChange();

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.user_tbl usr on usr.id=req.created_by ";
        joinClause += " inner join public.department reqdep on reqdep.id=req.requesting_department_id";
        joinClause += " left join public.user_tbl p_usr on p_usr.id=req.processed_by ";
        joinClause += " left join public.transport_driver tran_dr on tran_dr.id=req.driver_id";
        joinClause += " left join public.transport_vehicle tran_veh on tran_veh.id=req.vehicle_id";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.transport_request req", null, joinClause, whereClause, groupByClause, input);
    }

    public Object getPendingList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.user_tbl usr on usr.id=req.created_by ";
        joinClause += " inner join public.department reqdep on reqdep.id=req.requesting_department_id";
        joinClause += " left join public.user_tbl p_usr on p_usr.id=req.processed_by ";
        joinClause += " left join public.transport_driver tran_dr on tran_dr.id=req.driver_id";
        joinClause += " left join public.transport_vehicle tran_veh on tran_veh.id=req.vehicle_id";
        // To have first AND with no error
        String whereClause = " req.status='WAITING' " + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.transport_request req", null, joinClause, whereClause, groupByClause, input);
    }

    public Object getClosedList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.user_tbl usr on usr.id=req.created_by ";
        joinClause += " inner join public.department reqdep on reqdep.id=req.requesting_department_id";
        joinClause += " left join public.user_tbl p_usr on p_usr.id=req.processed_by ";
        joinClause += " left join public.transport_driver tran_dr on tran_dr.id=req.driver_id";
        joinClause += " left join public.transport_vehicle tran_veh on tran_veh.id=req.vehicle_id";
        // To have first AND with no error
        String whereClause = " req.status!='WAITING' " + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.transport_request req", null, joinClause, whereClause, groupByClause, input);
    }

    public Object getMyList(DataTablesInput input, Map<String, String> filters) {
        User currentLoginUser = userService.getLoggedInUser();
        String joinClause = " inner join public.user_tbl usr on usr.id=req.created_by ";
        joinClause += " inner join public.department reqdep on reqdep.id=req.requesting_department_id";
        joinClause += " left join public.user_tbl p_usr on p_usr.id=req.processed_by ";
        joinClause += " left join public.transport_driver tran_dr on tran_dr.id=req.driver_id";
        joinClause += " left join public.transport_vehicle tran_veh on tran_veh.id=req.vehicle_id";
        // To have first AND with no error
        String whereClause = " req.created_by=" + currentLoginUser.getId() + " " + dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.transport_request req", null, joinClause, whereClause, groupByClause, input);
    }

    @Override
    public Request findById(Long id) {
        System.out.println("Request.findById()" + id);
        Optional<Request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            System.out.println("Request: " + request);
            return request.get();
        }
        return null;
    }

    public Request cancelRequest(Long id) {
        User currentLoginUser = userService.getLoggedInUser();
        Request request = findById(id);
        if(!currentLoginUser.getId().equals(request.getCreatedBy().getId())) {
            throw new AccessDeniedException("You are not the Owner of Request");
        }
        String curStatus = request.getStatus();
        if(curStatus.equals("CLOSED")) {
            throw new AccessDeniedException("The request is already proccessed and closed.");
        }
        request.setStatus("CLOSED");
        request.setResolution("CANCELED");
        return save(request);
    }

    public Request processRequest(Long id, RequestDto requestDto) {
        User currentLoginUser = userService.getLoggedInUser();
        Request request = findById(id);
        request.setStatus("CLOSED");
        request.setResolution(requestDto.getResolution());
        request.setProcessedBy(currentLoginUser);
        request.setProcessedAt(LocalDateTime.now());
        request.setProcessedComment(requestDto.getProcessedComment());
        if(request.getResolution().equals("COMPLETED")) {
            request.setDriver(driverService.findById(requestDto.getDriverId()));
            request.setVehicle(vehicleService.findById(requestDto.getVehicleId()));
        }
        return save(request);
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public boolean delete(long id) {
        Optional<Request> pOptional = requestRepository.findById(id);
        if (pOptional.isPresent()) {
            Request info = pOptional.get();
            info.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            requestRepository.save(info);
            return true;
        }

        return false;
    }


    /*
    @Override
    public Request create(Request request) {
        return requestRepository.save(request);
    }
   */
    /*
    @Override
    public RequestDto create(RequestDto requestDto) {
        Request request = RequestMapper.map(requestDto, new Request());
        // request.setCreatedBy(userService.findById(1l));
        request.setCreatedBy(userService.getLoggedInUser());
        request.setVehicle(vehicleRepository.findById(requestDto.getVehicleId()).orElse(null));
        request.setDriver(driverRepository.findById(requestDto.getDriverId()).orElse(null));
        request.setApprovedBy(departmentRepository.findById(requestDto.getDepartmentId()).orElse(null));
        request.setStatus("NEW");
        return RequestMapper.map(create(request));
    }
     */

    @Override
    public Boolean update(Long id, RequestDto dto) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            Request info = RequestMapper.map(dto, request.get(), departmentService, driverService, vehicleService);
            Driver driver = driverRepository.findById(dto.getDriverId()).orElse(null);
            if(driver != null)
                info.setDriver(driver);
            Vehicle vehicle=vehicleRepository.findById(dto.getVehicleId()).orElse(null);
            if(vehicle!=null)
                info.setVehicle(vehicle);

            if (!info.equals(null)) {
                requestRepository.save(info);
                return true;
            }
        }
        return false;
    }


    @Override
    public List<Request> findAll() {
        List<Request> list=  requestRepository.findAll();
        return list;
    }



    @Override
    public Request create(RequestDto requestDto, User loggedInUser) throws JSONException, IOException {

        User currentLoginUser = loggedInUser;
//      JSONObject requestJson =
        // Gson gson = new Gson();
        // RequestDto requestDto = RequestMapper.map(data);
        Request newRequest = RequestMapper.map(requestDto, new Request(), departmentService, driverService, vehicleService);
        newRequest.setStatus("WAITING");
        newRequest.setCreatedBy(currentLoginUser);
        // newRequest.setDriver(driverRepository.findById(requestDto.getDriverId()).orElse(null));
        // newRequest.setVehicle(vehicleRepository.findById(requestDto.getVehicleId()).orElse(null));
        // newRequest.setApprovedBy(departmentRepository.findById(requestDto.getDepartmentId()).orElse(null));
//      newRequest=requestRepository.save(newRequest);
        return createRequest(newRequest);
    }

    @Override
    public Request createRequest(Request request) throws JSONException, IOException {
        return requestRepository.save(request);
    }

    public RequestPrintDto findRequestPrintDtoById(Long id){
        RequestPrintDto requestPrintDto = new RequestPrintDto();
        Request request = new Request();
        Optional<Request> newRequest = requestRepository.findById(id);
        if(newRequest.isPresent()){
            request =  newRequest.get();
        }

        RequestMapper.MapRequestToRequestPrintDto(requestPrintDto, request, vehicleService, driverService, departmentService, userService);
        return requestPrintDto;
    }

    @Override
    public long countALL() {
        return requestRepository.countALL();
    }

    @Override
    public long countToday() {
        return requestRepository.countToday();
    }

    @Override
    public List getRequestCountByDepartment() {
        return requestRepository.getRequestCountByDepartment();
    }

    @Override
    public List getRequestCountByStatus() {
        return requestRepository.getRequestCountByStatus();
    }


}


