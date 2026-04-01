package com.proyecto.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.proyecto.DTOs.UserCreateDTO;
import com.proyecto.DTOs.UserDTO;
import com.proyecto.models.User;

@Mapper(componentModel = "spring")

public interface UserMapper {

    User toEntity(UserCreateDTO dto);

    List<User> toEntityList(List<UserCreateDTO> users);

    UserDTO toDTO(User response);

    List<UserDTO> toDTOList(List<User> users);

}
