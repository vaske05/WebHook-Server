package com.elfak.whserver.service.mapper;

import com.elfak.whserver.service.dto.airQuality.AirQualityDataResponseDTO;
import com.elfak.whserver.service.dto.airQuality.AirQualityDataResponseRaw;
import com.elfak.whserver.service.dto.covid.CovidDataResponseDTO;
import com.elfak.whserver.service.dto.covid.CovidDataResponseRaw;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataServiceMapper {
    AirQualityDataResponseDTO airQualityDataResponseRawToDto(AirQualityDataResponseRaw raw);

    CovidDataResponseDTO covidDataResponseRawToDto(CovidDataResponseRaw raw);
}
