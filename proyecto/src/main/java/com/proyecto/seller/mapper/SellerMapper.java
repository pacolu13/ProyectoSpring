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
import com.proyecto.seller.entity.Seller;

@Mapper(componentModel="spring")
public interface SellerMapper {

    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolesList", ignore = true)
    @Mapping(target = "productsListingList", ignore = true)
    Seller toEntity(SellerCreateDTO dto);

    List<Seller> toEntityList(List<SellerCreateDTO> dtoList);

    SellerDTO toDTO(Seller vendedor);

    SellerCreateDTO toCreateDTO(Seller vendedor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolesList", ignore = true)
    @Mapping(target = "productsListingList", ignore = true)
    Seller updateSellerFromDto(SellerUpdateDTO dto, @MappingTarget Seller entity);

    List<SellerDTO> toDTOList(List<Seller> vendedor);

}
