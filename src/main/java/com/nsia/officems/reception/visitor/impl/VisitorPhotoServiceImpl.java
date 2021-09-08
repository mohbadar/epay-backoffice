package com.nsia.officems.reception.visitor.impl;

import com.nsia.officems.reception.visitor.Visitor;
import com.nsia.officems.reception.visitor.VisitorPhoto;
import com.nsia.officems.reception.visitor.VisitorPhotoRepository;
import com.nsia.officems.reception.visitor.VisitorPhotoService;
import com.nsia.officems.reception.visitor.VisitorRepository;
import com.nsia.officems.reception.visitor.VisitorService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorPhotoServiceImpl implements VisitorPhotoService {
    @Autowired
    private VisitorPhotoRepository  visitorPhotoRepository;

    public VisitorPhoto findById(Long id) {
        System.out.println("visitorPhotoRepository.findById()" + id);
        Optional<VisitorPhoto> objection = visitorPhotoRepository.findById(id);
        if (objection.isPresent()) {
            System.out.println("Visitor: " + objection);
            return objection.get();
        }
        return null;
    }

    public boolean delete(long id) {
        Optional<VisitorPhoto> document = visitorPhotoRepository.findById(id);
        if (document.isPresent()) {
            // document.setDeletedAt(True);
            return true;
        }
        return false;
    }

    @Override
    public VisitorPhoto save(VisitorPhoto visitorPhoto) {
        return this.visitorPhotoRepository.save(visitorPhoto);
    }
}
