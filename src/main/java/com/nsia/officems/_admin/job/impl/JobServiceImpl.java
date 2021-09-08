package com.nsia.officems._admin.job.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nsia.officems._identity.authentication.user.CustomUserService;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems.base.LookupProjection;
import com.nsia.officems._admin.job.Job;
import com.nsia.officems._admin.job.JobRepository;
import com.nsia.officems._admin.job.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    UserService userService;

    @Autowired
    CustomUserService customUserService;

    @Override
    public List<Job> findAll() {
        return jobRepository.findByDeletedFalseOrDeletedIsNullAndActiveTrue();
    }

    @Override
    public Job findById(Long id) {
        Optional<Job> optionalObj = jobRepository.findById(id);
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
                projection = jobRepository.findAllPs();
                break;
            case "en":
                projection = jobRepository.findAllEn();
                break;
            default:
                projection = jobRepository.findAllDr();
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
                projection = jobRepository.findByIdPs(id);
                break;
            case "en":
                projection = jobRepository.findByIdEn(id);
                break;
            default:
                projection = jobRepository.findByIdDr(id);
                break;

        }
        return projection;     
    }
    @Override
    public Job create(Job job) {
        job.setDeleted(false);
        job.setCreatedBy(userService.getLoggedInUser().getUsername());
        return jobRepository.save(job);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Job> oJob = jobRepository.findById(id);

        if (oJob.isPresent()) {
            Job job = oJob.get();
            job.setDeleted(true);
            job.setDeletedBy(userService.getLoggedInUser().getUsername());
            job.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            jobRepository.save(job);
            return true;
        }

        return false;
    }

    @Override
    public Job update(Long id, Job job) {
        if(id != null ) {
            Optional<Job> newJob = jobRepository.findById(id);
            if(newJob.isPresent())
            {   Job updatedJob = newJob.get();
                updatedJob.setNameDr(job.getNameDr());
                updatedJob.setNamePs(job.getNamePs());
                updatedJob.setNameEn(job.getNameEn());
                return jobRepository.save(updatedJob);
            }
            return null;
            
        }

        return null;
    }
}
