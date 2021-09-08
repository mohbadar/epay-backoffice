package com.nsia.officems.reception.visit;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.reception.vehicle.ReceptionVehicle;
import com.nsia.officems.reception.visit_vehicle.VisitVehicle;
import com.nsia.officems.reception.visit_visitor.VisitVisitor;
import com.nsia.officems.reception.visitor.Visitor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author mohbadar
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recep_visit")
@Entity(name = "Visit")
public class Visit {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_tbl_generator")
	@SequenceGenerator(name="visit_tbl_generator", sequenceName = "visit_tbl_seq", allocationSize = 1)
	private Long id;

    @Column
	private String subject;

    @Column
	private String source;

    @Column
	private String host;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column
	private Date visitDate;

    @Column
	private Time visitTime;
    
    // VIP - NORMAL
    @Column
    private String category;

    // OFFICIAL - NORMAL
    @Column
    private String type;

    // OFFICIAL - NORMAL
    @Column
    private boolean isScheduled;

    // SCHEDULED - CLOSED
    @Column
    private String status;

    // for each status as : COMPLETED - REJECTED - WAITING
    @Column
    private String resolution;

    @Column
    private String remarks;
    
    @Column(name = "deleted_by")
	private Long deletedBy;

	@Column(name = "deleted_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
	private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName = "id", nullable = false)
	private User createdBy;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="host_department_id", referencedColumnName = "id", nullable = false)
    private Department department;
    
    // @ManyToMany(fetch = FetchType.LAZY)
    // @JsonIgnore
    // List<Visitor> visitors;

    @OneToMany(mappedBy = "visitor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitVisitor> visitVisitors;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JsonIgnore
    // List<Vehicle> vehicles;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitVehicle> visitVehicles;
}
