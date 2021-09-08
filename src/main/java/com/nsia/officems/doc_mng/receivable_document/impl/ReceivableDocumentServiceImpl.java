package com.nsia.officems.doc_mng.receivable_document.impl;

import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.doc_mng.receivable_document.ReceivableDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class ReceivableDocumentServiceImpl implements ReceivableDocumentService {

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private UserService userService;

    public Object getMyDocumentsList(DataTablesInput input, Map<String, String> filters) {
        User currentLoginUser = userService.getLoggedInUser();
        List<Long> accessableDepartmentIds = userService.getAccessableDepartmentIds(currentLoginUser);
        String depIds = StringUtils.join(accessableDepartmentIds, ',');
        String joinClause = " inner join public.department fdep on fdep.id=doc.from_department_id ";
        joinClause += " inner join public.department tdep on tdep.id=doc.to_department_id ";
        joinClause += " inner join public.doc_mng_document_type doc_type on doc_type.id=doc.document_type_id ";
        joinClause += " inner join public.doc_mng_document_status doc_status on doc_status.id=doc.document_status_id ";
        joinClause += " inner join public.user_tbl usr on usr.id=doc.created_by ";
        joinClause += " left join public.doc_mng_document_receives doc_receives on doc_receives.document_id=doc.id ";
        joinClause += " left join public.doc_mng_document_receive doc_receive on doc_receive.id=doc_receives.receives_id ";

        String whereClause = " doc_receive.decision = 'PENDING' and doc_receive.department_id in(" + depIds + ") ";

        String filter = dataTablesUtil.whereClause(filters,"doc");
        if(filter.length() > 0){
            whereClause+=" AND ";
        }
        whereClause +=  filter;

        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document doc", null, joinClause, whereClause, groupByClause,
                input);
    }

    // public Object getMyExecutionsList(DataTablesInput input, Map<String, String> filters) {
    //     User currentLoginUser = userService.getLoggedInUser();
    //     List<Long> accessableDepartmentIds = userService.getAccessableDepartmentIds(currentLoginUser);
    //     String depIds = StringUtils.join(accessableDepartmentIds, ',');

    //     String joinClause = " inner join public.doc_mng_document doc on doc.id=dmde.document_id ";
    //     joinClause += " inner join public.department fdep on fdep.id=dmde.from_department_id ";
    //     joinClause += " inner join public.department tdep on tdep.id=dmde.to_department_id ";
    //     joinClause += " inner join public.doc_mng_document_execution_type dmdet on dmdet.id=dmde.document_execution_type_id ";
    //     joinClause += " inner join public.doc_mng_document_type doc_type on doc_type.id=doc.document_type_id ";
    //     joinClause += " inner join public.doc_mng_document_status doc_status on doc_status.id=doc.document_status_id ";
    //     joinClause += " inner join public.user_tbl usr on usr.id=dmde.created_by ";
    //     joinClause += " left join public.doc_mng_document_execution_receives doc_ex_receives on doc_ex_receives.document_execution_id=doc.id ";
    //     joinClause += " left join public.doc_mng_document_execution_receive doc_ex_receive on doc_ex_receive.id=doc_ex_receives.receives_id ";

    //     String whereClause = " doc_ex_receive.received is not true and doc_ex_receive.department_id in(" + depIds + ") ";
    //     whereClause += dataTablesUtil.whereClause(filters);

    //     String groupByClause = "";
    //     return dataTablesUtil.getDataList("public.doc_mng_document_execution dmde", null, joinClause, whereClause,
    //             groupByClause, input);
    // }
}
