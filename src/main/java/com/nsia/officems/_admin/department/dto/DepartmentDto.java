package com.nsia.officems._admin.department.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentDto {

    private Long id;
    private String nameEn;
    private String nameDr;
    private String namePs;

    private Long parentId;

    private String header;
    private String footer;
}
