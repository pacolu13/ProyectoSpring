package com.proyecto.seller.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.seller.dto.SellerCreateDTO;
import com.proyecto.seller.dto.SellerDTO;
import com.proyecto.seller.dto.SellerUpdateDTO;
import com.proyecto.user.entity.User;

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
