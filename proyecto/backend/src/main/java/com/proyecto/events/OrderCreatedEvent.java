package com.proyecto.events;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor

public class OrderCreatedEvent {
    private Long orderId;
    private String email;
}
