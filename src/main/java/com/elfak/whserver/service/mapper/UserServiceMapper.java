package com.elfak.whserver.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elfak.whserver.model.User;
import com.elfak.whserver.service.dto.UserRequestDTO;
import com.elfak.whserver.service.dto.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserServiceMapper {
    User userRequestDtoToUser(UserRequestDTO userRequestDTO);

    @Mapping(source = "id", target = "userId")
    UserResponseDTO userToUserResponseDto(User user);
}
