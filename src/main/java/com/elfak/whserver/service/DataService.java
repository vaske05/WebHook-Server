package com.elfak.whserver.service;

import com.elfak.whserver.model.dto.*;
import com.elfak.whserver.model.dto.vendor.AirQualityResponseDTO;
import com.elfak.whserver.model.dto.vendor.CovidResponseDTO;

public interface DataService {
    CovidResponseDTO getCovidData(CovidRequestDTO covidRequestDTO) throws Exception;

    AirQualityResponseDTO getAirQualityData(AirQualityRequestDTO airQualityRequestDTO) throws Exception;

    CovidSelectCountriesResponseDTO getCovidSelectCountries();

    AirQualitySelectCountriesResponseDTO getAirSelectCountries();

    AirQualitySelectRegionsResponseDTO getAirSelectRegions(String country);

    AirQualitySelectCitiesResponseDTO getAirSelectCities(String country, String region);
}
