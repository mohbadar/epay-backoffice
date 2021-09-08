package com.nsia.officems.reception.visitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorPhotoRepository extends JpaRepository<VisitorPhoto, Long>  {
    
}