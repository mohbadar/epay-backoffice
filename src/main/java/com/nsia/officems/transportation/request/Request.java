package com.nsia.officems.transportation.request;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._identity.authentication.user.User;

import com.nsia.officems.transportation.driver.Driver;
import com.nsia.officems.transportation.vehicle.Vehicle;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author mohbadar
 *
 **/

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transport_request")
@Entity(name = "Request")
public class Request {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transport_request_tbl_generator")
	@SequenceGenerator(name="transport_request_tbl_generator", sequenceName = "transport_request_tbl_seq", allocationSize = 1)
    private Long id;

    @Column
	private String pickupLocation;

    @Column
    private String dropOffLocation;

    // One Side or Two Side
    private Boolean hasReturn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="pickup_date")
    private Date pickupDate;

    @Column(name="pickup_time")
    private String pickupTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column (name="return_date")
    private Date returnDate;

    @Column(name="return_time")
    private String returnTime;

    @Column
    private String details;

    // PENDING - CLOSED
    @Column
    private String status;

    // COMPLETED - REJECTED - CANCELED
    @Column
    private String resolution;

    @ManyToOne
    @JoinColumn(name = "requesting_department_id", referencedColumnName = "id")
    private Department requestingDepartment;

    @ManyToOne(optional=true)
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = true)
    private Driver driver;

    @OneToOne(optional=true)
    @JoinColumn(name="vehicle_id", referencedColumnName = "id", nullable = true)
    private Vehicle vehicle;

    @Column
    private String processedComment;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @OneToOne(optional=true)
    @JoinColumn(name = "processed_by", referencedColumnName = "id", nullable = true)
    private User processedBy;
    
    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;
    
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

}
