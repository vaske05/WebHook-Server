package com.elfak.whserver.scheduler;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataScheduler {

    @Scheduled(cron = "0/8 * * ? * *")
    @SchedulerLock(name = "covidScheduler", lockAtMostFor = "1s", lockAtLeastFor = "1s")
    public void covidDataScheduler() {
        System.out.println("COVID scheduler...");
    }

    @Scheduled(cron = "0/5 * * ? * *")
    @SchedulerLock(name = "airScheduler", lockAtMostFor = "1s", lockAtLeastFor = "1s")
    public void airDataScheduler() {
        System.out.println("AIR scheduler...");
    }

}
