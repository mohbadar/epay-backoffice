package com.nsia.officems.task_mng.task.task_execution;

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
import com.nsia.officems.helpdesk.ticket.Ticket;
import com.nsia.officems.task_mng.task.Task;

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
@Table(name = "taskmng_task_execution")
@Entity(name = "TaskExecution")
public class TaskExecution {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskmng_task_execution_tbl_generator")
	@SequenceGenerator(name="taskmng_task_execution_tbl_generator", sequenceName = "taskmng_task_execution_tbl_seq", allocationSize = 1)
    private Long id;

    @Column
	private String details;

    @Column
    private String attachmentPath;

    @Column
    private Long progress;
    
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
    @JoinColumn(name="task_id", referencedColumnName = "id", nullable = false)
	private Task task;
}
