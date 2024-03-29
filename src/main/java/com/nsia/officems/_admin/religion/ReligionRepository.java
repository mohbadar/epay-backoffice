package com.nsia.officems._admin.religion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReligionRepository extends JpaRepository<Religion, Long> {
    public List<Religion> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
}
