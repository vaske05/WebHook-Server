package com.elfak.whserver.service;

import com.elfak.whserver.enumeration.WebHookType;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookCreateResponseDto;
import com.elfak.whserver.service.dto.WebHookDTO;
import com.elfak.whserver.service.dto.WebHooksResponseDTO;

import java.util.List;
import java.util.Optional;

public interface WebHookService {

    WebHookCreateResponseDto saveOrUpdate(WebHookCreateRequestDto webHookCreateRequestDto, String email);

    WebHooksResponseDTO findAllUserWebHooks(String email);

    WebHookDTO findByUrl(String url);

    Optional<WebHookDTO> findById(Long id);

    void delete(Long id);

    List<WebHookDTO> findWebHooksByType(WebHookType webHookType);
}
