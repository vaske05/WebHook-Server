package com.elfak.whserver.web.rest.controller;

import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import com.elfak.whserver.web.WebConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = WebConstant.WH_BASE_URL, produces = APPLICATION_JSON_VALUE)
public interface WebHookController {

    @PostMapping(value = "/create")
    ResponseEntity<?> createWebHook(@Valid @RequestBody WebHookCreateRequest webHookCreateRequest,
                                    BindingResult bindingResult, Principal principal);

    @GetMapping(value = "/all")
    ResponseEntity<?> findAllUserWebHooks(Principal principal);

    @DeleteMapping(value = "/delete/{webHookId}")
    ResponseEntity<?> deleteWebHook(@PathVariable Long webHookId, Principal principal);
}
