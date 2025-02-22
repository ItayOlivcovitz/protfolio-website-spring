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
import java.util.List;

@RestController
@RequestMapping("/api/visitor")
@CrossOrigin(origins = {"https://growhire.up.railway.app/", "http://127.0.0.1:5500"})
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;

    @GetMapping("/info")
    public ResponseEntity<List<Visitor>> getVisitorInfo() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }

    @PostMapping("/save")
    public ResponseEntity<Visitor> saveVisitorInfo(@RequestBody Visitor visitor, HttpServletRequest request) {
        // Retrieve the real client IP address
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress != null && !ipAddress.isEmpty() && !"unknown".equalsIgnoreCase(ipAddress)) {
            // The header may contain multiple IPs if the request went through proxies
            ipAddress = ipAddress.split(",")[0].trim();
        } else {
            ipAddress = request.getRemoteAddr(); // Fallback if no proxy
        }

        visitor.setIp(ipAddress);

        // Retrieve and parse the User-Agent header
        String userAgentString = request.getHeader("User-Agent");
        String deviceType = parseUserAgent(userAgentString);
        visitor.setPlatform(deviceType);

        // Set the current date and time
        LocalDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem")).toLocalDateTime();
        visitor.setDate(now);

        Visitor savedVisitor = visitorService.saveVisitor(visitor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVisitor);
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getVisitorCount() {
        long count = visitorService.countVisitors();
        return ResponseEntity.ok(count);
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
