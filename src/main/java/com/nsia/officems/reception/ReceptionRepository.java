package com.nsia.officems.reception;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception, Long>  {
    
}