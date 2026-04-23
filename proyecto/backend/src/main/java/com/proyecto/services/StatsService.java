package com.proyecto.services;

import com.proyecto.DTOs.StatsDTO;
import com.proyecto.repositories.OrderRepository;
import com.proyecto.repositories.ProductRepository;
import com.proyecto.repositories.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class StatsService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public StatsDTO getStatsValue() {
        return StatsDTO.builder()
                .cantOrders(orderRepository.count())
                .cantProducts(productRepository.count())
                .cantUsers(userRepository.count()).build();
    }

}
