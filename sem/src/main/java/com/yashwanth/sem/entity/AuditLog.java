package com.yashwanth.sem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    private String performedBy;

    private String entityName;

    private Long entityId;

    private LocalDateTime timestamp;

    public AuditLog() {}

    public AuditLog(String action, String performedBy, String entityName, Long entityId) {
        this.action = action;
        this.performedBy = performedBy;
        this.entityName = entityName;
        this.entityId = entityId;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public String getEntityName() {
        return entityName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}