package com.nsia.officems.helpdesk.ticket;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.helpdesk.service_type.ServiceType;

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
@Table(name = "helpdesk_ticket")
@Entity(name = "Ticket")
public class Ticket {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "helpdesk_ticket_tbl_generator")
	@SequenceGenerator(name="helpdesk_ticket_tbl_generator", sequenceName = "helpdesk_ticket_tbl_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title")
	private String title;
    
    @Column
    private String description;
    
	@Column
    private String tags;

    @Column
    private String status;

    @ManyToMany
    @JoinColumn(name="assigned_to", referencedColumnName = "id")
	private List<User> assignedTo;
    
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

    @ManyToOne
    @JoinColumn(name="service_type_id", referencedColumnName = "id", nullable = false)
	private ServiceType serviceType;
}
