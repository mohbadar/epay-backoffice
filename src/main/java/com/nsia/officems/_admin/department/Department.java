package com.nsia.officems._admin.department;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._infrastructure.base.BaseEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Department")
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_tbl_generator")
    @SequenceGenerator(name = "department_tbl_generator", sequenceName = "department_tbl_seq", allocationSize = 1)
    private Long id;

    @Column
    private String namePs;

    @Column
    private String nameDr;

    @Column
    private String nameEn;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "deleted_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    private LocalDateTime deletedAt;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    private Department parent;

    @Column(columnDefinition = "TEXT")
    private String header;

    @Column(columnDefinition = "TEXT")
    private String footer;

    public Department(Long id, String namePs, String nameDr, String nameEn) {
        this.id = id;
        this.namePs = namePs;
        this.nameDr = nameDr;
        this.nameEn = nameEn;
    }

    public Department(Long id, String namePs, String nameDr, String nameEn, String header, String footer) {
        this.id = id;
        this.namePs = namePs;
        this.nameDr = nameDr;
        this.nameEn = nameEn;
        this.header = header;
        this.footer = footer;
    }
}
