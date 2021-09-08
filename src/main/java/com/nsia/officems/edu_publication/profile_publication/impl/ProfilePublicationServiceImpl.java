package com.nsia.officems.edu_publication.profile_publication.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.article_type.ArticleTypeService;
import com.nsia.officems._admin.publicationSource.PublicationSourceService;
import com.nsia.officems._admin.publication_type.PublicationTypeService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._util.DateTimeUtil;
import com.nsia.officems.edu_publication.profile.TeacherProfileService;
import com.nsia.officems.edu_publication.profile_publication.ProfilePublication;
import com.nsia.officems.edu_publication.profile_publication.ProfilePublicationRepository;
import com.nsia.officems.edu_publication.profile_publication.ProfilePublicationService;
import com.nsia.officems.edu_publication.profile_publication.dto.ProfilePublicationDto;
import com.nsia.officems.edu_publication.profile_publication.dto.ProfilePublicationMapperDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class ProfilePublicationServiceImpl implements ProfilePublicationService {

    @Autowired
    private ProfilePublicationRepository publicationRepository;

    @Autowired
    private PublicationTypeService publicationTypeService;

    @Autowired
    private TeacherProfileService profileService;

    @Autowired
    private PublicationSourceService publicationSourceService;

    @Autowired
    private ArticleTypeService articleTypeService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Autowired
    private UserService userService;

    @Override
    public List<ProfilePublication> findAll() {
        return publicationRepository.findAll();
    }

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = "INNER JOIN edu_review_status st on st.id = pb.status_id INNER JOIN edu_article_type art on art.id = pb.article_type_id inner join edu_publication_source ps on ps.id = pb.publication_source_id";
        // To have first AND with no error
        String whereClause = " pb.deleted is not true";
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.edu_teacher_publication pb", null, joinClause, whereClause,
                groupByClause, input);
    }


    @Override
    public List<ProfilePublication> findByProfile_id(Long id) {
        return publicationRepository.findByProfile_idOrderByIdDesc(id);
    }

    @Override
    public ProfilePublication findById(Long id) {
        Optional<ProfilePublication> optionalObj = publicationRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }


    @Override
    public ProfilePublication create(ProfilePublicationDto dto) {
        ProfilePublication newPublication = new ProfilePublication();
        ProfilePublication publication = ProfilePublicationMapperDto.MapPublicationDto(newPublication, dto, profileService,
                publicationTypeService, publicationSourceService, articleTypeService, dateTimeUtil);
        // profile.setCreatedBy(userService.getLoggedInUser().getUsername());
        // profile.setEnvSlug(userAuthService.getCurrentEnv());
        if (!publication.equals(null)) {
            publication.setCreatedBy(userService.getLoggedInUser().getUsername());
            publication.setCreatedAt(LocalDateTime.now());
            return publicationRepository.save(publication);
        }
        return null;
    }

    public Boolean update(Long id, ProfilePublicationDto dto) {
        Optional<ProfilePublication> objection = publicationRepository.findById(id);
        if (objection.isPresent()) {
            ProfilePublication publication = ProfilePublicationMapperDto.MapPublicationDto(objection.get(), dto, profileService,
                    publicationTypeService, publicationSourceService, articleTypeService, dateTimeUtil);
            if (!publication.equals(null)) {
                publication.setUpdatedBy(userService.getLoggedInUser().getUsername());
                publication.setUpdatedAt(LocalDateTime.now());
                publicationRepository.save(publication);
                return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<ProfilePublication> publication = publicationRepository.findById(id);

        if (publication.isPresent()) {
            ProfilePublication publication2 = publication.get();
            publication2.setDeletedBy(userService.getLoggedInUser().getUsername());
            publication2.setDeleted(true);
            publication2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            publicationRepository.save(publication2);
            return true;
        }

        return false;
    }

    
}
