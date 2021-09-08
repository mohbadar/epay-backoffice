package com.nsia.officems.edu_publication.profile.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.nsia.officems._admin.academicGrade.AcademicGradeService;
import com.nsia.officems._admin.academicPosition.AcademicPositionService;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.district.DistrictService;
import com.nsia.officems._admin.gender.GenderService;
import com.nsia.officems._admin.province.ProvinceService;
import com.nsia.officems._admin.university.UniversityService;
import com.nsia.officems._admin.university_department.UniversityDepartmentService;
import com.nsia.officems._admin.university_faculty.UniversityFacultyService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.FileConverterUtil;
import com.nsia.officems._util.HTMLToPDFCreator;
import com.nsia.officems._util.UriCreator;
import com.nsia.officems.edu_publication.profile.TeacherProfile;
import com.nsia.officems.edu_publication.profile.TeacherProfileRepository;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile.dto.TeacherProfileDto;
import com.nsia.officems.edu_publication.profile.dto.TeacherProfileMapper;
import com.nsia.officems.edu_publication.profile.dto.TeacherProfilePrintDto;
import com.nsia.officems.edu_publication.profile_job.ProfileJob;
import com.nsia.officems.edu_publication.profile_job.ProfileJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TeacherProfileServiceImpl implements TeacherProfileService {

    @Autowired
    private TeacherProfileRepository profileRepository;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private GenderService genderService;

    @Autowired
    UserService userService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private UniversityFacultyService facultyService;

    @Autowired
    private UniversityDepartmentService departmentService;

    @Autowired
    private ProfileJobService profileJobService;

    @Autowired
    private AcademicPositionService academicPositionService;

    @Autowired
    private AcademicGradeService academicGradeService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;


    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "inner join public.edu_university u on u.id = tp.current_university_id INNER JOIN gender g on g.id = tp.gender_id inner join edu_teacher_job tj on tj.profile_id = tp.id inner join edu_academic_position ap on ap.id = tj.position_id INNER JOIN edu_academic_grade ag on ag.id = tp.academic_grade_id";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.edu_teacher_profile tp", null, joinClause, whereClause, groupByClause,
                input);
    }

    @Override
    public TeacherProfile findById(Long id) {
        System.out.println("TeacherProfile.findById()" + id);
        Optional<TeacherProfile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            return profile.get();
        }
        return null;
    }

    public Map<String, Object> findDetailsById(Long id) {
        System.out.println("TeacherProfile.findById()" + id);
        Optional<TeacherProfile> profile = profileRepository.findById(id);
        Map<String, Object> map = new HashMap<>();
        if (profile.isPresent()) {
            TeacherProfile mProfile = profile.get();
            Long pId = mProfile.getId();
            ProfileJob profileJob = profileJobService.findLastJobByProfileId(pId);
            map.put("profile", mProfile);
            map.put("profileJob", profileJob);
            map.put("gender",
                    mProfile.getGender() != null ? genderService.getOne(mProfile.getGender().getId()).getName() : null);
            map.put("academicPosition",
                    profileJob.getPosition().getId() != null
                            ? academicPositionService.getOne(profileJob.getPosition().getId())
                            : null);
            map.put("academicGrade",
                    mProfile.getAcademicGrade().getId() != null
                            ? academicGradeService.getOne(mProfile.getAcademicGrade().getId())
                            : null);
            map.put("university",
                    mProfile.getCurrentUniversity().getId() != null
                            ? universityService.getOne(mProfile.getCurrentUniversity().getId())
                            : null);
            map.put("faculty",
                    profileJob.getFaculty().getId() != null ? facultyService.getOne(profileJob.getFaculty().getId())
                            : null);
            map.put("department", departmentService
                    .getOne(profileJob.getDepartment().getId() != null ? profileJob.getDepartment().getId() : null));
            map.put("birthCountry",
                    mProfile.getBirthCountry() != null
                            ? countryService.getOne(mProfile.getBirthCountry().getId()).getName()
                            : null);
            map.put("originalCountry",
                    mProfile.getOriginalCountry() != null
                            ? countryService.getOne(mProfile.getOriginalCountry().getId()).getName()
                            : null);
            map.put("currentCountry",
                    mProfile.getCurrentCountry() != null
                            ? countryService.getOne(mProfile.getCurrentCountry().getId()).getName()
                            : null);

            map.put("birthProvince",
                    mProfile.getBirthProvince() != null
                            ? provinceService.getOne(mProfile.getBirthProvince().getId()).getName()
                            : null);
            map.put("originalProvince",
                    mProfile.getOriginalProvince() != null
                            ? provinceService.getOne(mProfile.getOriginalProvince().getId()).getName()
                            : null);
            map.put("currentProvince",
                    mProfile.getCurrentProvince() != null
                            ? provinceService.getOne(mProfile.getCurrentProvince().getId()).getName()
                            : null);

            map.put("birthDistrict",
                    mProfile.getBirthDistrict() != null
                            ? districtService.getOne(mProfile.getBirthDistrict().getId()).getName()
                            : null);
            map.put("originalDistrict",
                    mProfile.getOriginalDistrict() != null
                            ? districtService.getOne(mProfile.getOriginalDistrict().getId()).getName()
                            : null);
            map.put("currentDistrict",
                    mProfile.getCurrentDistrict() != null
                            ? districtService.getOne(mProfile.getCurrentDistrict().getId()).getName()
                            : null);

        }
        return map;
    }

    @Override
    public Map<String, Object> create(TeacherProfileDto profileDto) {
        Map<String, Object> data = new HashMap<>();
        if (profileDto == null) {
            data.put("status", HttpStatus.NOT_FOUND);
        } else {
            data.put("status", HttpStatus.FOUND);
            TeacherProfile profile = new TeacherProfile();
            profile = TeacherProfileMapper.map(profileDto, profile, genderService, universityService,
                    academicGradeService, countryService, provinceService, districtService);
            profile.setCreatedBy(userService.getLoggedInUser());
            profile.setCreatedAt(LocalDateTime.now());

            profile = profileRepository.save(profile);

            data.put("createdId", profile.getId());

        }
        return data;
    }

    @Override
    public Map<String, Object> update(TeacherProfileDto profileDto, Long id) {
        Map<String, Object> data = new HashMap<>();
        if (profileDto == null) {
            data.put("status", HttpStatus.NOT_FOUND);
        } else {

            Optional<TeacherProfile> opProfile = profileRepository.findById(id);
            if (opProfile.isPresent()) {
                TeacherProfile profile = opProfile.get();
                profile = TeacherProfileMapper.map(profileDto, profile, genderService, universityService,
                        academicGradeService, countryService, provinceService, districtService);
                profile.setUpdatedAt(LocalDateTime.now());

                profile = profileRepository.save(profile);
                data.put("updatedId", profile.getId());
                data.put("status", HttpStatus.FOUND);

            } else {
                data.put("status", HttpStatus.NOT_FOUND);

            }

        }
        return data;
    }

    @Override
    public File printSummary(Long id) throws IOException, InterruptedException {
        Optional<TeacherProfile> opTeacher = profileRepository.findById(id);
       

        return null;
    }
}
