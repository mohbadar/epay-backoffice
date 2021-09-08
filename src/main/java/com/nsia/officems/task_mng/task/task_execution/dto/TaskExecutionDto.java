package com.nsia.officems.task_mng.task.task_execution.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskExecutionDto {
    private long id;
    private String details;
    private Long progress;
    private Long taskId;
    
}
