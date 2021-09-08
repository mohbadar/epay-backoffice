package com.nsia.officems.reception.visitor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.reception.visit.Visit;
import com.nsia.officems.reception.visit_visitor.VisitVisitor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "recep_visitor")
@Entity(name = "Visitor")
public class Visitor {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitor_tbl_generator")
	@SequenceGenerator(name="visitor_tbl_generator", sequenceName = "visitor_tbl_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
	private String firstName;
    
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "father_name")
    private String fatherName;
    
	@Column
    private String address;
    
    @Column
    private String phoneNo;

    @Column
    private String email;

    @Column
    private String tazkiraNo;

    @Column
    private String designation;
    
    @Column
    private Boolean status;
    
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
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name="default_photo_id", referencedColumnName = "id", nullable = true)
    private VisitorPhoto defaultPhoto;

    // @OneToMany(mappedBy="visitor")
    // private List<VisitorPhoto> visitorPhotos;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JsonIgnore
    // List<Visit> visits;

    @OneToMany(mappedBy = "visitor", fetch = FetchType.LAZY)
    private List<VisitVisitor> visitVisitors;
}
