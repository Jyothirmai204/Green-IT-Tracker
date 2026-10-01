package com.greenittracker.mapper;

import com.greenittracker.dto.response.PaperlessMetricResponseDto;
import com.greenittracker.entity.PaperlessMetric;
import org.springframework.stereotype.Component;

@Component
public class PaperlessMapper {

    public PaperlessMetricResponseDto toResponseDto(
            PaperlessMetric metric) {

        return PaperlessMetricResponseDto.builder()
                .printedPages(metric.getPrintedPages())
                .digitalDocuments(metric.getDigitalDocuments())
                .paperSaved(metric.getPaperSaved())
                .build();
    }
}