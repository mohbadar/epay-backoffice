package com.nsia.officems.edu_publication.profile_job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileJobRepository  extends JpaRepository<ProfileJob, Long> {
    public List<ProfileJob> findByProfile_idOrderByIdDesc(Long id);

    @Query(value = "select * from edu_teacher_job where profile_id = ?1 order by id DESC limit 1", nativeQuery = true)
    public ProfileJob findLastByProfileId(Long id);

}
