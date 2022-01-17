package com.elfak.whserver.scheduler;

import com.elfak.whserver.sender.DataSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataScheduler {

    @Value("${covid.data.scheduler.enabled}")
    private String covidSchedulerEnabled;

    @Value("${air.data.scheduler.enabled}")
    private String airSchedulerEnabled;

    private final DataSender dataSender;

    @Scheduled(cron = "0/10 * * ? * *")
    @SchedulerLock(name = "covidScheduler", lockAtMostFor = "1s", lockAtLeastFor = "1s")
    @Transactional
    public void covidDataScheduler() {

        if (Boolean.valueOf(covidSchedulerEnabled).equals(Boolean.FALSE)) {
            log.info("COVID scheduler disabled");
            return;
        }
        System.out.println("COVID scheduler...");
        dataSender.sendCovidData();
    }

    @Scheduled(cron = "0/15 * * ? * *")
    @SchedulerLock(name = "covidScheduler", lockAtMostFor = "1s", lockAtLeastFor = "1s")
    @Transactional
    public void airDataScheduler() {

        if (Boolean.valueOf(airSchedulerEnabled).equals(Boolean.FALSE)) {
            log.info("AIR scheduler disabled");
            return;
        }
        System.out.println("AIR scheduler...");
        dataSender.sendAirQualityData();
    }
}
