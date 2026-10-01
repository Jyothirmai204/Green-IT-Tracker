package com.greenittracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "paperless_metrics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaperlessMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    private Integer printedPages;

    @Min(0)
    private Integer digitalDocuments;

    @Min(0)
    private Integer paperSaved;

    private LocalDate metricDate;
}