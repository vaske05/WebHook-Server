package com.elfak.whserver.service;

import com.elfak.whserver.helpers.UrlHelper;
import com.elfak.whserver.model.dto.AirQualityRequestDTO;
import com.elfak.whserver.model.dto.AirQualityResponseDTO;
import com.elfak.whserver.model.dto.CovidRequestDTO;
import com.elfak.whserver.model.dto.CovidResponseDTO;
import com.neovisionaries.i18n.CountryCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class DataServiceImpl implements DataService {

    UrlHelper urlHelper;

    @Value("${covid.data.url}")
    private String GET_COVID_DATA_URL;
    @Value("${air.data.url}")
    private String GET_AIR_DATA_URL;
    @Value("${air.data.key}")
    private String AIR_DATA_KEY;

    public DataServiceImpl(UrlHelper urlHelper) {
        this.urlHelper = urlHelper;
    }

    public CovidResponseDTO getCovidData(CovidRequestDTO covidRequestDTO) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject params = new JSONObject();
        try {
            CountryCode countryCode = CountryCode.findByName(covidRequestDTO.getCountry()).get(0);
            params.put("yesterday", false);
            params.put("strict", true);
            HttpEntity<String> entity = new HttpEntity<>(params.toString());
            ResponseEntity<CovidResponseDTO> response = restTemplate.exchange(GET_COVID_DATA_URL + countryCode, HttpMethod.GET, entity, CovidResponseDTO.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Error occurred during gathering covid data");
                throw new Exception();
            } else {
                log.info("Successfully obtained covid data for country: " + covidRequestDTO.getCountry());
                return response.getBody();
            }
        } catch (Exception e) {
            log.error("Error in : " + this.clone().toString() + e.getMessage(), e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public AirQualityResponseDTO getAirQualityData(AirQualityRequestDTO airQualityRequestDTO) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<String> entity = new HttpEntity<>(requestHeaders);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(GET_AIR_DATA_URL)
                    .queryParam("city", airQualityRequestDTO.getCity())
                    .queryParam("state", airQualityRequestDTO.getState())
                    .queryParam("country", airQualityRequestDTO.getCountry())
                    .queryParam("key", AIR_DATA_KEY);
            String url = urlHelper.replaceStringOccurrence(uriBuilder.toUriString(), "%20", " ");
            ResponseEntity<AirQualityResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, AirQualityResponseDTO.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Error occurred during gathering air data");
                throw new Exception();
            } else {
                log.info("Successfully obtained air data for: " + airQualityRequestDTO.getCountry() + ", " + airQualityRequestDTO.getCity());
                return response.getBody();
            }
        } catch (Exception e) {
            log.error("Unexpected Error: ", e);
            throw new Exception(e.getMessage());
        }
    }
}
