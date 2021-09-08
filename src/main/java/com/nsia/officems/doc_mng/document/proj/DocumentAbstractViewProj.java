package com.nsia.officems.doc_mng.document.proj;

import java.time.LocalDateTime;
import java.util.Date;

import com.nsia.officems._identity.authentication.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DocumentAbstractViewProj {
    public Long id;
    public Long type;
    public String doumentTypeName;
    public String status;
    public String documentStatusName;
    public String fromDepartmentName;
    public long documentTypeId;
    public String fromEntityName;
    public User createdBy;
    public String createdAtDate;
}
