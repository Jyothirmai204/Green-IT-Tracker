package com.greenittracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "utilization_metrics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilizationMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @Max(100)
    private Double cpuUsage;

    @Min(0)
    @Max(100)
    private Double memoryUsage;

    @Min(0)
    @Max(100)
    private Double diskUsage;

    @Min(0)
    @Max(100)
    private Double networkUsage;

    private LocalDateTime collectedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;
}