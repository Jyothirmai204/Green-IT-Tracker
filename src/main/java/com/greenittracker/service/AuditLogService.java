package com.greenittracker.service;

public interface AuditLogService {

    void saveAuditLog(
            String username,
            String action,
            String module,
            String description);
}