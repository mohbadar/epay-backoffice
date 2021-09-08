package com.nsia.officems.library.publication.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.library.publication.Publication;
import com.nsia.officems.library.publication.PublicationService;
import com.nsia.officems.library.publication.PublicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository  publicationRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.publication pub", null, joinClause, whereClause, groupByClause, input);
    }

    public Publication findById(Long id) {
        System.out.println(" Reception.findById()" + id);
        Optional<Publication> objection = publicationRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Publication: " + objection);
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<Publication> document = publicationRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public Publication save(Publication obj) {
        return this.publicationRepository.save(obj);
    }

    @Override
    public Publication create(String data) throws JSONException {
        JSONObject obj = new JSONObject(data);
        // TODO Auto-generated method stub
        return null;
    }


}
