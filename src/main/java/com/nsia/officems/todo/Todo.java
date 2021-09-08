package com.nsia.officems.todo;

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
import com.nsia.officems._identity.authentication.user.User;

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
@Table(name = "todo")
@Entity(name = "Todo")
public class Todo {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_tbl_generator")
	@SequenceGenerator(name="todo_tbl_generator", sequenceName = "todo_tbl_seq", allocationSize = 1)
    private Long id;

    @Column
    private String subject;

    @Column
    private String details;

    @Column(name = "bg_color")
    private String bgColor;
    
    @Column
    private Boolean done;

    @Column
    private Boolean archived;
    
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
