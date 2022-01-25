package com.elfak.whserver.facade.mapper;

import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import com.elfak.whserver.facade.model.response.*;
import com.elfak.whserver.service.dto.airQuality.AirQualitySelectCitiesResponseDTO;
import com.elfak.whserver.service.dto.airQuality.AirQualitySelectCountriesResponseDTO;
import com.elfak.whserver.service.dto.airQuality.AirQualitySelectRegionsResponseDTO;
import com.elfak.whserver.service.dto.covid.CovidSelectCountriesResponseDTO;
import com.elfak.whserver.service.dto.wh.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.wh.WebHookCreateResponseDto;
import com.elfak.whserver.service.dto.wh.WebHooksResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WebHookFacadeMapper {

    WebHookCreateRequestDto webHookCreateRequestToDto(WebHookCreateRequest request);

    WebHookCreateResponse webHookCreateResponseDtoToResponse(WebHookCreateResponseDto dto);

    WebHooksResponse.WebHookResponse webHookResponseDtoToWebHookResponse(WebHooksResponseDTO.WebHookResponseDTO dto);

    @Mapping(source = "webHooksDto", target = "webHooks")
    WebHooksResponse webHooksResponseDtoToWebHooksResponse(WebHooksResponseDTO dto);

    CovidSelectCountriesResponse covidSelectCountriesDtoToResponse(CovidSelectCountriesResponseDTO dto);

    AirQualitySelectCountriesResponse airQualitySelectCountriesDtoToResponse(AirQualitySelectCountriesResponseDTO dto);

    AirQualitySelectRegionsResponse airQualitySelectRegionsDtoToResponse(AirQualitySelectRegionsResponseDTO dto);

    AirQualitySelectCitiesResponse airQualitySelectCitiesDtoToResponse(AirQualitySelectCitiesResponseDTO dto);

}
