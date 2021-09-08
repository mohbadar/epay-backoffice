package com.nsia.officems.edu_publication.non_promotional_works;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NonPromotionalWorksRepository extends JpaRepository<NonPromotionalWorks, Long> {
    public List<NonPromotionalWorks> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<NonPromotionalWorks> findByProfile_idOrderByIdDesc(Long id);

    @Query(value = "select * from edu_non_promotional_works where title like %?1% or description like %?1%", nativeQuery = true)
    public List<NonPromotionalWorks> searchByValue(String value);

}
