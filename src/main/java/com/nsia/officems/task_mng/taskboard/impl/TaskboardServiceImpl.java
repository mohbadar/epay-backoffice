package com.nsia.officems.task_mng.taskboard.impl;

import com.nsia.officems.task_mng.taskboard.Taskboard;
import com.nsia.officems.task_mng.taskboard.TaskboardRepository;
import com.nsia.officems.task_mng.taskboard.TaskboardService;
import com.nsia.officems.task_mng.taskboard.dto.TaskboardDto;
import com.nsia.officems.task_mng.taskboard.dto.TaskboardMapper;
import com.nsia.officems.task_mng.taskboard.taskboard_status.TaskboardStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class TaskboardServiceImpl implements TaskboardService {
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private TaskboardRepository  taskboardRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.taskmng_taskboard tb", null, joinClause, whereClause, groupByClause, input);
    }

    public Taskboard findById(Long id) {
        System.out.println("Taskboard.findById()" + id);
        Optional<Taskboard> taskboard = taskboardRepository.findById(id);
        if (taskboard.isPresent()) {
            System.out.println("Taskboard: " + taskboard);
            return taskboard.get();
        }
        return null;
    }

    @Override
    public List<Taskboard> findMyTaskboards() {
        User currentLoginUser = userService.getLoggedInUser();
        return taskboardRepository.findByCreatedBy(currentLoginUser);
    }

    @Override
    public List<Taskboard> findSharedTaskboards() {
        User currentLoginUser = userService.getLoggedInUser();
        return taskboardRepository.findByMembers_Id(currentLoginUser.getId());
    }

    @Override
    public List<Taskboard> findByIsPublic(Boolean flag) {
        return taskboardRepository.findByIsPublic(flag);
    }

    public boolean delete(long id) {
        Optional<Taskboard> taskboard = taskboardRepository.findById(id);
        if (taskboard.isPresent()) {
            // taskboard.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Taskboard save(Taskboard obj) {
        return this.taskboardRepository.save(obj);
    }

    @Override
    public Taskboard create(String data, User loggedInUser) throws JSONException {
        JSONObject obj = new JSONObject(data);
        Taskboard taskboard = new Taskboard();
        taskboard.setTitle(obj.get("title") == null? null: obj.get("title").toString());
        taskboard.setDescription(obj.get("description") == null? null: obj.get("description").toString());
        taskboard.setTags(obj.get("tags") == null? null: obj.get("tags").toString());
        
        if(obj.get("isPublic") != null) {
            String isPublicVal = obj.get("isPublic").toString();
            if(isPublicVal.equals("true")) {
                taskboard.setIsPublic(true);
            } else {
                taskboard.setIsPublic(false);
            }
        } else {
            taskboard.setIsPublic(false);
        }
        
        taskboard.setCreatedBy(loggedInUser);
        taskboard = taskboardRepository.save(taskboard);
        return taskboard;
    }

    @Override
    public Taskboard update(Long id, TaskboardDto dto) {
        User currentLoginUser = userService.getLoggedInUser();
        Taskboard taskboard = findById(id);

        if(!currentLoginUser.getId().equals(taskboard.getCreatedBy().getId())) {
            throw new AccessDeniedException("Only taskboard owner can edit a taskboard.");
        }

        taskboard = TaskboardMapper.map(dto, taskboard, departmentService, this);
        return save(taskboard);
    }

    @Override
    public User addMember(Long taskboardId, String data, User loggedInUser) throws JSONException {
        JSONObject obj = new JSONObject(data);
        String userId = (obj.get("userId") == null)? null: obj.get("userId").toString();
        if(userId != null && userId.length() > 0) {
            User newMember = userService.findById(Long.valueOf(userId));
            Taskboard taskboard = findById(taskboardId);
            List<User> taskboardMembers = taskboard.getMembers();
            taskboardMembers.add(newMember);
            taskboard.setMembers(taskboardMembers);
            save(taskboard);
            return newMember;
        }
        return null;
    }

    @Override
    public Boolean removeMember(Long taskboardId, Long memberId) throws JSONException {
        Long removeMemberId = Long.valueOf(memberId);
        Taskboard taskboard = findById(taskboardId);
        List<User> taskboardMembers = taskboard.getMembers();
        taskboardMembers.removeIf(member -> member.getId().equals(removeMemberId));
        taskboard.setMembers(taskboardMembers);
        save(taskboard);
        return true;
    }

    @Override
    public long countALL() {
        return taskboardRepository.countALL();
    }

    @Override
    public long countToday() {
        return taskboardRepository.countToday();
    }
}
