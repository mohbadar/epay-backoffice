package com.nsia.officems.todo.impl;

import com.nsia.officems.todo.Todo;
import com.nsia.officems.todo.TodoRepository;
import com.nsia.officems.todo.TodoService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository  todoRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.profile pro", null, joinClause, whereClause, groupByClause, input);
    }

    public Todo findById(Long id) {
        System.out.println(" Todo.findById()" + id);
        Optional<Todo> objection = todoRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Todo: " + objection);
            return objection.get();
        }
        return null;
    }

    public List<Todo> findByDone(boolean done) {
        System.out.println(" Todo.findByDone()" + done);
        return todoRepository.findByDone(done);
    }

    public List<Todo> findByArchived() {
        System.out.println(" Todo.findByArchived()");
        return todoRepository.findByArchived(true);
    }

    public List<Todo> findByCreatedBy(User user) {
        return todoRepository.findByCreatedBy(user);
    }

    public boolean delete(long id) {
        todoRepository.deleteById(id);
        return true;
    }

    @Override
    public Todo save(Todo obj) {
        return this.todoRepository.save(obj);
    }

    @Override
    public Todo create(String data, User loginUser) throws JSONException {
        JSONObject obj = new JSONObject(data);
        Todo todo = new Todo();
        todo.setSubject(obj.get("subject") == null? null: obj.get("subject").toString());
        todo.setDetails(obj.get("details") == null? null: obj.get("details").toString());
        todo.setBgColor(obj.get("bgColor") == null? null: obj.get("bgColor").toString());
        todo.setDone(false);
        todo.setArchived(false);
        todo.setCreatedBy(loginUser);
        return save(todo);
    }

    @Override
    public Todo update(Long id, Todo todo) {
        Todo existingTodo = findById(id);
        if(existingTodo != null) {
            existingTodo.setSubject(todo.getSubject());
            existingTodo.setDetails(todo.getDetails());
            existingTodo.setBgColor(todo.getBgColor());
            return save(existingTodo);
        }
        return null;
    }
}
