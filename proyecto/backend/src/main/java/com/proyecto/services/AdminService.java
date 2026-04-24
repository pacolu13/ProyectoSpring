package com.proyecto.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.proyecto.DTOs.NotificationMessageDTO;
import com.proyecto.config.RabbitConfig;
import com.proyecto.models.ProductListing;
import com.proyecto.models.RolEnum;
import com.proyecto.models.User;
import com.proyecto.repositories.ProductListingRepository;
import com.proyecto.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class AdminService {

    private final ProductListingRepository productListingRepository;
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    public void deletePost(Long listingId, String adminEmail) {
        // Verificar que el usuario es admin
        User admin = userRepository.findByEmail(adminEmail).orElseThrow();
        boolean isAdmin = admin.getRoles().stream()
                .anyMatch(rol -> rol.getName() == RolEnum.ADMIN);

        if (!isAdmin) {
            throw new RuntimeException("El usuario no tiene permisos de administrador");
        }

        // Obtener el listing antes de eliminarlo para acceder al dueño
        ProductListing listing = productListingRepository.findById(listingId).orElseThrow();
        User owner = listing.getUser();

        // Eliminar la publicación
        productListingRepository.deleteById(listingId);

        // Notificar al dueño por RabbitMQ
        NotificationMessageDTO notification = new NotificationMessageDTO(
                owner.getEmail(),
                "Tu publicación fue eliminada",
                "Un administrador eliminó tu publicación: " + listing.getTitle());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,
                RabbitConfig.NOTIFICATION_ROUTING_KEY,
                notification);
    }
}
