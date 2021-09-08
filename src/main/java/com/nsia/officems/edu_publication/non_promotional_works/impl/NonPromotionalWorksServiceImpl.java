package com.nsia.officems.edu_publication.non_promotional_works.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.reviewStatus.ReviewStatusService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.DateTimeUtil;
import com.nsia.officems.edu_publication.non_promotional_works.NonPromotionalWorks;
import com.nsia.officems.edu_publication.non_promotional_works.NonPromotionalWorksRepository;
import com.nsia.officems.edu_publication.non_promotional_works.NonPromotionalWorksService;
import com.nsia.officems.edu_publication.non_promotional_works.dto.NonPromotionalWorksDto;
import com.nsia.officems.edu_publication.non_promotional_works.dto.NonPromotionalWorksMapper;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubject;
import com.nsia.officems.edu_publication.profile_research_subject.ResearchSubjectService;

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
public class NonPromotionalWorksServiceImpl implements NonPromotionalWorksService {

    @Autowired
    private NonPromotionalWorksRepository nonPromotionalRepository;

   
    @Autowired
    private TeacherProfileService profileService;

    @Autowired
    private ReviewStatusService reviewStatusService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private ResearchSubjectService researchSubjectService;

    @Override
    public List<NonPromotionalWorks> findAll() {
        return nonPromotionalRepository.findAll();
    }

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "INNER JOIN edu_review_status st on st.id = npw.status_id ";
        // To have first AND with no error
        String whereClause = " npw.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.edu_non_promotional_works npw", null, joinClause, whereClause,
                groupByClause, input);
    }

    @Override
    public List<NonPromotionalWorks> findByProfile_id(Long id) {
        return nonPromotionalRepository.findByProfile_idOrderByIdDesc(id);
    }

    @Override
    public NonPromotionalWorks findById(Long id) {
        Optional<NonPromotionalWorks> optionalObj = nonPromotionalRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public NonPromotionalWorks create(NonPromotionalWorksDto dto) {
        NonPromotionalWorks newPublication = new NonPromotionalWorks();
        NonPromotionalWorksMapper.mapNonPromotionalWorksToDto(newPublication, dto, profileService,
                 reviewStatusService, researchSubjectService, dateTimeUtil);

        // profile.setCreatedBy(userService.getLoggedInUser().getUsername());
        // profile.setEnvSlug(userAuthService.getCurrentEnv());
        if (newPublication != null) {
            newPublication.setCreatedAt(LocalDateTime.now());
            newPublication.setCreatedBy(userService.getLoggedInUser().getUsername());
            return nonPromotionalRepository.save(newPublication);
        }
        return null;
    }

    public Boolean update(Long id, NonPromotionalWorksDto dto) {
        Optional<NonPromotionalWorks> objection = nonPromotionalRepository.findById(id);
        if (objection.isPresent()) {
            NonPromotionalWorks publication = NonPromotionalWorksMapper.mapNonPromotionalWorksToDto(objection.get(), dto,
                    profileService, reviewStatusService, researchSubjectService, dateTimeUtil);
            if (!publication.equals(null)) {
                publication.setUpdatedAt(LocalDateTime.now());
                publication.setUpdatedBy(userService.getLoggedInUser().getUsername());
                publication.setId(id);
                nonPromotionalRepository.save(publication);
                return true;
            }
            return false;
        }

        return false;
    }

    public Boolean updateCommissionDecision(Long id, NonPromotionalWorksDto obj) {
        Optional<NonPromotionalWorks> objection = nonPromotionalRepository.findById(id);
        if (objection.isPresent()) {
            NonPromotionalWorks publication = objection.get();
            if (!publication.equals(null)) {
                publication.setId(id);
                publication.setStatus( obj.getStatus() != null ? reviewStatusService.findById(obj.getStatus()): null);
                publication.setComment(obj.getComment());
                publication.setDecisionDate(LocalDateTime.now());
                nonPromotionalRepository.save(publication);
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
    public Boolean updateFileLocation(Long id, String fieldName, String fileLocation, NonPromotionalWorks subject) {
    
        switch (fieldName) {
            case "tableOfContent":
                subject.setTableOfContent(fileLocation);
                break;
            case "commissionDecision":
                subject.setCommissionDecision(fileLocation);
                break;
            default:
                subject.setTableOfContent(fileLocation);

        }
        nonPromotionalRepository.save(subject);
        return true;
    }

    @Override
    public File downloadAttachment(Long id, String fieldName, String uploadDir,  NonPromotionalWorks subject) throws Exception {
        String fileName = subject.getTableOfContent();
        switch (fieldName) {
            case "tableOfContent":
                fileName = subject.getTableOfContent();
                break;
            case "commissionDecision":
                fileName = subject.getCommissionDecision();
                break;
            default:
                fileName = subject.getTableOfContent();

        }
        String saveDirectory = uploadDir + "/" + fileName;
        if (new File(saveDirectory).exists()) {
            return new File(saveDirectory);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<NonPromotionalWorks> publication = nonPromotionalRepository.findById(id);

        if (publication.isPresent()) {
            NonPromotionalWorks publication2 = publication.get();
            publication2.setDeleted(true);
            publication2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            nonPromotionalRepository.save(publication2);
            return true;
        }

        return false;
    }

    @Override
    public List<NonPromotionalWorks> getSearchSubject(String value){
        List<NonPromotionalWorks> subject = nonPromotionalRepository.searchByValue(value);

        return subject;

    }
}
