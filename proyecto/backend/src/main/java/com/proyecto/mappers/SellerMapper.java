package com.proyecto.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.DTOs.SellerCreateDTO;
import com.proyecto.DTOs.SellerDTO;
import com.proyecto.DTOs.SellerUpdateDTO;
import com.proyecto.models.User;

@Mapper(componentModel="spring")
public interface SellerMapper {

    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "productsListingList", ignore = true)
    User toEntity(SellerCreateDTO dto);

    List<User> toEntityList(List<SellerCreateDTO> dtoList);

    SellerDTO toDTO(User vendedor);

    SellerCreateDTO toCreateDTO(User vendedor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "productsListingList", ignore = true)
    User updateSellerFromDto(SellerUpdateDTO dto, @MappingTarget User entity);

    List<SellerDTO> toDTOList(List<User> vendedor);

}
