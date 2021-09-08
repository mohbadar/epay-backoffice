package com.nsia.officems.reception.impl;

import com.nsia.officems.reception.Reception;
import com.nsia.officems.reception.ReceptionRepository;
import com.nsia.officems.reception.ReceptionService;

import java.util.Map;
import java.util.Optional;

import com.nsia.officems._util.DataTablesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class ReceptionServiceImpl implements ReceptionService {
    @Autowired
    private ReceptionRepository  receptionRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.profile pro", null, joinClause, whereClause, groupByClause, input);
    }

    public Reception findById(Long id) {
        System.out.println(" Reception.findById()" + id);
        Optional<Reception> objection = receptionRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Reception: " + objection);
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Reception> document = receptionRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Reception save(Reception obj) {
        return this.receptionRepository.save(obj);
    }

    @Override
    public Long create(String data) throws JSONException {
        JSONObject obj = new JSONObject(data);
        // TODO Auto-generated method stub
        return null;
    }
}
