package com.proyecto.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.proyecto.config.ExceptionFactory;
import com.proyecto.events.OrderCreatedEvent;
import com.proyecto.models.Order;
import com.proyecto.repositories.OrderRepository;
import com.proyecto.services.MailService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@SuppressWarnings("null")
public class OrderCreatedListener {
    private final MailService mailService;
    private final OrderRepository orderRepository;

    @RabbitListener(queues = "order.created.queue")

    public void handle(OrderCreatedEvent event){
        System.out.println("Evento: " + event.getOrderId());
        
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow(
            () -> ExceptionFactory.createNotFoundException()
        );

        mailService.sendOrderConfirmation(event.getEmail(), order);
    }
}
