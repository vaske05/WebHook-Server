package com.elfak.whserver.facade.mapper;

import com.elfak.whserver.facade.model.request.UserLoginRequest;
import com.elfak.whserver.facade.model.request.UserRegistrationRequest;
import com.elfak.whserver.facade.model.response.JWTLoginSuccessResponse;
import com.elfak.whserver.facade.model.response.UserRegistrationResponse;
import com.elfak.whserver.service.dto.user.JWTLoginSuccessResponseDTO;
import com.elfak.whserver.service.dto.user.UserLoginRequestDTO;
import com.elfak.whserver.service.dto.user.UserRegistrationRequestDTO;
import com.elfak.whserver.service.dto.user.UserRegistrationResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserFacadeMapper {

	UserRegistrationRequestDTO userRegistrationRequestToDto(UserRegistrationRequest userRegistrationRequest);

	UserRegistrationResponse userRegistrationDtoToResponse(UserRegistrationResponseDTO userRegistrationResponseDTO);

	UserLoginRequestDTO userLoginRequestToDto(UserLoginRequest userLoginRequest);

	JWTLoginSuccessResponse jwtLoginSuccessDtoToResponse(JWTLoginSuccessResponseDTO jwtLoginSuccessResponseDTO);

}
