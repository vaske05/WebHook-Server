package com.elfak.whserver.facade.mapper;

import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import com.elfak.whserver.facade.model.response.*;
import com.elfak.whserver.model.dto.AirQualitySelectCitiesResponseDTO;
import com.elfak.whserver.model.dto.AirQualitySelectCountriesResponseDTO;
import com.elfak.whserver.model.dto.AirQualitySelectRegionsResponseDTO;
import com.elfak.whserver.model.dto.CovidSelectCountriesResponseDTO;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookCreateResponseDto;
import com.elfak.whserver.service.dto.WebHooksResponseDTO;
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
