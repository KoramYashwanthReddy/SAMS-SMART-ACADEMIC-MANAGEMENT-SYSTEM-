package com.yashwanth.sem.service;

import com.yashwanth.sem.entity.AuditLog;

import java.util.List;

public interface AuditService {

    // Save audit activity
    void log(String action, String performedBy, String entityName, Long entityId);

    // Get recent admin activities
    List<AuditLog> getRecentLogs();
}