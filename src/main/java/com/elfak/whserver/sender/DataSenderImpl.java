package com.elfak.whserver.sender;

import com.elfak.whserver.enumeration.WebHookType;
import com.elfak.whserver.service.DataService;
import com.elfak.whserver.service.WebHookService;
import com.elfak.whserver.service.dto.airQuality.AirQualityDataRequestDTO;
import com.elfak.whserver.service.dto.airQuality.AirQualityDataResponseDTO;
import com.elfak.whserver.service.dto.covid.CovidDataRequestDTO;
import com.elfak.whserver.service.dto.covid.CovidDataResponseDTO;
import com.elfak.whserver.service.dto.wh.WebHookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSenderImpl implements DataSender { // TODO: Think to refactor this class

    private final WebHookService webHookService;
    private final DataService dataService;

    @Override
    public void sendCovidData() {

        List<WebHookDTO> covidWebHooks = webHookService.findWebHooksByType(WebHookType.COVID_DATA);

        covidWebHooks.forEach(wh -> {

            String secretKey = wh.getUser().getSecretKey();
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            requestHeaders.add("API-KEY", secretKey);

            try {
                CovidDataResponseDTO covidDataResponseDTO = dataService.getCovidData(new CovidDataRequestDTO(wh.getCountry()));

                HttpEntity<CovidDataResponseDTO> entity = new HttpEntity<>(covidDataResponseDTO, requestHeaders);

                ResponseEntity<String> response = restTemplate.postForEntity(wh.getUrl(), entity, String.class);
                log.info(response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void sendAirQualityData() {

        List<WebHookDTO> airWebHooks = webHookService.findWebHooksByType(WebHookType.AIR_DATA);

        airWebHooks.forEach(wh -> {

            String secretKey = wh.getUser().getSecretKey();
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            requestHeaders.add("API-KEY", secretKey);

            try {
                AirQualityDataResponseDTO airQualityDataResponseDTO =
                        dataService.getAirQualityData(new AirQualityDataRequestDTO(wh.getCountry(), wh.getRegion(), wh.getCity()));

                HttpEntity<AirQualityDataResponseDTO> entity = new HttpEntity<>(airQualityDataResponseDTO, requestHeaders);

                ResponseEntity<String> response = restTemplate.postForEntity(wh.getUrl(), entity, String.class);
                log.info(response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
