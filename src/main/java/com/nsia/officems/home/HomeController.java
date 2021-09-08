package com.nsia.officems.home;


import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
// import com.nsia.officems.complaint.ComplaintService;
// import com.nsia.officems.decree.DecreeService;
// import com.nsia.officems.profile.ProfileService;
// import com.nsia.officems.profile_education.Dto.EducationDto;
// import com.nsia.officems.proposal.ProposalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/homes")
public class HomeController {

    // @Autowired
    // private ProfileService profileService;

    // @Autowired
    // private ProposalService proposalService;


    // @Autowired
    // private DecreeService decreeService;

    // @Autowired
    // private ComplaintService complaintService;

  //   @GetMapping(value = "/count")
  //   public ResponseEntity<Map<String, Object>> countData() {
  //       Map<String, Object> data = new HashMap<String, Object>();
  //       long profile = profileService.count();
  //       long proposal = proposalService.count();
  //       long decree = decreeService.count();
  //       long complaint = complaintService.count();

  //       data.put("profile", profile);
  //       data.put("proposal", proposal);
  //       data.put("decree", decree);
  //       data.put("complaint", complaint);
  //       return ResponseEntity.ok(data);
  //   }


  //   @GetMapping(path = {"/ethnic"})
  //   public List getProfileCountByEthnic() {
	// 	return profileService.getProfileCountByEthnic();
  //   }
    
  //   @GetMapping(path = {"/gender"})
  //   public List getProfileCountByGender() {
  //   return profileService.getProfileCountByGender();
  // }
    
  //   @GetMapping(path = {"/education"})
  //   public List getProfileEducationCount() {
  //   return profileService.getProfileCountEducation();
  // }
  
  // @GetMapping(path = {"/ministry"})
  //   public List getProfileMinistryCount() {
  //   return profileService.getProfileCountMinistry();
  // }
  
  // @GetMapping(path = {"/authority"})
  //   public List getProfileAuthorityCount() {
  //   return profileService.getProfileCountAuthority();
  // }

  // @GetMapping(path = {"/commission"})
  //   public List getProfileCommissionCount() {
  //   return profileService.getProfileCountCommission();
  // }
  
  // @GetMapping(path = {"/age"})
  //   public List getProfileAgeCount() {
  //   return profileService.getProfileCountAge();
	// }
    
}
