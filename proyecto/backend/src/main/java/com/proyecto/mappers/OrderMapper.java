package com.proyecto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyecto.DTOs.OrderDTO;
import com.proyecto.models.Order;

@Mapper(componentModel = "spring", uses = DetalleCompraMapper.class)
public interface OrderMapper {

    @Mapping(source = "order.user.username", target = "clientUsername")
    @Mapping(source = "order.totalBalance", target = "totalBalance")
    @Mapping(source = "order.orderDate", target = "orderDate")
    OrderDTO toDTO(Order order);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderDTO compraDTO);

}
