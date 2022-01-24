package com.elfak.whserver.facade;

import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.security.Principal;

public interface WebHookFacade {

    ResponseEntity<?> createWebHook(WebHookCreateRequest webHookCreateRequest, Principal principal,
                                    BindingResult bindingResult);

    ResponseEntity<?> findAllUserWebHooks(Principal principal);

    ResponseEntity<?> getWebHookById(Long webHookId, Principal principal);

    ResponseEntity<?> deleteWebHook(Long webHookId, Principal principal);

    ResponseEntity<?> getCovidSelectCountries();

    ResponseEntity<?> getAirQualitySelectCountries();

    ResponseEntity<?> getAirQualitySelectRegions(String country);

    ResponseEntity<?> getAirQualitySelectCities(String country, String region);
}
