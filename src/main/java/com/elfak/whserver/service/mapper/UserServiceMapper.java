package com.elfak.whserver.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elfak.whserver.model.User;
import com.elfak.whserver.service.dto.UserRegistrationRequestDTO;
import com.elfak.whserver.service.dto.UserRegistrationResponseDTO;

@Mapper(componentModel = "spring")
public interface UserServiceMapper {
    User userRegistrationRequestDtoToUser(UserRegistrationRequestDTO userRegistrationRequestDTO);

    @Mapping(source = "id", target = "userId")
    UserRegistrationResponseDTO userToUserRegistrationResponseDto(User user);
}
