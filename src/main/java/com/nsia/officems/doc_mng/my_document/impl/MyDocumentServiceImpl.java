package com.nsia.officems.doc_mng.my_document.impl;

import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.doc_mng.document.DocumentRepository;
import com.nsia.officems.doc_mng.my_document.MyDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class MyDocumentServiceImpl implements MyDocumentService {

    @Autowired
    private UserService userService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private DocumentRepository documentRepository;

    public Object getMyDocumentsList(DataTablesInput input, Map<String, String> filters) {

        String joinClause = " inner join public.department fdep on fdep.id=doc.from_department_id ";
        joinClause += "left join public.department tdep on tdep.id=doc.to_department_id ";
        joinClause += "inner join public.doc_mng_document_type doc_type on doc_type.id=doc.document_type_id ";
        joinClause += "inner join public.doc_mng_document_status doc_status on doc_status.id=doc.document_status_id ";
        joinClause += " inner join user_tbl usr on usr.id = doc.created_by ";

        Long userId = userService.getLoggedInUser().getId();
        String whereClause =  "doc.status = 'FINAL' and (doc.id in (select dmd.id from doc_mng_document dmd,user_tbl ut where dmd.from_department_id = ut.department_id and ut.id = "+ userId+") or doc.id in " +
                    "(select dmd2.id from doc_mng_document dmd2,user_tbl ut2 where dmd2.to_department_id = ut2.department_id and ut2.id = "+ userId+ ") or doc.id in " +
                    "(select dmdcd.document_id from doc_mng_document_cc_departments dmdcd, user_tbl ut3 where dmdcd.cc_departments_id = ut3.department_id and ut3.id = "+userId+"))";

        String filter = dataTablesUtil.whereClause(filters,"doc");
        if(filter.length() > 0){
            whereClause+=" AND ";
        }
        whereClause +=  filter;

        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document doc", null, joinClause, whereClause, groupByClause,
                input);



    }

    public Object getMyFollowupsList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "  inner join doc_mng_document_follow_up dmdf on dmdf.document_id = dmd.id ";
        joinClause += " inner join department td on td.id = dmd.to_department_id ";
        joinClause += " inner join department fd on fd.id = dmd.from_department_id ";
        joinClause += " inner join doc_mng_document_type doc_type on doc_type.id = dmd.document_type_id ";
        joinClause += " inner join doc_mng_document_status doc_status on doc_status.id=dmd.document_status_id ";
        joinClause += " inner join user_tbl ut on ut.id = dmd.created_by ";

        User currentLoginUser = userService.getLoggedInUser();
        Long departmentId = currentLoginUser.getDepartment().getId();
        String whereClause = "dmdf.assigned_to = " + currentLoginUser.getId().toString() + " or dmdf.department_id = " + departmentId + " ";
        whereClause += dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document dmd", null, joinClause, whereClause,
                groupByClause, input);
    }

    public Object getMyCommentsList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " inner join public.doc_mng_document doc on doc.id=dmdc.document_id ";
        joinClause += " inner join public.doc_mng_document_type doc_type on doc_type.id=doc.document_type_id ";
        joinClause += " inner join public.doc_mng_document_status doc_status on doc_status.id=doc.document_status_id ";
        joinClause += " inner join public.user_tbl usr on usr.id=dmdc.created_by ";

        User currentLoginUser = userService.getLoggedInUser();
        String whereClause = "dmdc.created_by = " + currentLoginUser.getId() + " ";
        whereClause += dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.doc_mng_document_comment dmdc", null, joinClause, whereClause,
                groupByClause, input);
    }

    @Override
    public Map<String, Object> countDocumentData() {
        Map<String,Object> data = new HashMap<String, Object>();
        Long userId = userService.getLoggedInUser().getId();
        data.put("processed", documentRepository.countMyDocuments(new ArrayList<>(List.of(3L)),userId));
        data.put("underProcess",documentRepository.countMyDocuments(new ArrayList<>(List.of(2L)),userId));
        data.put("unprocessed", documentRepository.countMyDocuments(new ArrayList<>(List.of(1L)),userId));
        data.put("all", documentRepository.countMyDocuments(new ArrayList<>(List.of(1L,2L,3L)),userId));
        return data;
    }


}
