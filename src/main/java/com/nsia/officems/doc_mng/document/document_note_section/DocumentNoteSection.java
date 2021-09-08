package com.nsia.officems.doc_mng.document.document_note_section;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsia.officems.doc_mng.document.Document;

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
@Table(name = "doc_mng_document_note_section")
@Entity(name = "DocumentNoteSection")
public class DocumentNoteSection {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_mng_document_note_section_tbl_generator")
    @SequenceGenerator(name = "doc_mng_document_note_section_tbl_generator", sequenceName = "doc_mng_document_note_section_tbl_seq", allocationSize = 1)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private Long orderCol = 0L;

    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Document document;
}
