package com.nsia.officems._util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.UUID;

import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.configurations.WrapperConfig;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HTMLToPDFCreator {
        // temp directory of Operation System
        private String _tempDirectory = System.getProperty("java.io.tmpdir");

        @Value("${app.upload.tmp}")
        private String _projectDirectory;

        @Value("${wkhtmltopdf}")
        private String wkhtmltopdf;

        public File generatePdf(String templateString, String fileName) throws IOException, InterruptedException {
                WrapperConfig wrapperConfig = new WrapperConfig(wkhtmltopdf);

                Pdf pdf = new Pdf(wrapperConfig);
                pdf.addPageFromString(templateString);

                // add parameters
                pdf.addParam(new Param("--enable-local-file-access"), new Param("--load-error-handling", "ignore"),
                                new Param("--load-media-error-handling", "ignore"), new Param("--margin-bottom", "10"),
                                new Param("--margin-top", "10"), new Param("--margin-left", "10"),
                                new Param("--margin-right", "10"), new Param("--viewport-size", "1920×1080"));

                // Save the PDF
                return this.savePdf(pdf, fileName);
        };

        public File generateDocumentPdf(String templateString, String headerHeight, String fileName, Path headerPath,
                        Path footerPath) throws IOException, InterruptedException {
                WrapperConfig wrapperConfig = new WrapperConfig(wkhtmltopdf);

                Pdf pdf = new Pdf(wrapperConfig);
                pdf.addPageFromString(templateString);

                // add parameters
                pdf.addParam(new Param("--enable-local-file-access"), new Param("--load-error-handling", "ignore"),
                                new Param("--margin-top", headerHeight),
                                new Param("--header-html",
                                                "\"file:///" + _tempDirectory + "/"
                                                                + headerPath.getFileName().toString() + "\""),
                                new Param("--footer-html",
                                                "\"file:///" + _tempDirectory + "/"
                                                                + footerPath.getFileName().toString() + "\""),
                                new Param("--viewport-size", "1920×1080"));

                // Save the PDF
                return this.savePdf(pdf, fileName);
        };

        public File savePdf(Pdf pdf, String fileName) throws IOException, InterruptedException {
                Calendar calendar = Calendar.getInstance();
                String FileName = fileName + "_" + calendar.getInstance().getTimeInMillis() + ".pdf";

                // File savedPdf = pdf.saveAsDirect(_tempDirectory + "/" + FileName);
                pdf.setAllowMissingAssets();
                File savedPdf = pdf.saveAs(_projectDirectory + "/" + FileName);
                return savedPdf;
        }
}
