package com.proyecto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyecto.DTOs.OrderDetailsDTO;
import com.proyecto.models.OrderDetails;

@Mapper(componentModel = "spring")
public interface DetalleCompraMapper {

    @Mapping(source = "orderDetail.productListing.product.name", target = "productName")
    @Mapping(source = "orderDetail.quantity", target = "quantity")
    @Mapping(source = "orderDetail.unitPrice", target = "unitPrice")
    OrderDetailsDTO toDTO(OrderDetails orderDetail);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "productListing", ignore = true)
    OrderDetails toEntity(OrderDetailsDTO detalleCompraDTO);
}
