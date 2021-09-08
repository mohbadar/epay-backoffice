package com.nsia.officems._admin.nationality;

import java.util.List;

public interface NationalityService {

    public List<Nationality> findAll();
    public Nationality findById(Long id);
    public Nationality create(Nationality ethnic);
    public Boolean delete(Long id);
    
}
