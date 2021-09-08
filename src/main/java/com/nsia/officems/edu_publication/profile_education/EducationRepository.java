package com.nsia.officems.edu_publication.profile_education;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository  extends JpaRepository<Education, Long> {
    public List<Education> findByProfile_idOrderByLevel_idDesc(Long id);

}
