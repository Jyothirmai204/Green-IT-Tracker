package com.greenittracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sustainability_metrics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SustainabilityMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    private Double energySaved;

    @Min(0)
    private Double carbonSaved;

    @Min(0)
    private Double costSaved;

    @Min(0)
    private Integer greenScore;

    private LocalDate metricDate;
}