package com.greenittracker.service.impl;

import com.greenittracker.dto.request.PaperlessMetricRequestDto;
import com.greenittracker.dto.response.PaperlessMetricResponseDto;
import com.greenittracker.entity.PaperlessMetric;
import com.greenittracker.mapper.PaperlessMapper;
import com.greenittracker.repository.PaperlessMetricRepository;
import com.greenittracker.service.AuditLogService;
import com.greenittracker.service.PaperlessService;
import com.greenittracker.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaperlessServiceImpl implements PaperlessService {

    private final PaperlessMetricRepository paperlessMetricRepository;
    private final PaperlessMapper paperlessMapper;
    private final AuditLogService auditLogService;

    @Override
    public PaperlessMetricResponseDto savePaperlessMetric(
            PaperlessMetricRequestDto requestDto) {

        log.info("Saving paperless metric");

        PaperlessMetric metric = PaperlessMetric.builder()
                .printedPages(requestDto.getPrintedPages())
                .digitalDocuments(requestDto.getDigitalDocuments())
                .paperSaved(requestDto.getPaperSaved())
                .metricDate(LocalDate.now())
                .build();

        PaperlessMetric savedMetric =
                paperlessMetricRepository.save(metric);

        auditLogService.saveAuditLog(
                SecurityUtil.getLoggedInUser(),
                "CREATE",
                "PAPERLESS",
                "Paperless metric created successfully"
        );

        return paperlessMapper.toResponseDto(savedMetric);
    }

    @Override
    @Transactional(readOnly = true)
    public PaperlessMetricResponseDto getLatestPaperlessMetric() {

        log.info("Fetching latest paperless metric");

        PaperlessMetric metric =
                paperlessMetricRepository
                        .findTopByOrderByMetricDateDesc()
                        .orElse(
                                PaperlessMetric.builder()
                                        .printedPages(0)
                                        .digitalDocuments(0)
                                        .paperSaved(0)
                                        .metricDate(LocalDate.now())
                                        .build()
                        );

        return paperlessMapper.toResponseDto(metric);
    }
}