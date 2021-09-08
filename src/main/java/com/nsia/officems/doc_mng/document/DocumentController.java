package com.nsia.officems.doc_mng.document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import com.nsia.officems._admin.department.Department;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._util.FileConverterUtil;
import com.nsia.officems._util.FileDownloadUtil;
import com.nsia.officems._util.HTMLToPDFCreator;
import com.nsia.officems._util.UriCreator;
import com.nsia.officems.doc_mng.document.document_comment.DocumentComment;
import com.nsia.officems.doc_mng.document.document_comment.DocumentCommentService;
import com.nsia.officems.doc_mng.document.document_comment.dto.DocumentCommentDto;
import com.nsia.officems.doc_mng.document.document_note_section.DocumentNoteSection;
import com.nsia.officems.doc_mng.document.document_receive.dto.DocumentReceiveDto;
import com.nsia.officems.doc_mng.document.document_review.DocumentReview;
import com.nsia.officems.doc_mng.document.document_review.DocumentReviewService;
import com.nsia.officems.doc_mng.document.document_review.dto.DocumentReviewDto;
import com.nsia.officems.doc_mng.document.dto.DocumentDto;
import com.nsia.officems.doc_mng.document.dto.DocumentPrintDto;
import com.nsia.officems.doc_mng.document.proj.DocumentAbstractViewProj;
import com.nsia.officems.doc_mng.document.dto.DocumentEvaluationDto;
import com.nsia.officems.doc_mng.document_type_template.DocumentTypeTemplate;
import com.nsia.officems.doc_mng.document_type_template.DocumentTypeTemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/doc_mng/documents")
public class DocumentController {
    @Value("${app.upload.doc_mng.document}")
    private String docUploadDir;

    private String _tempDirectory = System.getProperty("java.io.tmpdir");

    @Value("${app.url}")
    private String serverApi;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentReviewService documentReviewService;

    @Autowired
    private DocumentCommentService documentCommentService;

    @Autowired
    private HTMLToPDFCreator htmlToPDFCreator;

    @Autowired
    private FileConverterUtil fileConverterUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UriCreator uriCreator;

    @Autowired
    private DocumentTypeTemplateService documentTypeTemplateService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @Autowired
    private DateTimeChange dateTimeChange;

    @GetMapping(value = "/list")
    public List<Document> findAll() {
        return documentService.findAll();
    }

