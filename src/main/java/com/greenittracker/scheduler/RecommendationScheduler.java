package com.greenittracker.scheduler;

import com.greenittracker.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RecommendationScheduler {

    private final RecommendationService recommendationService;

    @Scheduled(cron = "0 0 1 * * *")
    public void generateRecommendations() {

        log.info("Recommendation Scheduler Started");

        recommendationService.generateRecommendations();

        log.info("Recommendation Scheduler Completed");
    }
}