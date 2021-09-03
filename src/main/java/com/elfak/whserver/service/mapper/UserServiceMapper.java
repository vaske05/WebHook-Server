package com.elfak.whserver.service.mapper;

import com.elfak.whserver.service.dto.UserRegisterRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elfak.whserver.model.User;
import com.elfak.whserver.service.dto.UserRegisterResponseDTO;

@Mapper(componentModel = "spring")
public interface UserServiceMapper {
    User userRegisterRequestDtoToUser(UserRegisterRequestDTO userRegisterRequestDTO);

    @Mapping(source = "id", target = "userId")
    UserRegisterResponseDTO userToUserRegisterResponseDto(User user);
}
