package com.nsia.officems.edu_publication.profile_research_subject.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.academicGrade.AcademicGradeService;
import com.nsia.officems._admin.publication_type.PublicationTypeService;
import com.nsia.officems._admin.researchSubjectLanguage.ResearchSubjectLanguageService;
import com.nsia.officems._admin.reviewStatus.ReviewStatusService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems.base.LookupProjection;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubject;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubjectRepository;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubjectService;
import com.nsia.officems.edu_publication.profile_research_subject.dto.ResearchSubjectDto;
import com.nsia.officems.edu_publication.profile_research_subject.dto.ResearchSubjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResearchSubjectServiceImpl implements ResearchSubjectService {

    @Autowired
    private ResearchSubjectRepository rSubjectRepository;

    @Autowired
    private PublicationTypeService publicationTypeService;

    @Autowired
    private TeacherProfileService profileService;

    @Autowired
    private ResearchSubjectLanguageService researchSubjectLanguageService;

    @Autowired
    private AcademicGradeService academicGradeService;

    @Autowired
    private ReviewStatusService reviewStatusService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private UserService userService;

    @Override
    public List<ResearchSubject> findAll() {
        return rSubjectRepository.findAll();
    }

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "inner join public.edu_academic_grade ag on ag.id = rs.grade_id INNER JOIN edu_publication_type pt on pt.id = rs.publication_type_id INNER JOIN edu_review_status st on st.id = rs.status_id ";
        // To have first AND with no error
        String whereClause = " rs.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.edu_research_subject rs", null, joinClause, whereClause,
                groupByClause, input);
    }

    @Override
    public List<ResearchSubject> findByProfile_id(Long id) {
        return rSubjectRepository.findByProfile_idOrderByIdDesc(id);
    }

    @Override
    public List<LookupProjection> getResearchSubjectsByProfile(Long id) {
        return rSubjectRepository.findByProfileId(id);
    }

    @Override
    public ResearchSubject findById(Long id) {
        Optional<ResearchSubject> optionalObj = rSubjectRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ResearchSubject sendToCommission(Long id) {
        Optional<ResearchSubject> optionalObj = rSubjectRepository.findById(id);
        if (optionalObj.isPresent()){
            ResearchSubject subject = optionalObj.get();
            subject.setCompletedSubject("Y");
            subject.setComplementStatus(reviewStatusService.findById((long) 1));
            subject.setUpdatedBy(userService.getLoggedInUser().getUsername());
            rSubjectRepository.save(subject);
            return subject;
        }
           
        return null;
    }

    @Override
    public ResearchSubject create(ResearchSubjectDto dto) {
        ResearchSubject newPublication = new ResearchSubject();
        ResearchSubject publication = ResearchSubjectMapper.mapResearchSubjectToDto(newPublication, dto, profileService,
                publicationTypeService, researchSubjectLanguageService, academicGradeService, reviewStatusService);

        // profile.setCreatedBy(userService.getLoggedInUser().getUsername());
        // profile.setEnvSlug(userAuthService.getCurrentEnv());
        if (!publication.equals(null)) {
            publication.setExpireDate(LocalDateTime.now().plusYears(3));
            publication.setCreatedBy(userService.getLoggedInUser().getUsername());
            publication.setCreatedAt(LocalDateTime.now());

            return rSubjectRepository.save(publication);
        }
        return null;
    }

    public Boolean update(Long id, ResearchSubjectDto dto) {
        Optional<ResearchSubject> objection = rSubjectRepository.findById(id);
        if (objection.isPresent()) {
            ResearchSubject publication = ResearchSubjectMapper.mapResearchSubjectToDto(objection.get(), dto,
                    profileService, publicationTypeService, researchSubjectLanguageService, academicGradeService,
                    reviewStatusService);
            if (!publication.equals(null)) {
                publication.setId(id);
                publication.setUpdatedBy(userService.getLoggedInUser().getUsername());
                publication.setUpdatedAt(LocalDateTime.now());
                rSubjectRepository.save(publication);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean updateCommissionDecision(Long id, ResearchSubjectDto obj) {
        Optional<ResearchSubject> objection = rSubjectRepository.findById(id);
        if (objection.isPresent()) {
            ResearchSubject publication = objection.get();
            if (!publication.equals(null)) {
                publication.setId(id);
                publication.setStatus(obj.getStatus() != null ? reviewStatusService.findById(obj.getStatus()) : null);
                publication.setComment(obj.getComment());
                publication.setDecisionDate(LocalDateTime.now());
                publication.setUpdatedBy(userService.getLoggedInUser().getUsername());
                publication.setUpdatedAt(LocalDateTime.now());
                rSubjectRepository.save(publication);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean updateDepartmentDecision(Long id, ResearchSubjectDto obj) {
        Optional<ResearchSubject> objection = rSubjectRepository.findById(id);
        if (objection.isPresent()) {
            ResearchSubject publication = objection.get();
            if (!publication.equals(null)) {
                publication.setId(id);
                publication.setReviewStatus(obj.getStatus() != null ? reviewStatusService.findById(obj.getStatus()).getNameDr() : null);
                publication.setComment(obj.getComment());
                publication.setReviewedAt(LocalDateTime.now());
                publication.setReviewedBy(userService.getLoggedInUser().getUsername());
                rSubjectRepository.save(publication);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean updateCommissionDecisionComplement(Long id, ResearchSubjectDto obj) {
        Optional<ResearchSubject> objection = rSubjectRepository.findById(id);
        if (objection.isPresent()) {
            ResearchSubject publication = objection.get();
            if (!publication.equals(null)) {
                publication.setId(id);
                publication.setComplementStatus(obj.getStatus() != null ? reviewStatusService.findById(obj.getStatus()) : null);
                publication.setComment(obj.getComment());
                publication.setDecisionDate(LocalDateTime.now());
                rSubjectRepository.save(publication);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public String saveAttachment(String uploadDirectory, MultipartFile file) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName = null;
        if (file != null) {
            fileName = formatter.format(Calendar.getInstance().getTime()) + "_" + file.getOriginalFilename();
            String saveDirectory = uploadDirectory;
            File test = new File(saveDirectory);
            if (!test.exists()) {
                test.mkdirs();
            }
            try {
                File f = new File(saveDirectory + "/" + fileName);
                // create the file
                if (!f.exists()) {

                    f.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(f);
                fout.write(file.getBytes());
                fout.close();
            } catch (Exception e) {

            }

        }
        return fileName;
    }

    @Override
    public Boolean updateFileLocation(Long id, String fieldName, String fileLocation, ResearchSubject subject) {

        switch (fieldName) {
            case "suggestion":
                subject.setSuggestion(fileLocation);
                break;
            case "subjectContent":
                subject.setSubjectContent(fileLocation);
                break;
            case "certificate":
                subject.setCertificate(fileLocation);
                break;
            case "administrativeDocuments":
                subject.setAdministrativeDocuments(fileLocation);
                break;
            case "evaluationForm":
                subject.setEvaluationForm(fileLocation);
                break;
            case "subjectMaktob":
                subject.setSubjectMaktob(fileLocation);
                break;
            case "completedSubject":
                subject.setCompletedSubject(fileLocation);
                break;
            case "commissionDecision":
                subject.setCommissionDecision(fileLocation);
                break;
            default:
                subject.setSuggestion(fileLocation);

        }
        rSubjectRepository.save(subject);
        return true;
    }

    @Override
    public File downloadAttachment(Long id, String fieldName, String uploadDir, ResearchSubject subject)
            throws Exception {
        String fileName = subject.getSuggestion();
        switch (fieldName) {
            case "suggestion":
                fileName = subject.getSuggestion();
                break;
            case "subjectContent":
                fileName = subject.getSubjectContent();
                break;
            case "certificate":
                fileName = subject.getCertificate();
                break;
            case "administrativeDocuments":
                fileName = subject.getAdministrativeDocuments();
                break;
            case "evaluationForm":
                fileName = subject.getEvaluationForm();
                break;
            case "subjectMaktob":
                fileName = subject.getSubjectMaktob();
                break;
            case "completedSubject":
                fileName = subject.getCompletedSubject();
                break;
            case "commissionDecision":
                fileName = subject.getCommissionDecision();
                break;
            default:
                fileName = subject.getSuggestion();

        }
        String saveDirectory = uploadDir + "/" + fileName;
        if (new File(saveDirectory).exists()) {
            return new File(saveDirectory);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<ResearchSubject> publication = rSubjectRepository.findById(id);

        if (publication.isPresent()) {
            ResearchSubject publication2 = publication.get();
            publication2.setDeleted(true);
            publication2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            rSubjectRepository.save(publication2);
            return true;
        }

        return false;
    }

    @Override
    public List<ResearchSubject> getSearchSubject(String value) {
        List<ResearchSubject> subject = rSubjectRepository.searchByValue(value);

        return subject;

    }
}
