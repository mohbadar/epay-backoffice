package com.nsia.officems.transportation.vehicle;

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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transport_vehicle")
@Entity(name = "Vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transport_vehicle_tbl_generator")
    @SequenceGenerator(name="transport_vehicle_tbl_generator", sequenceName = "transport_vehicle_tbl_seq", allocationSize = 1)
    private Long id;

    @Column(name = "vehicle_Class")
    private String vehicleClass;

    @Column
    private String manufacturer;

    @Column
    private String model;

    @Column
    private String type;

    @Column
    private String year;

    @Column
    private String colour;

    @Column
    private String plateNo;

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
