package com.nsia.officems._admin.issuing_authority.impl;

import com.nsia.officems._admin.issuing_authority.IssuingAuthority;
import com.nsia.officems._admin.issuing_authority.IssuingAuthorityRepository;
import com.nsia.officems._admin.issuing_authority.IssuingAuthorityService;
import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.base.LookupProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IssuingAuthorityServiceImpl implements IssuingAuthorityService {

    @Autowired
    private IssuingAuthorityRepository issuingAuthorityRepository;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private UserService userService;

    @Override
    public List<IssuingAuthority> findAll() {
        return issuingAuthorityRepository.findByDeletedFalseOrDeletedIsNull();
    }

    @Override
    public IssuingAuthority findById(Long id) {
        Optional<IssuingAuthority> optionalObj = issuingAuthorityRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public List<LookupProjection> getAll() {
        String lang = customUserService.getCurrentLang();
        List<LookupProjection> projection;
        switch (lang) {
            case "ps":
                projection = issuingAuthorityRepository.findAllPs();
                break;
            case "en":
                projection = issuingAuthorityRepository.findAllEn();
                break;
            default:
                projection = issuingAuthorityRepository.findAllDr();
                break;

        }
        return projection;
    }

    @Override
    public LookupProjection getOne(Long id) {
        String lang = customUserService.getCurrentLang();
        LookupProjection projection;
        switch (lang) {
            case "ps":
                projection = issuingAuthorityRepository.findByIdPs(id);
                break;
            case "en":
                projection = issuingAuthorityRepository.findByIdEn(id);
                break;
            default:
                projection = issuingAuthorityRepository.findByIdDr(id);
                break;

        }
        return projection;
    }

    @Override
    public IssuingAuthority create(IssuingAuthority type) {
        return issuingAuthorityRepository.save(type);
    }


    @Override
    public Boolean delete(Long id) {
        Optional<IssuingAuthority> issuingAuth = issuingAuthorityRepository.findById(id);

        if (issuingAuth.isPresent()) {
            IssuingAuthority issuingAuthority = issuingAuth.get();
            issuingAuthority.setDeleted(true);
            issuingAuthority.setDeletedBy(userService.getLoggedInUser().getUsername());
            issuingAuthority.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            issuingAuthorityRepository.save(issuingAuthority);
            return true;
        }

        return false;
    }
}
