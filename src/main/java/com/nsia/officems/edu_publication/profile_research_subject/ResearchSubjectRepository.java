package com.nsia.officems.edu_publication.profile_research_subject;

import java.util.List;

import com.nsia.officems.base.LookupProjection;
import com.nsia.officems.edu_publication.profile_research_subject.dto.ResearchSubjectDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResearchSubjectRepository extends JpaRepository<ResearchSubject, Long> {
    public List<ResearchSubject> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<ResearchSubject> findByProfile_idOrderByIdDesc(Long id);

    @Query(value = "select * from edu_research_subject where title like %?1% or description like %?1%", nativeQuery = true)
    public List<ResearchSubject> searchByValue(String value);

    @Query(value = "select id as value, title as name from edu_research_subject where profile_id = ?1 and (completed_subject != 'Y' or completed_subject is null)", nativeQuery = true)
    public List<LookupProjection> findByProfileId(Long id);

}
