package com.nsia.officems.reception.visitor;

public interface VisitorPhotoService {
    public VisitorPhoto save(VisitorPhoto visitor);
    public VisitorPhoto findById(Long id);
    public boolean delete(long id);
}
