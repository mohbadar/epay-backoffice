package com.nsia.officems._admin.education_level;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EducationLevelRepository extends JpaRepository<EducationLevel, Long> {
    public List<EducationLevel> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    @Query(value = "select id as value, name_dr as name from edu_education_level where id = ?1", nativeQuery = true)
	public LookupProjection findByIdDr(Long id);

	@Query(value = "select id as value, name_ps as name from edu_education_level where id = ?1", nativeQuery = true)
	public LookupProjection findByIdPs(Long id);

	@Query(value = "select id as value, name_en as name from edu_education_level where id = ?1", nativeQuery = true)
	public LookupProjection findByIdEn(Long id);

	@Query(value="select id as value, name_dr as name from edu_education_level", nativeQuery = true)
    public List<LookupProjection> findAllDr();
    @Query(value="select id as value, name_ps as name from edu_education_level", nativeQuery = true)
    public List<LookupProjection> findAllPs();
    @Query(value="select id as value, name_en as name from edu_education_level", nativeQuery = true)
    public List<LookupProjection> findAllEn();

}
