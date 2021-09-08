package com.nsia.officems.todo;

import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface TodoService {
    public Todo save(Todo obj);
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public Todo findById(Long id);
    public List<Todo> findByDone(boolean done);
    public List<Todo> findByArchived();
    public List<Todo> findByCreatedBy(User user);
    public boolean delete(long id);
    public Todo create(String data, User loginUser) throws JSONException;
    public Todo update(Long id, Todo todo);
}
