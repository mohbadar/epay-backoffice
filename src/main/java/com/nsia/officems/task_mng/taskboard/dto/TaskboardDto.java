package com.nsia.officems.task_mng.taskboard.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TaskboardDto {
    private Long id;
    private String title;
    private String description;
    private String tags;
    private Boolean isPublic;
}
