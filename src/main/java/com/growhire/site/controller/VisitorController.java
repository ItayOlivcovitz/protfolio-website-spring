package com.growhire.site.controller;

import com.growhire.site.entity.Visitor;
import com.growhire.site.service.VisitorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("growhire/visitor")
@CrossOrigin(origins = {"http://localhost:8080", "https://itay-olivcovitz.up.railway.app/growhire/images/display.png"}) // Allowed origins
public class VisitorController {

    private final VisitorService visitorService;
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/info")
    public ResponseEntity<List<Visitor>> getVisitorInfo() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }

    @PostMapping("/save")
    public ResponseEntity<Visitor> saveVisitorInfo(HttpServletRequest request) {
        // Obtain the IP address from the request
        String ip = request.getRemoteAddr();

        // Capture the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Retrieve the User-Agent header to determine the platform
        String userAgent = request.getHeader("User-Agent");
        String platform = determinePlatform(userAgent);

        // Create a new Visitor object
        Visitor visitor = new Visitor(ip, now, platform);

        // Save the visitor to the database using the service
        visitorService.save(visitor);

        return ResponseEntity.ok(visitor);
    }

    // Helper method to determine platform from the User-Agent header
    private String determinePlatform(String userAgent) {
        if (userAgent == null) {
            return "Unknown";
        }
        String ua = userAgent.toLowerCase();
        if (ua.contains("iphone")) {
            return "iPhone";
        }
        if (ua.contains("android")) {
            return "Android";
        }
        if (ua.contains("windows")) {
            return "Windows PC";
        }
        if (ua.contains("mac")) {
            return "Mac";
        }
        return "Other";
    }




    @GetMapping("/count")
    public ResponseEntity<Long> getVisitorCount() {
        long count = visitorService.countVisitors();
        return ResponseEntity.ok(count+175);
    }

    private String parseUserAgent(String userAgent) {
        if (userAgent == null) return "Unknown";
        userAgent = userAgent.toLowerCase();
        if (userAgent.contains("iphone")) return "iPhone";
        if (userAgent.contains("android")) return "Android";
        if (userAgent.contains("windows")) return "Windows PC";
        if (userAgent.contains("mac")) return "Mac";
        return "Other";
    }


}
