package com.nsia.officems.task_mng.task;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems.task_mng.task.task_attachment.TaskAttachment;
import com.nsia.officems.task_mng.task.task_comment.TaskComment;
import com.nsia.officems.task_mng.task.task_execution.TaskExecution;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "taskmng_task")
@Entity(name = "Task")
public class Task {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskmng_task_tbl_generator")
	@SequenceGenerator(name="taskmng_task_tbl_generator", sequenceName = "taskmng_task_tbl_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title")
	private String title;
    
    @Column
    private String description;
    
	@Column
    private String tags;

    // When the task is expected to be completed
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;

    // When the task is actually completed
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date completionDate;

    @Column
    private Long progress;

    @Column
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    private Task parent;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="assigned_to", referencedColumnName = "id")
	private List<User> assignedTo;
    
    @Column(columnDefinition = "boolean default false")
    private Boolean archived = false;
    
    @Column(name = "archived_by")
	private Long archivedBy;

	@Column(name = "archived_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
	private LocalDateTime archivedAt;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="taskboard_id", referencedColumnName = "id", nullable = false)
	private Taskboard taskboard;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @JsonIgnore
	private List<TaskComment> taskComments;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @JsonIgnore
	private List<TaskExecution> taskExecutions;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @JsonIgnore
	private List<TaskAttachment> taskAttachments;

    public Task(Long id, String title, String description, String status, User createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdBy = createdBy;
    }
}
