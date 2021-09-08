package com.nsia.officems.doc_mng.dashboard;

import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.transportation.request.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doc_mng/dashboard")
public class DocumentDashboardController {

    @Autowired
    private DocumentService documentService;
    
    @GetMapping(value = "/count")
    public ResponseEntity<Map<String, Object>> countData() {
        return ResponseEntity.ok(documentService.countDashboardData());
    }


    @GetMapping(value = "/mydashboard/count")
    public ResponseEntity<Map<String, Object>> countMyDashboardData() {
        return ResponseEntity.ok(documentService.countMyDashboardData());
    }
    @GetMapping(path = { "/entity" })
    public List getDocumentCountByEntity() {
        return documentService.getDocumentCountbyEntity();
    }

    @GetMapping("/count/typeId")
    public List getDocumentCountByTypeId(@RequestParam("pId") Long pId ) {
        return documentService.getDocumentCountByTypeId(pId);    
    }

    @GetMapping("/count/type/typeId")
    public List getDocumentCountbyEntityByTypeId(@RequestParam("pId") Long pId ) {
        return documentService.getDocumentCountbyEntityByTypeId(pId);    
    }


    @GetMapping(path = { "/mydashboard/entity" })
    public List getMyDashboardDocumentCountbyEntity() {
        return documentService.getMyDashboardDocumentCountbyEntity();
    }

    @GetMapping("/mydashboard/count/typeId")
    public List getMyDashboardDocumentCountByTypeId(@RequestParam("pId") Long pId ) {
        return documentService.getMyDashboardDocumentCountByTypeId(pId);    
    }

    @GetMapping("/mydashboard/count/type/typeId")
    public List getMyDashboardDocumentCountbyEntityByTypeId(@RequestParam("pId") Long pId ) {
        return documentService.getMyDashboardDocumentCountbyEntityByTypeId(pId);    
    }

}
