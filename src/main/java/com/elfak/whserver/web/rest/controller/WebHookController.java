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

    @GetMapping(value = "/get/{webHookId}")
    ResponseEntity<?> getWebHookById(@PathVariable Long webHookId, Principal principal);

    @DeleteMapping(value = "/delete/{webHookId}")
    ResponseEntity<?> deleteWebHook(@PathVariable Long webHookId, Principal principal);

    @GetMapping(value = "/getCovidSelectCountries")
    ResponseEntity<?> getCovidSelectCountries();

    @GetMapping(value = "/getAirSelectCountries")
    ResponseEntity<?> getAirQualitySelectCountries();

    @GetMapping(value = "/getAirSelectRegions")
    ResponseEntity<?> getAirQualitySelectRegions(@RequestParam String country);

    @GetMapping(value = "/getAirSelectCities")
    ResponseEntity<?> getAirQualitySelectCities(@RequestParam String country, @RequestParam String region);
}
