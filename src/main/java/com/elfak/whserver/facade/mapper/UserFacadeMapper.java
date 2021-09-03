package com.elfak.whserver.facade.mapper;

import com.elfak.whserver.facade.model.request.UserRegisterRequest;
import com.elfak.whserver.service.dto.UserRegisterResponseDTO;
import org.mapstruct.Mapper;

import com.elfak.whserver.facade.model.response.UserRegisterResponse;
import com.elfak.whserver.service.dto.UserRegisterRequestDTO;

@Mapper(componentModel = "spring")
public interface UserFacadeMapper {

	UserRegisterRequestDTO userRegisterRequestToDto(UserRegisterRequest userRegisterRequest);

	UserRegisterResponse userRegisterDtoToResponse(UserRegisterResponseDTO userRegisterResponseDTO);
}
