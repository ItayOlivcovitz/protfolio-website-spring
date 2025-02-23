package com.growhire.site.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/images/desplay.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getDesplayImage() {
        try {
            // Load the image from the classpath (e.g., src/main/resources/static/images/desplay.png)
            Resource resource = resourceLoader.getResource("classpath:static/images/display.png");
            byte[] imageBytes = StreamUtils.copyToByteArray(resource.getInputStream());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Log the error in a real application
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
