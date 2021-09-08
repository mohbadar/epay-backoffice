package com.nsia.officems.edu_publication.profile_job.Impl;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._admin.academicPosition.AcademicPositionService;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.university.UniversityService;
import com.nsia.officems._admin.university_department.UniversityDepartment;
import com.nsia.officems._admin.university_department.UniversityDepartmentService;
import com.nsia.officems._admin.university_faculty.UniversityFaculty;
import com.nsia.officems._admin.university_faculty.UniversityFacultyService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_job.ProfileJob;
import com.nsia.officems.edu_publication.profile_job.ProfileJobRepository;
import com.nsia.officems.edu_publication.profile_job.ProfileJobService;
import com.nsia.officems.edu_publication.profile_job.Dto.ProfileJobDto;
import com.nsia.officems.edu_publication.profile_job.Dto.ProfileJobMapper;

import org.springframework.stereotype.Service;

@Service
public class ProfileJobServiceImpl implements ProfileJobService{

    @Autowired
    private ProfileJobRepository profileJobRepository;

    @Autowired
    private TeacherProfileService profileService;

    @Autowired
    UserService userService;

    @Autowired
    UniversityService universityService;

    @Autowired 
    UniversityFacultyService universityFacultyService;

    @Autowired
    UniversityDepartmentService universityDepartmentService;

    @Autowired
    private AcademicPositionService academicPositionService;

    @Override
    public List<ProfileJob> findAll() {
        return profileJobRepository.findAll();
    }

    @Override
    public ProfileJob findById(Long id) {
        Optional<ProfileJob> optionalObj = profileJobRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public List<ProfileJob> findByProfile_id(Long id) {
        return profileJobRepository.findByProfile_idOrderByIdDesc(id);
    }

    @Override
    public ProfileJob findLastJobByProfileId(Long id) {
        return profileJobRepository.findLastByProfileId(id);
    }

    @Override
    public ProfileJob create(ProfileJobDto dto) {
        ProfileJob newProfileJob = new ProfileJob();
        ProfileJob profileJob = ProfileJobMapper.MapProfileJobDto(newProfileJob, dto, profileService, universityService, universityFacultyService, universityDepartmentService, academicPositionService);

        if (!profileJob.equals(null)) {
            profileJob.setCreatedAt(LocalDateTime.now());
            profileJob.setCreatedBy(userService.getLoggedInUser());
            return profileJobRepository.save(profileJob);
        }
        return null;
    }

    public Boolean update(Long id, ProfileJobDto dto) {
        Optional<ProfileJob> objection = profileJobRepository.findById(id);
        if (objection.isPresent()) {
            ProfileJob profileJob = ProfileJobMapper.MapProfileJobDto(objection.get(), dto, profileService, universityService, universityFacultyService, universityDepartmentService, academicPositionService);
            if(!profileJob.equals(null))
            {
                profileJob.setUpdatedAt(LocalDateTime.now());
                profileJobRepository.save(profileJob);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<ProfileJob> education = profileJobRepository.findById(id);

        if (education.isPresent()) {
            ProfileJob education2 = education.get();
            education2.setDeletedBy(userService.getLoggedInUser());
            education2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            profileJobRepository.save(education2);
            return true;
        }

        return false;
    }
    
}
