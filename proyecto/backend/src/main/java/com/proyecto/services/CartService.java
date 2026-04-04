package com.proyecto.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.DTOs.CartAddProductDTO;
import com.proyecto.DTOs.CartDTO;
import com.proyecto.DTOs.CartUpdateDTO;
import com.proyecto.config.ExceptionFactory;
import com.proyecto.models.Cart;
import com.proyecto.models.CartProduct;
import com.proyecto.models.ProductListing;
import com.proyecto.models.User;
import com.proyecto.repositories.CartRepository;
import com.proyecto.repositories.ProductListingRepository;
import com.proyecto.repositories.UserRepository;
import com.proyecto.mappers.CartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Agrega esta anotación para generar el constructor con los campos finales
@SuppressWarnings("null")
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductListingRepository productListingRepository;
    private final CartMapper cartMapper;

    private final ProductListingService productListingService;

    public CartDTO getCartByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionFactory.createUserNotFoundException());

        Cart cart = user.getCart();
        calculateTotals(cart);
        return cartMapper.toDTO(cart);
    }

    private void calculateTotals(Cart cart) {
        BigDecimal total = BigDecimal.ZERO;

        for (CartProduct pl : cart.getProducts()) {
            BigDecimal subtotal = BigDecimal.valueOf(pl.getQuantity())
                    .multiply(pl.getProductListing().getPrice());
            pl.setSubtotal(subtotal);
            total = total.add(subtotal);
        }
        cart.setTotal(total);
    }

    public CartDTO updateCart(String email, CartUpdateDTO dto) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionFactory.createUserNotFoundException());

        Cart cart = user.getCart();

        CartProduct product = cart.getProducts().stream()
                .filter(i -> i.getProductListing().getId().equals(dto.productListingId()))
                .findFirst()
                .orElseThrow(() -> ExceptionFactory.createCartProductNotFoundException());

        int newQuantity = product.getQuantity() + dto.quantity();

        if (newQuantity <= 0) {
            cart.getProducts().remove(product);
        } else {
            product.setQuantity(newQuantity);
        }

        return cartMapper.toDTO(cartRepository.save(cart));

    }

    @Transactional
    public CartDTO addProduct(String email, CartAddProductDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionFactory.createUserNotFoundException());

        ProductListing productListing = productListingRepository.findById(dto.productListingId())
                .orElseThrow(() -> ExceptionFactory.createProductListingNotFoundException());

        Cart cart = user.getCart();

        productListingService.checkStock(productListing.getId(), dto.quantity());

        addProductToCart(cart, productListing, dto.quantity());
        cartRepository.save(cart);
        return cartMapper.toDTO(cart);
    }

    private void addProductToCart(Cart cart, ProductListing productListing, Integer quantity) {
        CartProduct existItem = cart.getProducts()
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
            cart.getProducts().add(newCartProduct);
        }
    }
}
