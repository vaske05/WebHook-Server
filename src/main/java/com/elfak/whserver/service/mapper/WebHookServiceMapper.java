package com.elfak.whserver.service.mapper;

import com.elfak.whserver.model.WebHook;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookCreateResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebHookServiceMapper {

    WebHook webHookCreateRequestDtoToWebHook(WebHookCreateRequestDto webHookCreateRequestDto);

    WebHookCreateResponseDto webHookToWebHookCreateResponseDto(WebHook webHook);
}
