package com.elfak.whserver.scheduler;

import com.elfak.whserver.enumeration.WebHookType;
import com.elfak.whserver.model.dto.AirQualityRequestDTO;
import com.elfak.whserver.model.dto.AirQualityResponseDTO;
import com.elfak.whserver.model.dto.CovidRequestDTO;
import com.elfak.whserver.model.dto.CovidResponseDTO;
import com.elfak.whserver.service.DataService;
import com.elfak.whserver.service.WebHookService;
import com.elfak.whserver.service.dto.WebHookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataScheduler {

    private final WebHookService webHookService;
    private final DataService dataService;

    @Scheduled(cron = "0/10 * * ? * *")
    @SchedulerLock(name = "covidScheduler", lockAtMostFor = "1s", lockAtLeastFor = "1s")
    @Transactional
    public void covidDataScheduler() {
        System.out.println("COVID scheduler...");

        List<WebHookDTO> covidWebHooks = webHookService.findWebHooksByType(WebHookType.COVID_DATA);

        covidWebHooks.forEach(wh -> {

            String secretKey = wh.getUser().getSecretKey();
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            requestHeaders.add("API-KEY", secretKey);

            try {
                CovidResponseDTO covidResponseDTO = dataService.getCovidData(new CovidRequestDTO("Serbia")); // TODO: Grab country data from WH

                HttpEntity<CovidResponseDTO> entity = new HttpEntity<>(covidResponseDTO, requestHeaders);

                ResponseEntity<String> response = restTemplate.postForEntity(wh.getUrl(), entity, String.class);
                log.info(response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Scheduled(cron = "0/15 * * ? * *")
    @SchedulerLock(name = "covidScheduler", lockAtMostFor = "1s", lockAtLeastFor = "1s")
    @Transactional
    public void airDataScheduler() {
        System.out.println("AIR scheduler...");

        List<WebHookDTO> airWebHooks = webHookService.findWebHooksByType(WebHookType.AIR_DATA);

        airWebHooks.forEach(wh -> {

            String secretKey = wh.getUser().getSecretKey();
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            requestHeaders.add("API-KEY", secretKey);

            try {
                AirQualityResponseDTO airQualityResponseDTO =
                        dataService.getAirQualityData(new AirQualityRequestDTO("Serbia", "Central Serbia", "Nis")); // TODO: Grab country data from WH

                HttpEntity<AirQualityResponseDTO> entity = new HttpEntity<>(airQualityResponseDTO, requestHeaders);

                ResponseEntity<String> response = restTemplate.postForEntity(wh.getUrl(), entity, String.class);
                log.info(response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
