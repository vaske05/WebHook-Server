package com.elfak.whserver.sender;

import com.elfak.whserver.enumeration.WebHookType;
import com.elfak.whserver.model.dto.AirQualityRequestDTO;
import com.elfak.whserver.model.dto.CovidRequestDTO;
import com.elfak.whserver.model.dto.vendor.AirQualityResponseDTO;
import com.elfak.whserver.model.dto.vendor.CovidResponseDTO;
import com.elfak.whserver.service.DataService;
import com.elfak.whserver.service.WebHookService;
import com.elfak.whserver.service.dto.WebHookDTO;
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
                CovidResponseDTO covidResponseDTO = dataService.getCovidData(new CovidRequestDTO(wh.getCountry()));

                HttpEntity<CovidResponseDTO> entity = new HttpEntity<>(covidResponseDTO, requestHeaders);

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
                AirQualityResponseDTO airQualityResponseDTO =
                        dataService.getAirQualityData(new AirQualityRequestDTO(wh.getCountry(), wh.getRegion(), wh.getCity()));

                HttpEntity<AirQualityResponseDTO> entity = new HttpEntity<>(airQualityResponseDTO, requestHeaders);

                ResponseEntity<String> response = restTemplate.postForEntity(wh.getUrl(), entity, String.class);
                log.info(response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
