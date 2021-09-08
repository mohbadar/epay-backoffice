package com.nsia.officems.todo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Object getMyTodos() throws Exception {
        User currentLoginUser = userService.getLoggedInUser();
        Map<String, Object> data = new HashMap<String, Object>();
        // List<Todo> todos = todoService.findByStatus("DONE");
        List<Todo> todos = todoService.findByCreatedBy(currentLoginUser);
        data.put("todos", todos);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/{todoId}/done")
    public ResponseEntity<Todo> todoDone(@PathVariable(name = "todoId", required = true) long id) {
        Todo todo = todoService.findById(id);
        todo.setDone(true);
        todo = todoService.save(todo);
        return ResponseEntity.ok(todo);
    }

    @GetMapping(value = "/{todoId}/archive")
    public ResponseEntity<Todo> todoArchive(@PathVariable(name = "todoId", required = true) long id) {
        Todo todo = todoService.findById(id);
        todo.setArchived(true);
        todo = todoService.save(todo);
        return ResponseEntity.ok(todo);
    }

    @GetMapping(value = "/{todoId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "todoId", required = true) long id) {
        System.out.println("Todo " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Todo todo = todoService.findById(id);
        data.put("todo", todo);
        return ResponseEntity.ok(data);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Todo> update(@PathVariable(name = "id", required = true) Long id,
            @RequestBody(required = true) Todo todo) {
        return ResponseEntity.ok(todoService.update(id, todo));
    }

    @PostMapping()
    public ResponseEntity<Todo> create(@RequestBody String body) throws JSONException {
        User currentLoginUser = userService.getLoggedInUser();
        return ResponseEntity.ok(todoService.create(body, currentLoginUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(todoService.delete(id));
    }
}