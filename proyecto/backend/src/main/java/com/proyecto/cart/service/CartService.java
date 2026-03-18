package com.proyecto.cart.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.cart.dto.CartAddProductDTO;
import com.proyecto.cart.dto.CartDTO;
import com.proyecto.cart.entity.Cart;
import com.proyecto.cart.mapper.CartMapper;
import com.proyecto.cart.repository.CartRepository;
import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.productListing.entity.ProductListing;
import com.proyecto.productListing.repository.ProductListingRepository;
import com.proyecto.user.entity.User;
import com.proyecto.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor // Agrega esta anotación para generar el constructor con los campos finales
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductListingRepository productListingRepository;
    private final CartMapper cartMapper;

    public CartDTO getCartByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Cart cart = Optional.ofNullable(user.getCart())
                .orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado"));

        return cartMapper.toDTO(cart);
    }

    public CartDTO findCart(UUID clientId) {
        Cart cart = cartRepository.findByUserId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado"));
        return cartMapper.toDTO(cart);
    }

    @Transactional
    public CartDTO addProduct(CartAddProductDTO dto) {
        User client = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        ProductListing productListing = productListingRepository.findById(dto.productListingId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto Venta no encontrado"));

        Cart cart = client.getCart();

        if (!productListing.hayCantidad(dto.quantity())) {
            throw new ResourceNotFoundException("Stock insuficiente");
        }

        cart.addProduct(productListing, dto.quantity());
        cartRepository.save(cart);
        return cartMapper.toDTO(cart);
    }
}
