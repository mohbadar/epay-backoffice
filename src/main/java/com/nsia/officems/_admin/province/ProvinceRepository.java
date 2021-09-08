package com.nsia.officems._admin.province;
import java.util.List;

import com.nsia.officems.base.LookupProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    @Query(value = "select id as value, name_dr as name from province where country_id = ?1", nativeQuery = true)
    public List<LookupProjection> findAllByCountry_id(Long id);

	public List<Province> findByDeletedFalseOrDeletedIsNullAndActiveTrue(); 
    @Query(value = "select id as value, name_dr as name from province where id = ?1", nativeQuery = true)
	public LookupProjection findByIdDr(Long id);

	@Query(value = "select id as value, name_ps as name from province where id = ?1", nativeQuery = true)
	public LookupProjection findByIdPs(Long id);

	@Query(value = "select id as value, name_en as name from province where id = ?1", nativeQuery = true)
	public LookupProjection findByIdEn(Long id);

	@Query(value="select id as value, name_dr as name from province", nativeQuery = true)
    public List<LookupProjection> findAllDr();
    @Query(value="select id as value, name_ps as name from province", nativeQuery = true)
    public List<LookupProjection> findAllPs();
    @Query(value="select id as value, name_en as name from province", nativeQuery = true)
    public List<LookupProjection> findAllEn();
}