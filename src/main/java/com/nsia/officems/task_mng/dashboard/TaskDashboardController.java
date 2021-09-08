package com.nsia.officems.task_mng.dashboard;
import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.taskboard.TaskboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/task_mng/dashboard")
public class TaskDashboardController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskboardService taskboardService;

    @GetMapping(value = "/count")
    private ResponseEntity<Map<String,Object>> countData(){
        Map<String, Object> data = new HashMap<String, Object>();
        long allTasks = taskService.countALL();
        long todayTasks = taskService.countToday();
        long allTaskBoards=taskboardService.countALL();
        long todayTaskBoards=taskboardService.countToday();
        data.put("alltasks", allTasks);
        data.put("todaytasks", todayTasks);
        data.put("alltaskboards",allTaskBoards);
        data.put("todaytaskboards",todayTaskBoards);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = { "/taskboard" })
    public List getTaskCountByTaskBoard() {
        return taskService.getTaskCountByTaskBoard();
    }

    @GetMapping(path = { "/status" })
    public List getTaskCountByStatus() {
        return taskService.getTaskCountByStatus();
    }
}
