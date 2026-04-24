package com.proyecto.DTOs;

import lombok.Builder;

@Builder

public record NotificationMessageDTO(
    String toEmail,
    String subject,
    String body
) {

}
