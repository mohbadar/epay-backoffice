package com.nsia.officems.helpdesk.ticket.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketDto {
    private long id;
    private String title;
    private String description;
    private String status;
    private String serviceTypeName;
    private long serviceTypeId;
    
}
