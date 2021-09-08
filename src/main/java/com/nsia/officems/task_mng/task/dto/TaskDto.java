package com.nsia.officems.task_mng.task.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskDto {
    private long id;
    private String title;
    private String description;
    private String tags;
    private String status;
    private Long progress;
}
