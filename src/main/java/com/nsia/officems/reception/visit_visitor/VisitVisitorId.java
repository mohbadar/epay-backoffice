package com.nsia.officems.reception.visit_visitor;

import lombok.AllArgsConstructor;  
import lombok.Data;  
import lombok.NoArgsConstructor;

import javax.persistence.Column;  
import javax.persistence.Embeddable;  
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VisitVisitorId implements Serializable {  
    @Column(name = "visit_id")
    private Long visitId;

    @Column(name = "visitor_id")
    private Long visitorId;
}