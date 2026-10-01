package com.greenittracker.repository;

import com.greenittracker.entity.SustainabilityMetric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SustainabilityMetricRepository
        extends JpaRepository<SustainabilityMetric, Long> {

}