package com.nsia.officems.transportation.driver;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._identity.authentication.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transport_driver")
@Entity(name = "Driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transport_driver_tbl_generator")
    @SequenceGenerator(name="transport_driver_tbl_generator", sequenceName = "transport_driver_tbl_seq", allocationSize = 1)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String fatherName;

    @Column
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="license_issue_date")
    private Date licenseIssueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="license_expiry_date")
    private Date licenseExpiryDate;

    @Column(name="license_Class")
    private String LicenseClass;

    @Column(name="driver_Image")
    private String driverImage;

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
