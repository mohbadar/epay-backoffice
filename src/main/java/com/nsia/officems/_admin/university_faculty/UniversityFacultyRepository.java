package com.nsia.officems._admin.university_faculty;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UniversityFacultyRepository extends JpaRepository<UniversityFaculty, Long> {
    public List<UniversityFaculty> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    @Query(value = "select id as value, name_dr as name from edu_university_faculty where university_id = ?1", nativeQuery =true)
    public List<LookupProjection> findByUniversityId(Long id);

    @Query(value="select id as value, name_dr as name from edu_university_faculty where id = ?1", nativeQuery = true)
    public LookupProjection findByIdDr(Long id);
    @Query(value="select id as value, name_ps as name from edu_university_faculty where id = ?1", nativeQuery = true)
    public LookupProjection findByIdPs(Long id);
    @Query(value="select id as value, name_en as name from edu_university_faculty where id = ?1", nativeQuery = true)
    public LookupProjection findByIdEn(Long id);

    @Query(value="select id as value, name_dr as name from edu_university_faculty", nativeQuery = true)
    public List<LookupProjection> findAllDr();
    @Query(value="select id as value, name_ps as name from edu_university_faculty", nativeQuery = true)
    public List<LookupProjection> findAllPs();
    @Query(value="select id as value, name_en as name from edu_university_faculty", nativeQuery = true)
    public List<LookupProjection> findAllEn();
    
}
