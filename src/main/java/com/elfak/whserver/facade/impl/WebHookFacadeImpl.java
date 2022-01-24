package com.elfak.whserver.facade.impl;

import com.elfak.whserver.exceptions.WebHookNotFoundException;
import com.elfak.whserver.facade.WebHookFacade;
import com.elfak.whserver.facade.mapper.WebHookFacadeMapper;
import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import com.elfak.whserver.facade.model.response.*;
import com.elfak.whserver.model.dto.AirQualitySelectCitiesResponseDTO;
import com.elfak.whserver.model.dto.AirQualitySelectCountriesResponseDTO;
import com.elfak.whserver.model.dto.AirQualitySelectRegionsResponseDTO;
import com.elfak.whserver.model.dto.CovidSelectCountriesResponseDTO;
import com.elfak.whserver.service.DataService;
import com.elfak.whserver.service.ValidationErrorService;
import com.elfak.whserver.service.WebHookService;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookDTO;
import com.elfak.whserver.service.dto.WebHooksResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebHookFacadeImpl implements WebHookFacade {

    private final ValidationErrorService errorService;
    private final WebHookService webHookService;
    private final DataService dataService;
    private final WebHookFacadeMapper mapper;

    @Override
    public ResponseEntity<?> createWebHook(WebHookCreateRequest webHookCreateRequest, Principal principal,
                                           BindingResult bindingResult) {

        Optional<ResponseEntity<?>> optionalErrorMap = errorService.validateFields(bindingResult);
        if (optionalErrorMap.isPresent()) {
            return optionalErrorMap.get();
        }

        // Request -> DTO
        WebHookCreateRequestDto webHookCreateRequestDto = mapper.webHookCreateRequestToDto(webHookCreateRequest);

        // DTO - Response
        WebHookCreateResponse webHookCreateResponse = mapper
                .webHookCreateResponseDtoToResponse(webHookService.saveOrUpdate(webHookCreateRequestDto, principal.getName()));
        log.info("Web hook - created: " + webHookCreateResponse.getName());


        return new ResponseEntity<>(webHookCreateResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> findAllUserWebHooks(Principal principal) {

        log.info("Web hook - find all user webHooks by email: " + principal.getName());

        WebHooksResponseDTO webHooksResponseDTO = webHookService.findAllUserWebHooks(principal.getName());
        // DTO -> Response
        WebHooksResponse webHooksResponse = mapper.webHooksResponseDtoToWebHooksResponse(webHooksResponseDTO);

        return new ResponseEntity<>(webHooksResponse.getWebHooks(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getWebHookById(Long webHookId, Principal principal) {

        WebHookDTO webHookDTO = getWebHookDTO(webHookId);

        if (isNotEquals(principal, webHookDTO)) {
            log.info("Web hook - web hook not found in your account");
            throw new WebHookNotFoundException("Web hook not found in your account");
        }

        return new ResponseEntity<>(webHookDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteWebHook(Long webHookId, Principal principal) {

        WebHookDTO webHookDTO = getWebHookDTO(webHookId);

        if (isNotEquals(principal, webHookDTO)) {
            log.info("Web hook - web hook not found in your account");
            throw new WebHookNotFoundException("Web hook not found in your account");
        }

        webHookService.delete(webHookDTO.getId());

        return new ResponseEntity<>("Web hook with ID: '" + webHookId + "' was deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCovidSelectCountries() {
        CovidSelectCountriesResponseDTO dto = dataService.getCovidSelectCountries();
        CovidSelectCountriesResponse response = mapper.covidSelectCountriesDtoToResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAirQualitySelectCountries() {
        AirQualitySelectCountriesResponseDTO dto = dataService.getAirSelectCountries();
        AirQualitySelectCountriesResponse response = mapper.airQualitySelectCountriesDtoToResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAirQualitySelectRegions(String country) {
        AirQualitySelectRegionsResponseDTO dto = dataService.getAirSelectRegions(country);
        AirQualitySelectRegionsResponse response = mapper.airQualitySelectRegionsDtoToResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAirQualitySelectCities(String country, String region) {
        AirQualitySelectCitiesResponseDTO dto = dataService.getAirSelectCities(country, region);
        AirQualitySelectCitiesResponse response = mapper.airQualitySelectCitiesDtoToResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private WebHookDTO getWebHookDTO(Long webHookId) {
        Optional<WebHookDTO> optionalWebHookDTO = webHookService.findById(webHookId);
        return optionalWebHookDTO.orElseThrow(() -> new WebHookNotFoundException("Web hook not found with id: " + webHookId));
    }

    private boolean isNotEquals(Principal principal, WebHookDTO webHookDTO) {
        return !webHookDTO.getUser().getEmail().equals(principal.getName());
    }
}
