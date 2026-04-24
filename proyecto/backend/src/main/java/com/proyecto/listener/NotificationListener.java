package com.proyecto.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.proyecto.DTOs.NotificationMessageDTO;
import com.proyecto.config.RabbitConfig;
import com.proyecto.services.MailService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final MailService mailService;

    @RabbitListener(queues = RabbitConfig.NOTIFICATION_QUEUE)
    public void handle(NotificationMessageDTO message) {
        mailService.sendAdminNotification(
            message.toEmail(),
            message.subject(),
            message.body()
        );
    }
}