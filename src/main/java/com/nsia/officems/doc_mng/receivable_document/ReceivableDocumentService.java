package com.nsia.officems.doc_mng.receivable_document;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.multipart.MultipartFile;

public interface ReceivableDocumentService {
    public Object getMyDocumentsList(DataTablesInput input, Map<String, String> filters);
    // public Object getMyExecutionsList(DataTablesInput input, Map<String, String> filters);
}
