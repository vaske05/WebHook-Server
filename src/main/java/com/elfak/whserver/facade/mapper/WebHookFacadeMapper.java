package com.elfak.whserver.facade.mapper;

import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import com.elfak.whserver.facade.model.response.WebHookCreateResponse;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookCreateResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebHookFacadeMapper {

	WebHookCreateRequestDto webHookCreateRequestToDto(WebHookCreateRequest webHookCreateRequest);

	WebHookCreateResponse webHookCreateResponseDtoToResponse(WebHookCreateResponseDto webHookCreateResponseDto);
}
