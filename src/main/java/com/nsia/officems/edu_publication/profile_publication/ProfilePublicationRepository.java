package com.nsia.officems.edu_publication.profile_publication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePublicationRepository extends JpaRepository<ProfilePublication, Long> {
    public List<ProfilePublication> findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    public List<ProfilePublication> findByProfile_idOrderByIdDesc(Long id);

}
