package com.proyecto.cart.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.proyecto.cart.dto.CartAddProductDTO;
import com.proyecto.cart.dto.CartDTO;
import com.proyecto.cart.entity.Cart;
import com.proyecto.cart.mapper.CartMapper;
import com.proyecto.cart.repository.CartRepository;
import com.proyecto.client.entity.Client;
import com.proyecto.client.repository.ClientRepository;
import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.productListing.entity.ProductListing;
import com.proyecto.productListing.repository.ProductListingRepository;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor // Agrega esta anotación para generar el constructor con los campos finales
public class CartService {

    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final ProductListingRepository productListingRepository;
    private final CartMapper cartMapper;

    public CartDTO findCart(UUID clientId) {
        Cart cart = cartRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado"));
        return cartMapper.toDTO(cart);
    }

    public CartDTO addProduct(CartAddProductDTO dto) {
        Client client = clientRepository.findById(dto.clientId())
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
