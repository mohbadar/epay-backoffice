package com.nsia.officems.task_mng.task.task_status.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskStatusDto {

    private long id;
    private String comment;
    private Long taskId;
    
}
