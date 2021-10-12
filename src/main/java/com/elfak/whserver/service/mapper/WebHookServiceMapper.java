package com.elfak.whserver.service.mapper;

import com.elfak.whserver.model.WebHook;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookCreateResponseDto;
import com.elfak.whserver.service.dto.WebHooksResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WebHookServiceMapper {

    WebHook webHookCreateRequestDtoToWebHook(WebHookCreateRequestDto webHookCreateRequestDto);

    WebHookCreateResponseDto webHookToWebHookCreateResponseDto(WebHook webHook);

    WebHooksResponseDTO.WebHookResponseDTO webHookToWebHookResponseDTO(WebHook webHook);

    List<WebHooksResponseDTO.WebHookResponseDTO> webHooksToWebHooksResponseDTO(List<WebHook> webHooks);
}
