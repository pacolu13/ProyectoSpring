package com.proyecto.config;

import java.util.Optional;

import com.proyecto.exceptions.ResourceNotFoundException;

public class ExceptionFactory {

    private ExceptionFactory() {

    }

    // Message
    private static final String SELLER_NOT_FOUND = "Seller not found";
    private static final String SELLERS_NOT_FOUND = "No sellers found with the specified filters";
    private static final String PRODUCT_NOT_FOUND = "Product not found";
    private static final String CART_PRODUCT_NOT_FOUND = "Product not found in cart";
    private static final String PRODUCT_LISTING_NOT_FOUND = "Product listing not found";
    private static final String ORDER_NOT_FOUND = "Order not found";
    private static final String USER_NOT_FOUND = "User not found";
    private static final String CATEGORY_NOT_FOUND = "Category not found";

    private static final String USER_BALANCE_INSUFFICIENT = "Balance is insufficient for this purchase";
    private static final String PRODUCT_OUT_OF_STOCK = "One or more products in the cart are out of stock";
    private static final String CART_EMPTY = "The cart is empty";

    private static final String TOKEN_INVALID = "Invalid token";

    // Generic method to handle Optional and throw exceptions
    public static <T> T orElseThrow(Optional<T> optional, String message) {
        return optional.orElseThrow(() -> new ResourceNotFoundException(message));
    }

    // Methods
    public static RuntimeException createSellerNotFoundException() {
        return new ResourceNotFoundException(SELLER_NOT_FOUND);
    }

    public static RuntimeException createSellersNotFoundException() {
        return new ResourceNotFoundException(SELLERS_NOT_FOUND);
    }

    public static RuntimeException createProductNotFoundException() {
        return new ResourceNotFoundException(PRODUCT_NOT_FOUND);
    }

    public static RuntimeException createCartProductNotFoundException() {
        return new ResourceNotFoundException(CART_PRODUCT_NOT_FOUND);
    }

    public static RuntimeException createProductListingNotFoundException() {
        return new ResourceNotFoundException(PRODUCT_LISTING_NOT_FOUND);
    }

    public static RuntimeException createOrderNotFoundException() {
        return new ResourceNotFoundException(ORDER_NOT_FOUND);
    }

    public static RuntimeException createUserNotFoundException() {
        return new ResourceNotFoundException(USER_NOT_FOUND);
    }

    public static RuntimeException createCategoryNotFoundException() {
        return new ResourceNotFoundException(CATEGORY_NOT_FOUND);
    }

    public static RuntimeException createUserBalanceInsufficientException() {
        return new IllegalStateException(USER_BALANCE_INSUFFICIENT);
    }

    public static RuntimeException createProductOutOfStockException() {
        return new IllegalStateException(PRODUCT_OUT_OF_STOCK);
    }

    public static RuntimeException createCartEmptyException() {
        return new IllegalStateException(CART_EMPTY);
    }

    public static RuntimeException createTokenInvalidException() {
        return new IllegalArgumentException(TOKEN_INVALID);
    }
}
