package com.nsia.officems.edu_publication.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherProfileRepository extends JpaRepository<TeacherProfile, Long>  {
}