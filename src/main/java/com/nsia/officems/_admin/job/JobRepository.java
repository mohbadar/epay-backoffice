package com.nsia.officems._admin.job;
import java.util.List;

import com.nsia.officems.base.LookupProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {
    public List<Job> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    @Query(value = "select id as value, name_dr as name from job where id = ?1", nativeQuery = true)
	public LookupProjection findByIdDr(Long id);

	@Query(value = "select id as value, name_ps as name from job where id = ?1", nativeQuery = true)
	public LookupProjection findByIdPs(Long id);

	@Query(value = "select id as value, name_en as name from job where id = ?1", nativeQuery = true)
	public LookupProjection findByIdEn(Long id);

	@Query(value="select id as value, name_dr as name from job", nativeQuery = true)
    public List<LookupProjection> findAllDr();
    @Query(value="select id as value, name_ps as name from job", nativeQuery = true)
    public List<LookupProjection> findAllPs();
    @Query(value="select id as value, name_en as name from job", nativeQuery = true)
    public List<LookupProjection> findAllEn();
}
