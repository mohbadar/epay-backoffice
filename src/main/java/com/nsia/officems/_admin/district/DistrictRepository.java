package com.nsia.officems._admin.district;

import java.util.List;

import com.nsia.officems.base.LookupProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistrictRepository extends JpaRepository<District, Long> {

    public List<District> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    @Query(value = "select id as value, name_dr as name from district where province_id = ?1", nativeQuery = true)
	public List<LookupProjection> findByProvince_idDr(Long id);

    @Query(value = "select id as value, name_ps as name from district where province_id = ?1", nativeQuery = true)
    public List<LookupProjection> findByProvince_idPs(Long id);

    @Query(value = "select id as value, name_en as name from district where province_id = ?1", nativeQuery = true)
    public List<LookupProjection> findByProvince_idEn(Long id);



    @Query(value = "select id as value, name_dr as name from district where id = ?1", nativeQuery = true)
	public LookupProjection findByIdDr(Long id);

	@Query(value = "select id as value, name_ps as name from district where id = ?1", nativeQuery = true)
	public LookupProjection findByIdPs(Long id);

	@Query(value = "select id as value, name_en as name from district where id = ?1", nativeQuery = true)
	public LookupProjection findByIdEn(Long id);

	@Query(value="select id as value, name_dr as name from district", nativeQuery = true)
    public List<LookupProjection> findAllDr();
    @Query(value="select id as value, name_ps as name from district", nativeQuery = true)
    public List<LookupProjection> findAllPs();
    @Query(value="select id as value, name_en as name from district", nativeQuery = true)
    public List<LookupProjection> findAllEn();
}
