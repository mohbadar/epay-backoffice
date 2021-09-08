package com.nsia.officems.task_mng.task.task_execution.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.FileUploadUtil;
import com.nsia.officems.task_mng.task.Task;
import com.nsia.officems.task_mng.task.TaskService;
import com.nsia.officems.task_mng.task.task_execution.TaskExecution;
import com.nsia.officems.task_mng.task.task_execution.TaskExecutionRepository;
import com.nsia.officems.task_mng.task.task_execution.TaskExecutionService;
import com.nsia.officems.task_mng.task.task_execution.dto.TaskExecutionDto;
import com.nsia.officems.task_mng.task.task_execution.dto.TaskExecutionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TaskExecutionServiceImpl implements TaskExecutionService {
    private final TaskExecutionRepository taskExecutionRepository;
    @Value("${app.upload.task_mng.execution}")
    private String uploadDir;
    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    public TaskExecutionServiceImpl(TaskExecutionRepository taskExecutionRepository) {
        this.taskExecutionRepository = taskExecutionRepository;
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " ";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visitor vor", null, joinClause, whereClause, groupByClause, input);
    }

    public TaskExecution findById(Long id) {
        System.out.println(" TaskExecution.findById()" + id);
        Optional<TaskExecution> taskExecution = taskExecutionRepository.findById(id);
        if (taskExecution.isPresent()) {
            System.out.println("TaskExecution: " + taskExecution);
            return taskExecution.get();
        }
        return null;
    }

    public List<TaskExecution> findByTaskId(Long taskId) {
        return taskExecutionRepository.findByTaskId(taskId);
    }

    public boolean delete(long id) {
        Optional<TaskExecution> taskExecution = taskExecutionRepository.findById(id);
        if (taskExecution.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public List<TaskExecution> findAll() {
        return taskExecutionRepository.findAll();
    }

    @Override
    public TaskExecution save(TaskExecution taskExecution) {
        return taskExecutionRepository.save(taskExecution);
    }

    @Override
    public TaskExecution create(TaskExecution taskExecution) {
        return save(taskExecution);
    }

    @Override
    public TaskExecution create(TaskExecutionDto taskExecutionDto) {
        TaskExecution taskExecution = TaskExecutionMapper.map(taskExecutionDto, new TaskExecution(), taskService);
        taskExecution.setCreatedBy(userService.getLoggedInUser());
        taskExecution = create(taskExecution);
        Task task = taskExecution.getTask();
        task.setProgress(taskExecution.getProgress());
        taskService.save(task);
        return taskExecution;
    }

    public TaskExecution create(TaskExecutionDto taskExecutionDto, MultipartFile file){
        TaskExecution taskExecution = TaskExecutionMapper.map(taskExecutionDto, new TaskExecution(), taskService);
        taskExecution.setCreatedBy(userService.getLoggedInUser());
        TaskExecution newTaskExecution = this.taskExecutionRepository.save(taskExecution);
        if(file !=null){
            String fileName = fileUploadUtil.saveAttachment(file, uploadDir, newTaskExecution.getId().toString(), "task");
                if (taskExecution != null) {
                    taskExecution.setAttachmentPath(fileName);
                }
        }
        taskExecution = create(taskExecution);
        Task task = taskExecution.getTask();
        task.setProgress(taskExecution.getProgress());
        taskService.save(task);
        return newTaskExecution;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, TaskExecution taskExecution) {
        return false;
    }

    public File downloadAttachment(Long id) throws Exception{
        Optional<TaskExecution> taskAttchement = taskExecutionRepository.findById(id);
        if (taskAttchement.isPresent()) {
            String fileName = taskAttchement.get().getAttachmentPath();
            String saveDirectory = uploadDir + "/" + id + "/" + fileName;
            if (new File(saveDirectory).exists()) {
                return new File(saveDirectory);
            }
        }

        return null;
    }


}
