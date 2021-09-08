package com.nsia.officems.task_mng.taskboard.taskboard_status;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.task_mng.taskboard.Taskboard;

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
@Table(name = "taskmng_taskboard_status")
@Entity(name = "TaskboardStatus")
public class TaskboardStatus {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskmng_taskboard_status_tbl_generator")
	@SequenceGenerator(name="taskmng_taskboard_status_tbl_generator", sequenceName = "taskmng_taskboard_status_tbl_seq", allocationSize = 1)
    private Long id;

    @Column
	private String status;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="taskboard_id", referencedColumnName = "id", nullable = false)
	private Taskboard taskboard;
}
