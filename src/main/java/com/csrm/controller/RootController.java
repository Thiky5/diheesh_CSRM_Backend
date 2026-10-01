package com.csrm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {

    private final String frontendUrl;

    public RootController(@Value("${frontend.url:http://localhost:5173}") String frontendUrl) {
        this.frontendUrl = frontendUrl;
    }

    @GetMapping("/")
    public RedirectView redirectToFrontend() {
        return new RedirectView(frontendUrl);
    }

    @GetMapping("/api")
    public ResponseEntity<Map<String, Object>> apiStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("system", "Campus Smart Resource Management (CSRM) API");
        status.put("version", "2.0");
        status.put("status", "UP");
        status.put("frontendUrl", frontendUrl);
        status.put("availableEndpoints", new String[]{
                "/api/auth/login",
                "/api/auth/register",
                "/api/resources",
                "/api/bookings",
                "/api/admin/users",
                "/api/audit",
                "/api/services",
                "/api/notifications"
        });
        return ResponseEntity.ok(status);
    }
}
