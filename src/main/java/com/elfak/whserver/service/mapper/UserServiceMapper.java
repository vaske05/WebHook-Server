package com.elfak.whserver.service.mapper;

import com.elfak.whserver.model.User;
import com.elfak.whserver.service.dto.user.UserRegistrationRequestDTO;
import com.elfak.whserver.service.dto.user.UserRegistrationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserServiceMapper {
    User userRegistrationRequestDtoToUser(UserRegistrationRequestDTO userRegistrationRequestDTO);

    @Mapping(source = "id", target = "userId")
    UserRegistrationResponseDTO userToUserRegistrationResponseDto(User user);
}
