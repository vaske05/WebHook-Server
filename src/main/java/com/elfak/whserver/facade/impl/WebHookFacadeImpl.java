package com.elfak.whserver.facade.impl;

import com.elfak.whserver.facade.WebHookFacade;
import com.elfak.whserver.facade.mapper.WebHookFacadeMapper;
import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import com.elfak.whserver.facade.model.response.WebHookCreateResponse;
import com.elfak.whserver.facade.model.response.WebHooksResponse;
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

    @Override
    public ResponseEntity<?> findAllUserWebHooks(Principal principal) {

        log.info("Web hook - find all user webHooks by email: " + principal.getName());

        WebHooksResponseDTO webHooksResponseDTO = webHookService.findAllUserWebHooks(principal.getName());
        // DTO -> Response
        WebHooksResponse webHooksResponse = mapper.webHooksResponseDtoToWebHooksResponse(webHooksResponseDTO);

        return new ResponseEntity<>(webHooksResponse.getWebHooks(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteWebHook(Long webHookId, Principal principal) {
        Optional<WebHookDTO> optionalWebHookDTO = webHookService.findById(webHookId);
        WebHookDTO webHookDTO = optionalWebHookDTO.orElseThrow(); // TODO: Throw wh not found exception

        if (!webHookDTO.getUser().getEmail().equals(principal.getName())) {
            //throw new NotFoundException("Web hook not found in your account");
            log.info("Web hook not found in your account"); // TODO: Throw wh not found exception
            return new ResponseEntity<>(new WebHookDTO(), HttpStatus.BAD_REQUEST);
        }
        webHookService.delete(webHookDTO.getId());

        return new ResponseEntity<>("Web hook with ID: '" + webHookId + "' was deleted", HttpStatus.OK);
    }
}
