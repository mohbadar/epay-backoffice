package com.nsia.officems.hr.profile.education;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

public interface ProfileEducationRepository extends JpaRepository<ProfileEducation, Long>, RevisionRepository<ProfileEducation, Long, Integer>  {
    public List<ProfileEducation> findByDeletedFalseOrDeletedIsNullAndActiveTrue();

    // @Query(value = "SELECT * from public.education ed inner join public.education_level le on ed.education_level=le.id where ed.profile_id=:proId and ed.deleted is not true order by le.id Desc", nativeQuery = true)
    // public List<ProfileEducation> findByProfile_idOrderByLevel_idDesc(@Param("proId") long proId);

    // // public List<PrintEducationDto> findByProfile_id(Long id);

    // @Query(value = "SELECT * from public.education ed inner join public.education_level el on el.id = ed.education_level where ed.profile_id=:proId and ed.deleted is not true order by el.id desc limit 1", nativeQuery = true)
    // public ProfileEducation findbyLatestEducationProfile(@Param("proId") long proId); 

    // public List<ProfileEducation> findByProfileJob_id(Long id);

}
