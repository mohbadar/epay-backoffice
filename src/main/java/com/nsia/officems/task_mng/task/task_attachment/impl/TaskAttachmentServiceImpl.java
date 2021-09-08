package com.nsia.officems.task_mng.task.task_attachment.impl;

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
import com.nsia.officems.task_mng.task.task_attachment.TaskAttachment;
import com.nsia.officems.task_mng.task.task_attachment.TaskAttachmentRepository;
import com.nsia.officems.task_mng.task.task_attachment.TaskAttachmentService;
import com.nsia.officems.task_mng.task.task_attachment.dto.TaskAttachmentDto;
import com.nsia.officems.task_mng.task.task_attachment.dto.TaskAttachmentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TaskAttachmentServiceImpl implements TaskAttachmentService {
    private final TaskAttachmentRepository taskAttachmentRepository;
    @Value("${app.upload.task_mng.attachment}")
    private String uploadDir;
    
    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    public TaskAttachmentServiceImpl(TaskAttachmentRepository taskAttachmentRepository) {
        this.taskAttachmentRepository = taskAttachmentRepository;
    }

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " ";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.recep_visitor vor", null, joinClause, whereClause, groupByClause, input);
    }

    public TaskAttachment findById(Long id) {
        System.out.println(" TaskAttachment.findById()" + id);
        Optional<TaskAttachment> taskAttachment = taskAttachmentRepository.findById(id);
        if (taskAttachment.isPresent()) {
            System.out.println("TaskAttachment: " + taskAttachment);
            return taskAttachment.get();
        }
        return null;
    }

    public List<TaskAttachment> findByTaskId(Long taskId) {
        return taskAttachmentRepository.findByTaskId(taskId);
    }

    public boolean delete(long id) {
        Optional<TaskAttachment> taskAttachment = taskAttachmentRepository.findById(id);
        if (taskAttachment.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public List<TaskAttachment> findAll() {
        return taskAttachmentRepository.findAll();
    }

    @Override
    public TaskAttachment save(TaskAttachment taskAttachment) {
        return taskAttachmentRepository.save(taskAttachment);
    }

    @Override
    public TaskAttachment create(long taskId, MultipartFile file){
        try {
            Task newTask = this.taskService.findById(taskId);

            if (!newTask.equals(null)) {
                TaskAttachment taskAttachmentObj = new TaskAttachment();
                taskAttachmentObj.setTask(newTask);
                taskAttachmentObj.setCreatedBy(this.userService.getLoggedInUser());
                TaskAttachment NewtaskAttachment = taskAttachmentRepository.save(taskAttachmentObj);
                // String fileName = this.saveAttachment(file, newObj.getId());
                String fileName = fileUploadUtil.saveAttachment(file, uploadDir, NewtaskAttachment.getId().toString(), "task");
                if (NewtaskAttachment != null) {
                    NewtaskAttachment.setFileName(fileName);
                    return taskAttachmentRepository.save(NewtaskAttachment);
                }

            }
            return null;
        } catch (Exception e) {
            System.out.println("exception occured in creating documentUpload");
            return null;
        }
    }
    @Override
    public TaskAttachment create(TaskAttachmentDto taskAttachmentDto) {
        TaskAttachment taskAttachment = TaskAttachmentMapper.map(taskAttachmentDto, new TaskAttachment(), taskService);
        taskAttachment.setCreatedBy(userService.getLoggedInUser());
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, TaskAttachment taskAttachment) {
        return false;
    }

    @Override
    public File downloadAttachment(Long id) throws Exception{
        Optional<TaskAttachment> taskAttchement = taskAttachmentRepository.findById(id);
        if (taskAttchement.isPresent()) {
            String fileName = taskAttchement.get().getFileName();
            String saveDirectory = uploadDir + "/" + id + "/" + fileName;
            if (new File(saveDirectory).exists()) {
                return new File(saveDirectory);
            }
        }

        return null;
    }


}
