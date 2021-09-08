package com.nsia.officems.reception.visit_visitor;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems.reception.visit.Visit;
import com.nsia.officems.reception.visitor.Visitor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author mohbadar
 */

@Getter
@Setter
@NoArgsConstructor
@Table(name = "recep_visit_visitor")
@Entity(name = "VisitVisitor")
public class VisitVisitor {
    @EmbeddedId
    private VisitVisitorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("visitId")
    @JoinColumn(name = "visit_id")
    @JsonIgnore
    private Visit visit;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("visitorId")
    @JoinColumn(name = "visitor_id")
    @JsonIgnore
    private Visitor visitor;

    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "entry_time")
    private Time entryTime;

    // DONE - CANCELED - REJECTED
    @Column
    private String status;

    public VisitVisitor(Visit visit, Visitor visitor, Date entryDate, Time entryTime, String status) {
        this.id = new VisitVisitorId(visit.getId(), visitor.getId());
        this.visit = visit;
        this.visitor = visitor;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.status = status;
    }
}