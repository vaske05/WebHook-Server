package com.elfak.whserver.service;

import com.elfak.whserver.service.dto.airQuality.*;
import com.elfak.whserver.service.dto.covid.CovidDataRequestDTO;
import com.elfak.whserver.service.dto.covid.CovidDataResponseDTO;
import com.elfak.whserver.service.dto.covid.CovidSelectCountriesResponseDTO;

public interface DataService {
    CovidDataResponseDTO getCovidData(CovidDataRequestDTO covidDataRequestDTO) throws Exception;

    AirQualityDataResponseDTO getAirQualityData(AirQualityDataRequestDTO airQualityDataRequestDTO) throws Exception;

    CovidSelectCountriesResponseDTO getCovidSelectCountries();

    AirQualitySelectCountriesResponseDTO getAirSelectCountries();

    AirQualitySelectRegionsResponseDTO getAirSelectRegions(String country);

    AirQualitySelectCitiesResponseDTO getAirSelectCities(String country, String region);
}
