package com.nsia.officems._admin.issuing_authority;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "IssuingAuthority")
@Table(name = "doc_mng_issuing_authority")
public class IssuingAuthority{
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_mng_issuing_authority_tbl_generator")
	@SequenceGenerator(name="doc_mng_issuing_authority_tbl_generator", sequenceName = "doc_mng_issuing_authority_tbl_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
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

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;
    
}
