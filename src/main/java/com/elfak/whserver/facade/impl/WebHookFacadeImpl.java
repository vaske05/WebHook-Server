package com.elfak.whserver.facade.impl;

import com.elfak.whserver.facade.WebHookFacade;
import com.elfak.whserver.facade.mapper.WebHookFacadeMapper;
import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import com.elfak.whserver.facade.model.response.WebHookCreateResponse;
import com.elfak.whserver.service.ValidationErrorService;
import com.elfak.whserver.service.WebHookService;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.security.Principal;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebHookFacadeImpl implements WebHookFacade {

    private final ValidationErrorService errorService;
    private final WebHookService webHookService;
    private final WebHookFacadeMapper mapper;

    @Override
    public ResponseEntity<?> createWebHook(WebHookCreateRequest webHookCreateRequest, Principal principal,
                                           BindingResult bindingResult) {

        ResponseEntity<?> errorMap = errorService.validateFields(bindingResult);
        if (errorMap != null) {
            return errorMap;
        }

        // Request -> DTO
        WebHookCreateRequestDto webHookCreateRequestDto = mapper.webHookCreateRequestToDto(webHookCreateRequest);

        // DTO - Response
        WebHookCreateResponse webHookCreateResponse = mapper
                .webHookCreateResponseDtoToResponse(webHookService.saveOrUpdate(webHookCreateRequestDto, principal.getName()));
        log.info("Web hook - created: " + webHookCreateResponse.getName());


        return new ResponseEntity<>(webHookCreateResponse, HttpStatus.CREATED);
    }
}
