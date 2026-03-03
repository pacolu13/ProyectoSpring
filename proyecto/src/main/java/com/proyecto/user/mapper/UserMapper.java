package com.proyecto.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.proyecto.user.dto.UserCreateDTO;
import com.proyecto.user.dto.UserDTO;
import com.proyecto.user.entity.User;

@Mapper(componentModel = "spring")

public interface UserMapper {

    User toEntity(UserCreateDTO dto);

    List<User> toEntityList(List<UserCreateDTO> users);

    UserDTO toDTO(User response);

    List<UserDTO> toDTOList(List<User> users);

    
} 
