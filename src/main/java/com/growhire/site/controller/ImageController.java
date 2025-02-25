package com.growhire.site.controller;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/growhire/images/display.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getDisplayImage() {
        try {
            // Load the image from the specified location
            Resource resource = resourceLoader.getResource("classpath:/projectwebsite/growhire/images/display.png");

            if (!resource.exists()) {
                logger.error("Image not found at: {}", resource.getURI());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            byte[] imageBytes = StreamUtils.copyToByteArray(resource.getInputStream());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            logger.info("Successfully loaded image from: {}", resource.getURI());

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            logger.error("Error reading image file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
