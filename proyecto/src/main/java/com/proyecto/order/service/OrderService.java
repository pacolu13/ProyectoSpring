package com.proyecto.order.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.cartProduct.entity.CartProduct;
import com.proyecto.client.entity.Client;
import com.proyecto.client.repository.ClientRepository;
import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.order.dto.OrderDTO;
import com.proyecto.order.entity.Order;
import com.proyecto.order.mapper.OrderMapper;
import com.proyecto.order.repository.OrderRepository;
import com.proyecto.orderDetails.entity.OrderDetails;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor

public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OrderMapper orderMapper;

    @Transactional // O se ejecuta todo o no se ejecuta nada, rollback en caso de error
    public OrderDTO confirmarCompra(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        if (client.getCart() == null || client.getCart().getProductsList().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        BigDecimal total = BigDecimal.ZERO;
        List<CartProduct> productList = client.getCart().getProductsList();

        for (CartProduct producto : productList) {
            if (producto.getProductListing().getQuantity() < producto.getQuantity()) {
                throw new IllegalStateException("Stock insuficiente para el producto: "
                        + producto.getProductListing().getProduct().getName());
            }
            Integer cantComprada = producto.getQuantity();
            Integer stockActual = producto.getProductListing().getQuantity();
            BigDecimal precioUnitario = producto.getProductListing().getPrice();

            producto.getProductListing().setQuantity(stockActual - cantComprada);
            total = total.add(precioUnitario.multiply(BigDecimal.valueOf(cantComprada)));
        }

        if (total.compareTo(client.getBalance()) > 0) {
            throw new IllegalStateException("Saldo insuficiente para realizar la compra");
        }

        Order compra = new Order();
        compra.setClient(client);
        compra.setTotalBalance(total);

        for (CartProduct producto : productList) {
            OrderDetails detail = new OrderDetails();
            detail.setOrder(compra);
            detail.setProductListing(producto.getProductListing());
            detail.setUnitPrice(producto.getProductListing().getPrice());
            detail.setQuantity(producto.getQuantity());
            compra.getOrderDetailsList().add(detail);
        }

        client.setBalance(client.getBalance().subtract(total));
        client.getCart().getProductsList().clear();

        return orderMapper.toDTO(orderRepository.save(compra));
    }
}
