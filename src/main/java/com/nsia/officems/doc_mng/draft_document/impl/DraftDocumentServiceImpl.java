package com.nsia.officems.doc_mng.draft_document.impl;

import java.util.Map;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.doc_mng.draft_document.DraftDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class DraftDocumentServiceImpl implements DraftDocumentService {

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private UserService userService;

    public Object getMyDocumentsList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.department fdep on fdep.id=doc.from_department_id ";
        joinClause += " left join public.department tdep on tdep.id=doc.to_department_id ";
        joinClause += " inner join public.doc_mng_document_type doc_type on doc_type.id=doc.document_type_id ";
        joinClause += " inner join public.doc_mng_document_status doc_status on doc_status.id=doc.document_status_id ";
        joinClause += " inner join public.user_tbl usr on usr.id=doc.created_by ";
        joinClause += " left join public.doc_mng_document_reviews doc_reviews on doc_reviews.document_id=doc.id ";
        joinClause += " left join public.doc_mng_document_review doc_review on doc_review.id=doc_reviews.reviews_id ";

        User currentLoginUser = userService.getLoggedInUser();
        String whereClause = " doc.status != 'FINAL' and ";
        whereClause += " (doc.created_by=" + currentLoginUser.getId() + " OR (doc_review.reviewer=" + currentLoginUser.getId() + " and doc_review.decision='PENDING')) ";

        String filter = dataTablesUtil.whereClause(filters,"doc");
        if(filter.length() > 0){
            whereClause+=" AND ";
        }
        whereClause +=  filter;
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document doc", null, joinClause, whereClause, groupByClause,
                input);
    }

    public Object getMyExecutionsList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.doc_mng_document doc on doc.id=dmde.document_id ";
        joinClause += " inner join public.department fdep on fdep.id=dmde.from_department_id ";
        joinClause += " left join public.department tdep on tdep.id=dmde.to_department_id ";
        joinClause += " inner join public.doc_mng_document_execution_type dmdet on dmdet.id=dmde.document_execution_type_id ";
        joinClause += " inner join public.doc_mng_document_type doc_type on doc_type.id=doc.document_type_id ";
        joinClause += " inner join public.doc_mng_document_status doc_status on doc_status.id=doc.document_status_id ";
        joinClause += " inner join public.user_tbl usr on usr.id=dmde.created_by ";
        joinClause += " left join public.doc_mng_document_execution_reviews doc_ex_reviews on doc_ex_reviews.document_execution_id=doc.id ";
        joinClause += " left join public.doc_mng_document_execution_review doc_ex_review on doc_ex_review.id=doc_ex_reviews.reviews_id ";

        User currentLoginUser = userService.getLoggedInUser();
        String whereClause = "doc.status != 'FINAL' and ";
        whereClause += " (doc.created_by=" + currentLoginUser.getId() + " OR doc_ex_review.reviewer=" + currentLoginUser.getId() + ") ";
        whereClause += dataTablesUtil.whereClause(filters);

        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document_execution dmde", null, joinClause, whereClause,
                groupByClause, input);
    }
}
