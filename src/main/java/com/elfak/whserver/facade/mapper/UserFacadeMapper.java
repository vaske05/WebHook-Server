package com.elfak.whserver.facade.mapper;

import org.mapstruct.Mapper;

import com.elfak.whserver.facade.model.request.UserRequest;
import com.elfak.whserver.facade.model.response.UserResponse;
import com.elfak.whserver.service.dto.UserRequestDTO;
import com.elfak.whserver.service.dto.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserFacadeMapper {

	UserRequestDTO userRequestToDto(UserRequest userRequest);

	UserResponse userDtoToResponse(UserResponseDTO userResponseDTO);
}
