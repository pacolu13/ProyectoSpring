package com.proyecto.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyecto.order.dto.OrderDTO;
import com.proyecto.order.entity.Order;
import com.proyecto.orderDetails.mapper.DetalleCompraMapper;

@Mapper(componentModel = "spring", uses = DetalleCompraMapper.class)
public interface OrderMapper {

    @Mapping(source = "order.client.name", target = "clientName")
    @Mapping(source = "order.client.lastName", target = "clientLastName")
    @Mapping(source = "order.totalBalance", target = "totalBalance")
    @Mapping(source = "order.orderDate", target = "orderDate")
    OrderDTO toDTO(Order order);

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderDTO compraDTO);

}
