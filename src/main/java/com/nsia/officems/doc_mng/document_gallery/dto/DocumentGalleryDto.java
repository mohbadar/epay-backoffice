package com.nsia.officems.doc_mng.document_gallery.dto;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentGalleryDto {
    @NotNull
    private String namePs;

    @NotNull
    private String nameDr;

    @NotNull
    private String nameEn;
}
