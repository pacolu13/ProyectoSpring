package com.proyecto.cart.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.cart.dto.CartAddProductDTO;
import com.proyecto.cart.dto.CartDTO;
import com.proyecto.cart.entity.Cart;
import com.proyecto.cart.mapper.CartMapper;
import com.proyecto.cart.repository.CartRepository;
import com.proyecto.cartProduct.entity.CartProduct;
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

        calculateTotals(cart);
        return cartMapper.toDTO(cart);
    }

    private void calculateTotals(Cart cart) {
        BigDecimal total = BigDecimal.ZERO;

        for (CartProduct pl : cart.getProductsList()) {
            BigDecimal subtotal = BigDecimal.valueOf(pl.getQuantity())
                    .multiply(pl.getProductListing().getPrice());
            pl.setSubtotal(subtotal);
            total = total.add(subtotal);
        }
        cart.setTotal(total);
    }

    @Transactional
    public CartDTO addProduct(String email, CartAddProductDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        ProductListing productListing = productListingRepository.findById(dto.productListingId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto Venta no encontrado"));

        Cart cart = user.getCart();

        if (!productListing.hayCantidad(dto.quantity())) {
            throw new ResourceNotFoundException("Stock insuficiente");
        }

        addProductToCart(cart, productListing, dto.quantity());
        cartRepository.save(cart);
        return cartMapper.toDTO(cart);
    }

    private void addProductToCart(Cart cart, ProductListing productListing, Integer quantity) {
        CartProduct existItem = cart.getProductsList()
                .stream()
                .filter(cp -> cp.getProductListing().getId().equals(productListing.getId()))
                .findFirst()
                .orElse(null);

        if (existItem != null) {
            existItem.setQuantity(existItem.getQuantity() + quantity);
        } else {
            CartProduct newCartProduct = new CartProduct();
            newCartProduct.setCart(cart);
            newCartProduct.setProductListing(productListing);
            newCartProduct.setQuantity(quantity);
            cart.getProductsList().add(newCartProduct);
        }
    }
}
