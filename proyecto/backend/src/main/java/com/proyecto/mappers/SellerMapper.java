package com.proyecto.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.proyecto.DTOs.SellerDTO;
import com.proyecto.DTOs.SellerUpdateDTO;
import com.proyecto.models.User;

@Mapper(componentModel="spring")
public interface SellerMapper {

    SellerDTO toDTO(User seller);

    User updateSellerFromDto(SellerUpdateDTO dto, @MappingTarget User entity);

    List<SellerDTO> toDTOList(List<User> sellers);

}
