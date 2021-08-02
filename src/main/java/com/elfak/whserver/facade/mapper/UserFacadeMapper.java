package com.elfak.whserver.facade.mapper;

import org.mapstruct.Mapper;

import com.elfak.whserver.facade.model.request.UserLoginRequest;
import com.elfak.whserver.facade.model.request.UserRegistrationRequest;
import com.elfak.whserver.facade.model.response.JWTLoginSuccessResponse;
import com.elfak.whserver.facade.model.response.UserRegistrationResponse;
import com.elfak.whserver.service.dto.JWTLoginSuccessResponseDTO;
import com.elfak.whserver.service.dto.UserLoginRequestDTO;
import com.elfak.whserver.service.dto.UserRegistrationRequestDTO;
import com.elfak.whserver.service.dto.UserRegistrationResponseDTO;

@Mapper(componentModel = "spring")
public interface UserFacadeMapper {

	UserRegistrationRequestDTO userRegistrationRequestToDto(UserRegistrationRequest userRegistrationRequest);

	UserRegistrationResponse userRegistrationDtoToResponse(UserRegistrationResponseDTO userRegistrationResponseDTO);

	UserLoginRequestDTO userLoginRequestToDto(UserLoginRequest userLoginRequest);

	JWTLoginSuccessResponse jwtLoginSuccessDtoToResponse(JWTLoginSuccessResponseDTO jwtLoginSuccessResponseDTO);

}
