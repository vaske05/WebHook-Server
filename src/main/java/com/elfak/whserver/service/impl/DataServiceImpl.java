package com.elfak.whserver.service.impl;

import com.elfak.whserver.helpers.Constants;
import com.elfak.whserver.helpers.UrlHelper;
import com.elfak.whserver.model.dto.*;
import com.elfak.whserver.model.dto.vendor.*;
import com.elfak.whserver.service.DataService;
import com.neovisionaries.i18n.CountryCode;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataServiceImpl implements DataService {

    @Value("${covid.data.url}")
    private String GET_COVID_DATA_URL;

    @Value("${air.data.url}")
    private String GET_AIR_DATA_URL;
    @Value("${air.countries.url}")
    private String GET_AIR_COUNTRIES_URL;
    @Value("${air.regions.url}")
    private String GET_AIR_REGIONS_URL;
    @Value("${air.cities.url}")
    private String GET_AIR_CITIES_URL;
    @Value("${air.data.key}")
    private String AIR_DATA_KEY;

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
                log.error("COVID DATA - Error occurred during gathering covid data");
                throw new Exception();
            } else {
                log.info("COVID DATA - Successfully obtained covid data for country: " + covidRequestDTO.getCountry());
                return response.getBody();
            }
        } catch (IndexOutOfBoundsException e) {
            log.error("COVID DATA - Country code not found for given country");
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("COVID DATA - Error occurred during gathering covid data: " + e.getMessage(), e);
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
            String url = UrlHelper.replaceStringOccurrence(uriBuilder.toUriString(), "%20", " ");
            ResponseEntity<AirQualityResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, AirQualityResponseDTO.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("AIR DATA - Error occurred during gathering air data");
                throw new Exception();
            } else {
                log.info("AIR DATA - Successfully obtained air data for: " + airQualityRequestDTO.getCountry() + ", " + airQualityRequestDTO.getCity());
                return response.getBody();
            }
        } catch (Exception e) {
            log.error("AIR DATA - Unexpected Error: ", e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Cacheable(Constants.CacheNames.COVID_SELECT_INPUT_CACHE)
    public CovidSelectCountriesResponseDTO getCovidSelectCountries() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<List<CovidCountriesResponseRaw>> response = restTemplate.exchange(GET_COVID_DATA_URL, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {
            });

            if (Optional.ofNullable(response.getBody()).isEmpty() || !response.getStatusCode().is2xxSuccessful()) {
                log.error("COVID SELECT COUNTRIES - Error occurred during gathering covid countries");
                throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, "Error occurred during gathering covid countries");
            } else {
                log.info("COVID SELECT COUNTRIES - Successfully obtained covid select countries");
                return CovidSelectCountriesResponseDTO
                        .builder()
                        .countries(response.getBody().stream().map(CovidCountriesResponseRaw::getCountry).collect(Collectors.toList()))
                        .build();
            }
        } catch (Exception e) {
            log.error("COVID SELECT COUNTRIES - Error occurred during gathering covid countries: " + e.getMessage(), e);
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @Cacheable(Constants.CacheNames.AIR_SELECT_INPUTS_CACHE)
    public AirQualitySelectCountriesResponseDTO getAirSelectCountries() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(GET_AIR_COUNTRIES_URL)
                    .queryParam("key", AIR_DATA_KEY);
            String url = UrlHelper.replaceStringOccurrence(uriBuilder.toUriString(), "%20", " ");
            ResponseEntity<AirQualityCountriesResponseRaw> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, AirQualityCountriesResponseRaw.class);

            if (Optional.ofNullable(response.getBody()).isEmpty() || !response.getStatusCode().is2xxSuccessful()) {
                log.error("AIR SELECT COUNTRIES - Error occurred during gathering air data");
                throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, "Error occurred during gathering air countries");
            } else {
                log.info("AIR SELECT COUNTRIES - Successfully obtained air countries data");
                return AirQualitySelectCountriesResponseDTO
                        .builder()
                        .countries(response.getBody().getData().stream().map(AirQualityCountriesResponseRaw.Data::getCountry).collect(Collectors.toList()))
                        .build();
            }
        } catch (Exception e) {
            log.error("AIR SELECT COUNTRIES - Unexpected Error: ", e);
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @Cacheable(Constants.CacheNames.AIR_SELECT_INPUTS_CACHE)
    public AirQualitySelectRegionsResponseDTO getAirSelectRegions(String country) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(GET_AIR_REGIONS_URL)
                    .queryParam("country", country)
                    .queryParam("key", AIR_DATA_KEY);
            String url = UrlHelper.replaceStringOccurrence(uriBuilder.toUriString(), "%20", " ");
            ResponseEntity<AirQualityRegionsResponseRaw> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, AirQualityRegionsResponseRaw.class);

            if (Optional.ofNullable(response.getBody()).isEmpty() || !response.getStatusCode().is2xxSuccessful()) {
                log.error("AIR SELECT REGIONS - Error occurred during gathering air regions");
                throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, "Error occurred during gathering air regions");
            } else {
                log.info("AIR SELECT REGIONS - Successfully obtained air regions data");
                return AirQualitySelectRegionsResponseDTO
                        .builder()
                        .regions(response.getBody().getData().stream().map(AirQualityRegionsResponseRaw.Data::getState).collect(Collectors.toList()))
                        .build();
            }
        } catch (Exception e) {
            log.error("AIR SELECT REGIONS - Unexpected Error: ", e);
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @Cacheable(Constants.CacheNames.AIR_SELECT_INPUTS_CACHE)
    public AirQualitySelectCitiesResponseDTO getAirSelectCities(String country, String region) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(GET_AIR_CITIES_URL)
                    .queryParam("country", country)
                    .queryParam("state", region)
                    .queryParam("key", AIR_DATA_KEY);
            String url = UrlHelper.replaceStringOccurrence(uriBuilder.toUriString(), "%20", " ");
            ResponseEntity<AirQualityCitiesResponseRaw> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, AirQualityCitiesResponseRaw.class);

            if (Optional.ofNullable(response.getBody()).isEmpty() || !response.getStatusCode().is2xxSuccessful()) {
                log.error("AIR SELECT CITIES - Error occurred during gathering air cities");
                throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, "Error occurred during gathering air cities");
            } else {
                log.info("AIR SELECT CITIES - Successfully obtained air cities data");
                return AirQualitySelectCitiesResponseDTO
                        .builder()
                        .cities(response.getBody().getData().stream().map(AirQualityCitiesResponseRaw.Data::getCity).collect(Collectors.toList()))
                        .build();
            }
        } catch (Exception e) {
            if (e.getMessage().contains("no_active_cities_found") || e.getMessage().contains("state_not_found")) {
                log.info("AIR SELECT CITIES - No active cities found, country: " + country + " region: " + region);
                return AirQualitySelectCitiesResponseDTO.builder().cities(Collections.emptyList()).build();
            }
            log.error("AIR SELECT CITIES - Unexpected Error: ");
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
