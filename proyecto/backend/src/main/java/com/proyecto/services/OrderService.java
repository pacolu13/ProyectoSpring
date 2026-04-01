package com.proyecto.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.DTOs.OrderDTO;
import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.mappers.OrderMapper;
import com.proyecto.models.CartProduct;
import com.proyecto.models.Order;
import com.proyecto.models.OrderDetails;
import com.proyecto.models.User;
import com.proyecto.repositories.OrderRepository;
import com.proyecto.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor

public class OrderService {

    private final MailService mailService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDTO orderSubmit(String email) {
        User client = getClientWithCart(email);
        List<CartProduct> items = client.getCart().getProducts();

        validateStock(items);
        BigDecimal total = calculateTotal(items);
        validateBalance(client, total);

        Order order = createOrder(client, items, total);
        applyPostPurchaseEffects(client, items, total);

        mailService.sendOrderConfirmation(email, order);

        return orderMapper.toDTO(orderRepository.save(order));
    }

    private User getClientWithCart(String email) {
        User client = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        if (client.getCart() == null || client.getCart().getProducts().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }
        return client;
    }

    private Order createOrder(User client, List<CartProduct> items, BigDecimal total) {
        Order order = new Order();
        order.setUser(client);
        order.setTotalBalance(total);

        for (CartProduct item : items) {
            OrderDetails detail = new OrderDetails();
            detail.setOrder(order);
            detail.setProductListing(item.getProductListing());
            detail.setUnitPrice(item.getProductListing().getPrice());
            detail.setQuantity(item.getQuantity());
            order.getOrderDetailsList().add(detail);
        }

        return order;
    }

    private void validateStock(List<CartProduct> items) {
        for (CartProduct item : items) {
            if (item.getProductListing().getStock() < item.getQuantity()) {
                throw new IllegalStateException("Stock insuficiente: "
                        + item.getProductListing().getProduct().getName());
            }
        }
    }

    private BigDecimal calculateTotal(List<CartProduct> items) {
        return items.stream()
                .map(i -> i.getProductListing().getPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validateBalance(User client, BigDecimal total) {
        if (total.compareTo(client.getBalance()) > 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }
    }

    private void applyPostPurchaseEffects(User client, List<CartProduct> items, BigDecimal total) {
        client.setBalance(client.getBalance().subtract(total));
        client.getCart().getProducts().clear();

        for (CartProduct item : items) {
            int cantidad = item.getQuantity();
            item.getProductListing().setStock(
                    item.getProductListing().getStock() - cantidad);
            User seller = item.getProductListing().getUser();
            seller.setTotalSales(seller.getTotalSales() + cantidad);
            // Nuestras ventas cuentan la cantidad de productos vendidos
        }
    }
}
