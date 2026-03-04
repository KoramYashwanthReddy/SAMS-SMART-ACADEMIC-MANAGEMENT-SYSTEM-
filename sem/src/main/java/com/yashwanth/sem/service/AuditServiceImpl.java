package com.yashwanth.sem.service;

import com.yashwanth.sem.entity.AuditLog;
import com.yashwanth.sem.repository.AuditLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public void log(String action, String performedBy, String entityName, Long entityId) {

        AuditLog log = new AuditLog(action, performedBy, entityName, entityId);

        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> getRecentLogs() {

        return auditLogRepository.findTop20ByOrderByTimestampDesc();
    }
}