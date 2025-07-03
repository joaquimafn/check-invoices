package com.infuse.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
@Slf4j
public class HealthController {
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        log.info("Health check requested");
        
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("service", "Infuse Application");
        health.put("version", "1.0.0");
        
        return ResponseEntity.ok(health);
    }
    
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        log.info("Info check requested");
        
        Map<String, Object> info = new HashMap<>();
        info.put("application", "Infuse Spring Boot Application");
        info.put("description", "API RESTful para gerenciamento de usuários");
        info.put("version", "1.0.0");
        info.put("java.version", System.getProperty("java.version"));
        info.put("spring.profiles.active", System.getProperty("spring.profiles.active"));
        
        return ResponseEntity.ok(info);
    }
} 