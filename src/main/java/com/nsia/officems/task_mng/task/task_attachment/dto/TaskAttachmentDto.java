package com.nsia.officems.task_mng.task.task_attachment.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskAttachmentDto {
    private long id;
    private String details;
    private Long taskId;
    
}
