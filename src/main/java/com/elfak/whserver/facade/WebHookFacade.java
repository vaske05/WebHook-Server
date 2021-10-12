package com.elfak.whserver.facade;

import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.security.Principal;

public interface WebHookFacade {

    ResponseEntity<?> createWebHook(WebHookCreateRequest webHookCreateRequest, Principal principal,
                                    BindingResult bindingResult);

    ResponseEntity<?> findAllUserWebHooks(Principal principal);
}
