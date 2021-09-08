package com.nsia.officems._admin.ethnic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EthnicRepository extends JpaRepository<Ethnic, Long> {
    public List<Ethnic> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
