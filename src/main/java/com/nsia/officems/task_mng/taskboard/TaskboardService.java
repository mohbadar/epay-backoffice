package com.nsia.officems.task_mng.taskboard;

import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.task_mng.taskboard.dto.TaskboardDto;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface TaskboardService {
    public Taskboard save(Taskboard obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Taskboard findById(Long id);
    public List<Taskboard> findMyTaskboards();
    public List<Taskboard> findSharedTaskboards();
    public List<Taskboard> findByIsPublic(Boolean flag);
    public boolean delete(long id);
    public Taskboard create(String data, User loggedInUser) throws JSONException;
    public Taskboard update(Long id, TaskboardDto dto);
    public User addMember(Long taskboardId, String data, User loggedInUser) throws JSONException;
    public Boolean removeMember(Long taskboardId, Long memberId) throws JSONException;
    public long countALL();
    public long countToday();
}
