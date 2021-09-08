package com.nsia.officems.reception.visit.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class VisitDto {
    private Long id;
    private String subject;
    private String host;
    private String source;
    private String visitDate;
    private String visitTime;
    private String category;
    private String type;
    private String remarks;
    private String department;
}
