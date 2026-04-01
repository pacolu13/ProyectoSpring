package com.proyecto.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.cart.mapper.CartMapper;
import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.models.Cart;
import com.proyecto.models.CartAddProductDTO;
import com.proyecto.models.CartDTO;
import com.proyecto.models.CartProduct;
import com.proyecto.models.CartUpdateDTO;
import com.proyecto.models.ProductListing;
import com.proyecto.models.User;
import com.proyecto.repositories.CartRepository;
import com.proyecto.repositories.ProductListingRepository;
import com.proyecto.repositories.UserRepository;

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

    public CartDTO updateCart(String email, CartUpdateDTO dto) {

        System.out.println("\n" + dto + "\n");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Cart cart = user.getCart();

        CartProduct product = cart.getProductsList().stream()
                .filter(i -> i.getProductListing().getId().equals(dto.productListingId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        int newQuantity = product.getQuantity() + dto.quantity();

        if (newQuantity <= 0) {
            cart.getProductsList().remove(product);
        } else {
            product.setQuantity(newQuantity);
        }

        return cartMapper.toDTO(cartRepository.save(cart));

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
