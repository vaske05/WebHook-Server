package com.elfak.whserver.facade.mapper;

import com.elfak.whserver.facade.model.request.WebHookCreateRequest;
import com.elfak.whserver.facade.model.response.WebHookCreateResponse;
import com.elfak.whserver.facade.model.response.WebHooksResponse;
import com.elfak.whserver.service.dto.WebHookCreateRequestDto;
import com.elfak.whserver.service.dto.WebHookCreateResponseDto;
import com.elfak.whserver.service.dto.WebHooksResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WebHookFacadeMapper {

	WebHookCreateRequestDto webHookCreateRequestToDto(WebHookCreateRequest webHookCreateRequest);

	WebHookCreateResponse webHookCreateResponseDtoToResponse(WebHookCreateResponseDto webHookCreateResponseDto);

	WebHooksResponse.WebHookResponse webHookResponseDtoToWebHookResponse(WebHooksResponseDTO.WebHookResponseDTO webHookResponseDTO);

	@Mapping(source = "webHooksDto", target = "webHooks")
	WebHooksResponse webHooksResponseDtoToWebHooksResponse(WebHooksResponseDTO webHooksResponseDTO);
}
