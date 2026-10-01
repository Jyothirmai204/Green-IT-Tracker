package com.greenittracker.repository;

import com.greenittracker.entity.PaperlessMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PaperlessMetricRepository
        extends JpaRepository<PaperlessMetric, Long> {
    Optional<PaperlessMetric> findTopByOrderByMetricDateDesc();

}