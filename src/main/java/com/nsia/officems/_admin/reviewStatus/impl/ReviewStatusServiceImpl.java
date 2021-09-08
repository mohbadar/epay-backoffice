package com.nsia.officems._admin.reviewStatus.impl;


import com.nsia.officems._admin.reviewStatus.ReviewStatus;
import com.nsia.officems._admin.reviewStatus.ReviewStatusRepository;
import com.nsia.officems._admin.reviewStatus.ReviewStatusService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems.base.LookupProjection;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewStatusServiceImpl implements ReviewStatusService{
    @Autowired
    private ReviewStatusRepository reviewStatusRepository;

    @Autowired
    private CustomUserService userService;

    @Override
    public List<ReviewStatus> findAll() {
        return reviewStatusRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public List<LookupProjection> getAll() {
        String lang = userService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = reviewStatusRepository.findAllPs();
                break;
            case "en":
                projection = reviewStatusRepository.findAllEn();
                break;
            default:
                projection = reviewStatusRepository.findAllDr();
                break;

        }
        return projection;
    }

    @Override
    public LookupProjection getOne(Long id) {
        String lang = userService.getCurrentLang();
        LookupProjection projection;
        switch (lang) {
            case "ps":
                projection = reviewStatusRepository.findByIdPs(id);
                break;
            case "en":
                projection = reviewStatusRepository.findByIdEn(id);
                break;
            default:
                projection = reviewStatusRepository.findByIdDr(id);
                break;

        }
        return projection;     
    }
    @Override
    public ReviewStatus findById(Long id) {
        Optional<ReviewStatus> optionalObj = reviewStatusRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public ReviewStatus create(ReviewStatus gender) {
        return reviewStatusRepository.save(gender);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<ReviewStatus> gender = reviewStatusRepository.findById(id);

        if (gender.isPresent()) {
            ReviewStatus gender2 = gender.get();
            gender2.setDeleted(true);
            // language2.setDeletedBy(userService.getLoggedInUser().getUsername());
            gender2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            reviewStatusRepository.save(gender2);
            return true;
        }

        return false;
    } 
}
