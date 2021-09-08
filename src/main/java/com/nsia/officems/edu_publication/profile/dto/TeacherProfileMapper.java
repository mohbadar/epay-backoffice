package com.nsia.officems.edu_publication.profile.dto;

import com.nsia.officems._admin.academicGrade.AcademicGradeService;
import com.nsia.officems._admin.country.CountryService;
import com.nsia.officems._admin.district.DistrictService;
import com.nsia.officems._admin.gender.GenderService;
import com.nsia.officems._admin.province.ProvinceService;
import com.nsia.officems._admin.university.UniversityService;
import com.nsia.officems.edu_publication.profile.TeacherProfile;

public class TeacherProfileMapper {

    public static TeacherProfile map(TeacherProfileDto dto, TeacherProfile profile, GenderService genderService, 
    UniversityService universityService, AcademicGradeService academicGradeService,
    CountryService countryService, ProvinceService provinceService, DistrictService districtService)
    {
        if(profile == null)
             profile = new TeacherProfile();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setFatherName(dto.getFatherName());
        profile.setGrandFatherName(dto.getGrandFatherName());
        profile.setDob(dto.getDob());
        profile.setDobGregorian(dto.getDobGregorian());

        profile.setTazkiraNumber(dto.getTazkiraNumber());
        profile.setTazkiraTog(dto.getTazkiraTog());
        profile.setTazkiraRegister(dto.getTazkiraRegister());
        profile.setTazkiraPage(dto.getTazkiraPage());
        profile.setTazkiraDate(dto.getTazkiraDate());
        profile.setTazkiraPlace(dto.getTazkiraPlace());
        profile.setEnid(dto.getEnid());

        profile.setGender(dto.getGender()!=null? genderService.findById(dto.getGender()):null);
        profile.setOriginalCountry(dto.getOriginalCountry()!=null? countryService.findById(dto.getOriginalCountry()):null);
        profile.setOriginalProvince(dto.getOriginalProvince()!=null? provinceService.findById(dto.getOriginalProvince()):null);
        profile.setOriginalDistrict(dto.getOriginalDistrict()!=null? districtService.findById(dto.getOriginalDistrict()):null);

        profile.setBirthCountry(dto.getBirthCountry()!=null? countryService.findById(dto.getBirthCountry()):null);
        profile.setBirthProvince(dto.getBirthProvince()!=null? provinceService.findById(dto.getBirthProvince()):null);
        profile.setBirthDistrict(dto.getBirthDistrict()!=null? districtService.findById(dto.getBirthDistrict()):null);

        profile.setCurrentCountry(dto.getCurrentCountry()!=null? countryService.findById(dto.getCurrentCountry()):null);
        profile.setCurrentProvince(dto.getCurrentProvince()!=null? provinceService.findById(dto.getCurrentProvince()):null);
        profile.setCurrentDistrict(dto.getCurrentDistrict()!=null? districtService.findById(dto.getCurrentDistrict()):null);
        profile.setCurrentUniversity(dto.getCurrentUniversity()!=null? universityService.findById(dto.getCurrentUniversity()):null);
        profile.setPhonNo(dto.getPhonNo());
        profile.setEmail(dto.getEmail());
        profile.setAcademicGrade(dto.getAcademicGrade() != null? academicGradeService.findById(dto.getAcademicGrade()):null);
        return profile;

    }
}
