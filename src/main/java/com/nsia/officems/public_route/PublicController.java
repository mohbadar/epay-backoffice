package com.nsia.officems.public_route;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsia.officems._identity.authentication.user.User;
import com.nsia.officems._identity.authentication.user.UserService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems._util.ExceptionUtil;
import com.nsia.officems.doc_mng.document.Document;
import com.nsia.officems.doc_mng.document.DocumentService;
import com.nsia.officems.reception.vehicle.ReceptionVehicle;
import com.nsia.officems.reception.vehicle.ReceptionVehiclePhoto;
import com.nsia.officems.reception.vehicle.ReceptionVehicleService;
import com.nsia.officems.reception.visitor.Visitor;
import com.nsia.officems.reception.visitor.VisitorPhoto;
import com.nsia.officems.reception.visitor.VisitorService;
import com.nsia.officems.doc_mng.document_gallery.DocumentGalleryService;
import com.nsia.officems._util.FileDownloadUtil;

import com.nsia.officems._util.QRCodeGenerator;

import org.springframework.core.io.Resource;

import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping({ "/api/public" })
public class PublicController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value(value = "${app.upload.reception.visitors}")
    private String visitorPhotosPath;

    @Value(value = "${app.upload.reception.vehicles}")
    private String vehiclePhotosPath;

    @Value("${app.upload.teacherProfilePath}")
    private String teacherProfilePath;


    @Value("${app.upload.user}")
    private String userUploadDirectory;


    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private ReceptionVehicleService vehicleService;

    @Autowired
    private DocumentGalleryService documentGalleryService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    DateTimeChange changeDate = new DateTimeChange();

    private String failureEmailSub = "500 - OfficeMs Exception - Public API";

    @RequestMapping(value = "/visitor/{visitorId}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getVisitorDefaultImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("visitorId") Long visitorId, @RequestParam(required = false) String size) throws Exception {
        logger.info("Entry PublicController>getVisitorImage() - GET");
        try {
            Visitor visitor = visitorService.findById(visitorId);
            VisitorPhoto visitorDefaultPhoto = visitor.getDefaultPhoto();
            if(visitorDefaultPhoto != null) {
                String fileName = visitorDefaultPhoto.getName();
                String filePath = visitorPhotosPath + File.separator + fileName;
                System.out.println("avatarPhotoPath" + filePath);
                int width = -1;
                int height = -1;
                if (size != null && size.length() > 0) {
                    String[] sizeParts = size.split("x|X");
                    width = Integer.parseInt(sizeParts[0]);
                    height = Integer.parseInt(sizeParts[1]);
                }
                return readImage(filePath, width, height);
            }
            return null;
        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, null, null, request, false);
            return null;
        }
    }

    @RequestMapping(value = "/vehicle/{vehicleId}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getVehicleImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("vehicleId") Long vehicleId, @RequestParam(required = false) String size) throws Exception {
        logger.info("Entry PublicController>getVehicleImage() - GET");
        try {
            ReceptionVehicle vehicle = vehicleService.findById(vehicleId);
            ReceptionVehiclePhoto vehiclePhoto = vehicle.getVehiclePhoto();
            if(vehiclePhoto != null) {
                String fileName = vehiclePhoto.getName();
                String filePath = vehiclePhotosPath + File.separator + fileName;
                System.out.println("avatarPhotoPath" + filePath);
                int width = -1;
                int height = -1;
                if (size != null && size.length() > 0) {
                    String[] sizeParts = size.split("x|X");
                    width = Integer.parseInt(sizeParts[0]);
                    height = Integer.parseInt(sizeParts[1]);
                }
                return readImage(filePath, width, height);
            }
            return null;
        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, null, null, request, false);
            return null;
        }
    }

    byte[] readImage(String filePath, int width, int height) throws Exception {
        try {
            if (new File(filePath).exists()) {
                final ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
                if (width != -1 && height != -1) {
                    final BufferedImage thumbnail = Scalr.resize(bufferedImage, Mode.AUTOMATIC, width, height);
                    ImageIO.write(thumbnail, "png", bos);
                } else {
                    ImageIO.write(bufferedImage, "png", bos);
                }
                return bos.toByteArray();
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }


    @RequestMapping(value = "/user/{Id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getUserImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("Id") Long Id, @RequestParam(required = false) String size) throws Exception {
        logger.info("Entry PublicController>getProfileImage() - GET");
        try {
            User user = userService.findById(Id);
            String avatarName = user.getAvatar();
            String avatarPhotoPath = userUploadDirectory + '/' + Id + '/'+ avatarName;
            System.out.println("avatarPhotoPath" + avatarPhotoPath);
            int width = -1;
            int height = -1;
            if (size != null && size.length() > 0) {
                String[] sizeParts = size.split("x|X");
                width = Integer.parseInt(sizeParts[0]);
                height = Integer.parseInt(sizeParts[1]);
            }
            if (new File(avatarPhotoPath).exists()) {
                final ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final BufferedImage bufferedImage = ImageIO.read(new FileInputStream(avatarPhotoPath));
                if (width != -1 && height != -1) {
                    final BufferedImage thumbnail = Scalr.resize(bufferedImage, Mode.AUTOMATIC, width, height);
                    ImageIO.write(thumbnail, "png", bos);
                } else {
                    ImageIO.write(bufferedImage, "png", bos);
                }
                return bos.toByteArray();
            }
            return null;
        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, null, null, request, false);
            return null;
        }
    }

    @RequestMapping(value = "/documents/{docId}/genQRCode/{width}/{height}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] generateDocQRCode(@PathVariable("docId") Long docId, @PathVariable("width") Integer width, @PathVariable("height") Integer height) throws Exception {
        Document document = documentService.findById(docId);
        // 0: means main document
        String codeText= document.getType() + ";" + document.getId().toString() + ";" + document.getDocumentType().getNameEn()+ ";"+ document.getCreatedAt();
        return QRCodeGenerator.getQRCodeImage(codeText, width, height);
    }

    // @RequestMapping(value = "/executions/{execId}/genQRCode/{width}/{height}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    // public @ResponseBody byte[] generateExecQRCode(@PathVariable("execId") Long execId, @PathVariable("width") Integer width, @PathVariable("height") Integer height) throws Exception {
    //     DocumentExecution execution = documentExecutionService.findById(execId);
    //     String codeText= "1;" + execution.getId().toString() + ";" + execution.getDocumentExecutionType().getNameEn()+ ";"+ execution.getCreatedAt();
    //     return QRCodeGenerator.getQRCodeImage(codeText, width, height);
    // }

    @GetMapping(value = "/download-image/{imageId}")
    public void downloadImage(@PathVariable(value = "imageId") Long imageId, HttpServletResponse response)
            throws Exception {
        File file = documentGalleryService.downloadAttachment(imageId);
        fileDownloadUtil.fileDownload(file, response);
    }

    @GetMapping(value = "/download-thumbnail/{imageId}")
    public void downloadThumbnail(@PathVariable(value = "imageId") Long imageId, HttpServletResponse response)
            throws Exception {
        File file = documentGalleryService.downloadThumbnail(imageId);
        fileDownloadUtil.fileDownload(file, response);
    }
}
