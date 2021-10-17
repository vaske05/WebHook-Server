package com.elfak.whserver.web.rest.controller.impl;

import com.elfak.whserver.facade.WebHookFacade;
import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import com.elfak.whserver.web.rest.controller.WebHookController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WebHookControllerImpl implements WebHookController {

    private final WebHookFacade webHookFacade;

    @Override
    public ResponseEntity<?> createWebHook(WebHookCreateRequest webHookCreateRequest, BindingResult bindingResult,
                                           Principal principal) {
        return webHookFacade.createWebHook(webHookCreateRequest, principal, bindingResult);
    }

    @Override
    public ResponseEntity<?> findAllUserWebHooks(Principal principal) {
        return webHookFacade.findAllUserWebHooks(principal);
    }

    @Override
    public ResponseEntity<?> deleteWebHook(Long webHookId, Principal principal) {
        return webHookFacade.deleteWebHook(webHookId, principal);
    }
}