    @PostMapping("/list")
    public Object getFilesList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside GetFileList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return documentService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @PostMapping("/followup_list")
    public Object getFollowupList(@RequestBody String requestBody) throws Exception {
        JSONObject json = new JSONObject(requestBody);

        DataTablesInput input = objectMapper.readValue(json.get("input").toString(), DataTablesInput.class);
        System.out.println("requestBody: " + input);

        System.out.println("Filter inside GetFileList " + json.get("filters"));
        Map<String, String> filters = new HashMap<>();
        if (json.has("filters") && !(json.get("filters").toString().equals("null"))) {
            System.out.println(json.get("filters"));
            filters = objectMapper.readValue(json.get("filters").toString(), Map.class);
        } else {
            filters = null;
        }
        try {
            return documentService.getList(input, filters);
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(name = "Id", required = true) long id) {
        System.out.println("Document " + id);
        Map<String, Object> data = new HashMap<String, Object>();
        Document document = documentService.findById(id);
        List<Document> linkedDocuments = document.getLinkedDocuments();
        data.put("document", document);
        data.put("linkedDocuments", linkedDocuments);
        return ResponseEntity.ok(data);
    }

    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Document> update(@PathVariable(name = "id", required = true) Long id,
            @RequestParam(value = "obj") String obj, @RequestParam(value = "file", required = false) MultipartFile file)
            throws Exception {
        return ResponseEntity.ok(updateDocument(id, obj, file));
    }

    private Document updateDocument(Long id, String obj, MultipartFile file) {
        Gson g = new Gson();
        DocumentDto dto = g.fromJson(obj, DocumentDto.class);
        Document updatedDocument = documentService.update(id, dto);
        if (file != null) {
            String uploadDirectory = docUploadDir + "/" + updatedDocument.getId().toString();
            String fileLocation = documentService.saveAttachment(uploadDirectory, file);
            documentService.updateFileLocation(updatedDocument, fileLocation);
        }
        return updatedDocument;
    }

    @PutMapping(value = "/{id}/executed", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Document> setDocumentStatusExecuted(
            @PathVariable(name = "id", required = true) Long id) throws Exception {
        Document updatedDocument = documentService.setDocumentStatusExecuted(id);
        return ResponseEntity.ok(updatedDocument);
    }

    @PutMapping(value = "/{id}/finalize", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Document> finalizeDocumentStatus(
            @PathVariable(name = "id", required = true) Long id) throws Exception {
        Document updatedDocument = documentService.finalizeDocumentStatus(id);
        return ResponseEntity.ok(updatedDocument);
    }

    @PutMapping(value = "/{id}/receive/{receiveId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> documentReceived(@PathVariable(name = "id", required = true) Long id,
            @PathVariable(name = "receiveId", required = true) Long receiveId, @RequestBody DocumentReceiveDto dto)
            throws Exception {
        dto.setId(receiveId);
        dto.setDocumentId(id);
        return ResponseEntity.ok(documentService.documentReceiving(dto));
    }

    @PutMapping(value = "/{id}/rejected", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> documentRejected(@PathVariable(name = "id", required = true) Long id)
            throws Exception {
        return ResponseEntity.ok(documentService.documentRejected(id));
    }

    @PutMapping(value = "/{id}/evaluate", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Document> evaluate(@PathVariable(name = "id", required = true) Long id,
            @RequestBody DocumentEvaluationDto documentEvaluationDto) throws Exception {
        return ResponseEntity.ok(documentService.updateEvaluation(id, documentEvaluationDto));
    }

    @PutMapping(value = "/reviews/{reviewId}")
    public ResponseEntity<DocumentReview> updateReview(@PathVariable(name = "reviewId", required = true) Long reviewId,
            @RequestBody DocumentReviewDto dto) {
        return ResponseEntity.ok(documentReviewService.updateReviewDecision(reviewId, dto));
    }

    @PutMapping(value = "/reviews/{reviewId}/reset")
    public ResponseEntity<DocumentReview> resetReview(@PathVariable(name = "reviewId", required = true) Long reviewId) {
        return ResponseEntity.ok(documentReviewService.resetReviewDecision(reviewId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Document> create(@RequestParam(value = "obj") String obj,
            @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        return ResponseEntity.ok(createDocument(0L, null, obj, file));
    }

    private Document createDocument(Long docType, Long mainDocId, String obj, MultipartFile file) {
        Gson g = new Gson();
        DocumentDto dto = g.fromJson(obj, DocumentDto.class);
        dto.setType(docType);
        dto.setMainDocumentId(mainDocId);
        Document document = documentService.create(dto);
        if (file != null) {
            String uploadDirectory = docUploadDir + "/" + document.getId().toString();
            String fileLocation = documentService.saveAttachment(uploadDirectory, file);
            documentService.updateFileLocation(document, fileLocation);
        }
        return document;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(documentService.delete(id));
    }

    @GetMapping(value = "/my/{status}")
    public List<Document> findAllByStatus(@PathVariable(name = "status", required = true) String status) {
        User currentLoginUser = userService.getLoggedInUser();
        return documentService.findByCreatedByAndStatus(currentLoginUser, status);
    }

    // --------------- EXECUTIONS ------------------------
    @GetMapping(value = "/{docId}/executions")
    public List<DocumentAbstractViewProj> findAllExecutionsByDocId(@PathVariable(name = "docId", required = true) long docId) {
        System.out.println("docId " + docId);
        return documentService.findByMainDocumentId(docId);
    }

    @PostMapping(value = "/{docId}/executions", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Document> createExecution(
            @PathVariable(name = "docId", required = true) long docId, @RequestParam(value = "obj") String obj,
            @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        return ResponseEntity.ok(createDocument(1L, docId, obj, file));
    }

    @PutMapping(value = "/{docId}/executions/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Document> updateExecution(@PathVariable(name = "id", required = true) Long id,
            @RequestParam(value = "obj") String obj, @RequestParam(value = "file", required = false) MultipartFile file)
            throws Exception {
        return ResponseEntity.ok(updateDocument(id, obj, file));
    }

    @PutMapping(value = "/{docId}/executions/{id}/finalize", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Document> finalizeDocumentExecutionStatus(
            @PathVariable(name = "id", required = true) Long id) throws Exception {
        Document updatedDocument = documentService.finalizeDocumentStatus(id);
        return ResponseEntity.ok(updatedDocument);
    }

    @PutMapping(value = "/{docId}/executions/reviews/{reviewId}")
    public ResponseEntity<DocumentReview> updateExecutionReview(
            @PathVariable(name = "reviewId", required = true) Long reviewId, @RequestBody DocumentReviewDto dto) {
        return ResponseEntity.ok(documentReviewService.updateReviewDecision(reviewId, dto));
    }

    @PutMapping(value = "/{docId}/executions/{execId}/receive/{receiveId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Boolean> documentExecutionReceived(
            @PathVariable(name = "execId", required = true) Long execId,
            @PathVariable(name = "receiveId", required = true) Long receiveId, @RequestBody DocumentReceiveDto dto)
            throws Exception {
        dto.setId(receiveId);
        dto.setDocumentId(execId);
        return ResponseEntity.ok(documentService.documentReceiving(dto));
    }

    @GetMapping("/{docId}/executions/{execId}/print")
    public ResponseEntity<byte[]> printExecution(HttpServletResponse response, @PathVariable("execId") Long execId)
            throws Exception {
        return print(response, execId);
    }

    // --------------- COMMENTS ------------------------
    @GetMapping(value = "/{inDocId}/comments")
    public List<DocumentComment> findAllCommentsByDocumentId(
            @PathVariable(name = "inDocId", required = true) long inDocId) {
        System.out.println("task " + inDocId);
        return documentCommentService.findByDocumentId(inDocId);
    }

    @PostMapping(value = "/{inDocId}/comments")
    public DocumentComment createComment(@PathVariable(name = "inDocId", required = true) long inDocId,
            @RequestBody DocumentCommentDto documentCommentDto) {
        documentCommentDto.setDocumentId(inDocId);
        return documentCommentService.create(documentCommentDto);
    }

    @GetMapping("/{id}/print")
    public ResponseEntity<byte[]> printDocument(HttpServletResponse response, @PathVariable("id") Long id)
            throws Exception {
        return print(response, id);
    }

    public ResponseEntity<byte[]> print(HttpServletResponse response, Long id) throws Exception {

        Document getDocument = documentService.findById(id);
        Document document = null;
        String hukumPeshnehad = null;
        String directorOrder = null;
        if (getDocument.getDocumentType().getSlug().equals("HUKUM_PESHNIHAD")) {
            document = documentService.findById(getDocument.getMainDocument().getId());
            hukumPeshnehad = getDocument.getContent();
        } else if (getDocument.getDocumentType().getSlug().equals("DIR_ORDER")) {
            document = documentService.findById(getDocument.getMainDocument().getId());
            directorOrder = getDocument.getContent();
        } else {
            document = getDocument;
        }

        Department department = document.getFromDepartment();
        DocumentTypeTemplate documentTemplate = documentTypeTemplateService
                .findByDocumentTypeIdAndEntityId(document.getDocumentType().getId(), document.getFromEntity().getId());
        String documentDate = document.getDocumentDate() == null ? null
                : dateTimeChange.convertGregorianDateToPersianDate(document.getDocumentDate());
        String createdAt = document.getCreatedAt() == null ? null
                : dateTimeChange.convertLocalGregorianDateToPersianDate(document.getCreatedAt());

        List<DocumentNoteSection> noteSections = null;
        if (document.getNoteSections() != null) {
            noteSections = document.getNoteSections().stream()
                    .sorted(Comparator.comparingLong(DocumentNoteSection::getOrderCol)).collect(Collectors.toList());
        }

        String uuid = UUID.randomUUID().toString();
        String tmpdir = _tempDirectory;
        Path temp = Files.createTempFile(uuid, ".html");
        Files.write(temp, documentTemplate != null ? documentTemplate.getTemplate().getBytes(StandardCharsets.UTF_8)
                : "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> <title>Document</title> </head><body dir=\"rtl\"><div style='margin-top: 20px; padding: 15px;'>{{{content}}}</div></body></html>"
                        .getBytes(StandardCharsets.UTF_8));

        // Create dto
        DocumentPrintDto documentPrintDto = DocumentPrintDto.builder().serverApi(serverApi).directorOrder(directorOrder)
                .template(documentTemplate != null ? documentTemplate.getTemplate() : "").documentDate(documentDate)
                .document(document).documentId(document.getId()).content(document.getContent()).createdAt(createdAt)
                .hukumPeshnehad(hukumPeshnehad).noteSections(noteSections)
                .fontBoldUri(uriCreator.fontUriCreator("bahij_halvetic_bold.ttf"))
                .fontLightUri(uriCreator.fontUriCreator("bahij_halvetic_light.ttf"))
                .fontRomanUri(uriCreator.fontUriCreator("bahij_halvetic_roman.ttf"))
                .departmentHeader(department.getHeader() != null ? department.getHeader() : "")
                .departmentFooter(department.getFooter() != null ? department.getFooter() : "").build();

        TemplateLoader loader = new FileTemplateLoader(tmpdir, null);
        Handlebars handlebars = new Handlebars(loader);

        handlebars.registerHelper(ConditionalHelpers.eq.name(), ConditionalHelpers.eq);
        StringHelpers.register(handlebars);

        Template template = handlebars.compile(temp.getFileName().toString());

        String templateString = template.apply(documentPrintDto);

        String fileName = UUID.randomUUID().toString();
        File generatedPdf = htmlToPDFCreator.generatePdf(templateString, fileName);

        byte[] byteArrayOfFile = fileConverterUtil.fileToByteArray(generatedPdf);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + generatedPdf.getName());

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(byteArrayOfFile);
    }

    @GetMapping(value = "/download-file/{documentId}")
    public void downloadAgendaTopicAttachment(@PathVariable(value = "documentId") Long documentId,
            HttpServletResponse response) throws Exception {
        File file = documentService.downloadAttachment(documentId);
        fileDownloadUtil.fileDownload(file, response);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Map<String, Object>> countDocumentData() {
        return ResponseEntity.ok(documentService.countDocumentData());
    }

}