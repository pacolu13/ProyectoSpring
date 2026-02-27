package com.proyecto.orderDetails.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyecto.orderDetails.dto.OrderDetailsDTO;
import com.proyecto.orderDetails.entity.OrderDetails;

@Mapper(componentModel = "spring")
public interface DetalleCompraMapper {

    @Mapping(source = "orderDetail.productListing.product.name", target = "productName")
    @Mapping(source = "orderDetail.quantity", target = "quantity")
    @Mapping(source = "orderDetail.unitPrice", target = "unitPrice")
    OrderDetailsDTO toDTO(OrderDetails orderDetail);

    OrderDetails toEntity(OrderDetailsDTO detalleCompraDTO);
}
