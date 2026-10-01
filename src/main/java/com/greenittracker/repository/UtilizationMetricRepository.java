package com.greenittracker.repository;

import com.greenittracker.entity.Resource;
import com.greenittracker.entity.UtilizationMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilizationMetricRepository
        extends JpaRepository<UtilizationMetric, Long> {

    List<UtilizationMetric> findByResource(Resource resource);
}