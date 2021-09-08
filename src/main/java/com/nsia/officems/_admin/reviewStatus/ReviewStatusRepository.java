package com.nsia.officems._admin.reviewStatus;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewStatusRepository extends JpaRepository<ReviewStatus, Long> {

	public List<ReviewStatus> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

	@Query(value = "select id as value, name_dr as name from edu_review_status where id = ?1", nativeQuery = true)
	public LookupProjection findByIdDr(Long id);

	@Query(value = "select id as value, name_ps as name from edu_review_status where id = ?1", nativeQuery = true)
	public LookupProjection findByIdPs(Long id);

	@Query(value = "select id as value, name_en as name from edu_review_status where id = ?1", nativeQuery = true)
	public LookupProjection findByIdEn(Long id);

	@Query(value="select id as value, name_dr as name from edu_review_status", nativeQuery = true)
    public List<LookupProjection> findAllDr();
    @Query(value="select id as value, name_ps as name from edu_review_status", nativeQuery = true)
    public List<LookupProjection> findAllPs();
    @Query(value="select id as value, name_en as name from edu_review_status", nativeQuery = true)
    public List<LookupProjection> findAllEn();

}
