package com.greenittracker.service.impl;

import com.greenittracker.entity.AuditLog;
import com.greenittracker.repository.AuditLogRepository;
import com.greenittracker.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public void saveAuditLog(
            String username,
            String action,
            String module,
            String description) {

        AuditLog log = AuditLog.builder()
                .username(username)
                .action(action)
                .module(module)
                .description(description)
                .build();

        auditLogRepository.save(log);
    }
}