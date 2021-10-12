package com.elfak.whserver.service;

import com.elfak.whserver.model.WebHook;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookCreateResponseDto;
import com.elfak.whserver.service.dto.WebHooksResponseDTO;

public interface WebHookService {

    WebHookCreateResponseDto saveOrUpdate(WebHookCreateRequestDto webHookCreateRequestDto, String email);

    WebHooksResponseDTO findAllUserWebHooks(String email);

    WebHook findByUrl(String url); // TODO

    WebHook findById(Long id); // TODO
}
