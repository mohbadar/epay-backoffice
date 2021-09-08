package com.nsia.officems.reception.visitor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "recep_visitor_photo")
@Entity(name = "VisitorPhoto")
public class VisitorPhoto {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitor_ph_tbl_generator")
	@SequenceGenerator(name="visitor_ph_tbl_generator", sequenceName = "visitor_ph_tbl_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String path;

    private String mimeType;

    @Column
    private Boolean isDefault;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="visitor_id", referencedColumnName = "id", nullable = false)
    private Visitor visitor;
}
