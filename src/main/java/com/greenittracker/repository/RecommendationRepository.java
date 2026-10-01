package com.greenittracker.repository;

import com.greenittracker.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository
        extends JpaRepository<Recommendation, Long> {
}