package com.elfak.whserver.service;

import com.elfak.whserver.model.WebHook;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookCreateResponseDto;

public interface WebHookService {

    WebHookCreateResponseDto saveOrUpdate(WebHookCreateRequestDto webHookCreateRequestDto, String email);

    WebHook findByUrl(String url);

    WebHook findById(Long id);
}
