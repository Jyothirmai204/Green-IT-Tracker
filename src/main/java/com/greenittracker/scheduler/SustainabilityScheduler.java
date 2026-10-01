package com.greenittracker.scheduler;

import com.greenittracker.service.SustainabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SustainabilityScheduler {

    private final SustainabilityService sustainabilityService;

    @Scheduled(cron = "0 0 2 * * *")
    public void calculateMetrics() {

        log.info("Sustainability Scheduler Started");

        sustainabilityService.calculateMetrics();

        log.info("Sustainability Scheduler Completed");
    }
}