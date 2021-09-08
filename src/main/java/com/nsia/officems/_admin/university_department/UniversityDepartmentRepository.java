package com.nsia.officems._admin.university_department;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UniversityDepartmentRepository extends JpaRepository<UniversityDepartment, Long> {
    public List<UniversityDepartment> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    @Query(value = "select id as value, name_dr as name from edu_university_department where faculty_id = ?1", nativeQuery = true)
    public List<LookupProjection> findByFacultyId(Long id);

    @Query(value = "select id as value, name_dr as name from edu_university_department where id = ?1", nativeQuery = true)
    public LookupProjection findByIdDr(Long id);

    @Query(value = "select id as value, name_ps as name from edu_university_department where id = ?1", nativeQuery = true)
    public LookupProjection findByIdPs(Long id);

    @Query(value = "select id as value, name_en as name from edu_university_department where id = ?1", nativeQuery = true)
    public LookupProjection findByIdEn(Long id);

    @Query(value="select id as value, name_dr as name from edu_university_department", nativeQuery = true)
    public List<LookupProjection> findAllDr();
    @Query(value="select id as value, name_ps as name from edu_university_department", nativeQuery = true)
    public List<LookupProjection> findAllPs();
    @Query(value="select id as value, name_en as name from edu_university_department", nativeQuery = true)
    public List<LookupProjection> findAllEn();

    
}
