package com.nsia.officems.edu_publication.profile.dto;    
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import com.nsia.officems.edu_publication.non_promotional_works.NonPromotionalWorks;
import com.nsia.officems.edu_publication.profile.TeacherProfile;
import com.nsia.officems.edu_publication.profile_education.Education;
import com.nsia.officems.edu_publication.profile_job.ProfileJob;
import com.nsia.officems.edu_publication.profile_publication.ProfilePublication;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TeacherProfilePrintDto {

    private TeacherProfile profile;
    private List<Education> education;
    private List<ProfileJob> profileJobs;
    private List<ProfilePublication> profilePublications;
    private List<ResearchSubject> researchSubjects;
    private List<NonPromotionalWorks> nonPromotionalWorks;
    
}
