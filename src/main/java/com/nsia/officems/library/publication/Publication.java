package com.nsia.officems.library.publication;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._identity.authentication.user.User;

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
@Table(name = "lib_publication")
@Entity(name = "Publication")
public class Publication {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lib_pubication_tbl_generator")
	@SequenceGenerator(name="lib_pubication_tbl_generator", sequenceName = "lib_pubication_tbl_seq", allocationSize = 1)
	private Long id;

    @Column
	private String subject;

}
