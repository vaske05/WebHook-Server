package com.elfak.whserver.service;

import com.elfak.whserver.model.dto.AirQualityRequestDTO;
import com.elfak.whserver.model.dto.AirQualityResponseDTO;
import com.elfak.whserver.model.dto.CovidRequestDTO;
import com.elfak.whserver.model.dto.CovidResponseDTO;

public interface DataService {
    CovidResponseDTO getCovidData(CovidRequestDTO covidRequestDTO) throws Exception;

    AirQualityResponseDTO getAirQualityData(AirQualityRequestDTO airQualityRequestDTO) throws Exception;
}
