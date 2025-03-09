package com.growhire.site.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/cv")
public class CvController {

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadCv() throws IOException {
        Resource resource = new ClassPathResource("static/resume/Itay-Olivcovitz-Resume.pdf");

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Itay_Olivcovitz_CV.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
