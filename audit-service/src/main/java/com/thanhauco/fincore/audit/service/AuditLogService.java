package com.thanhauco.fincore.audit.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuditLogService {
    public void logAction(String userId, String action, String resource) {
        // Simulate writing to MongoDB or ElasticSearch
        System.out.println(LocalDateTime.now() + " AUDIT: User " + userId + " performed " + action + " on " + resource);
    }
}
