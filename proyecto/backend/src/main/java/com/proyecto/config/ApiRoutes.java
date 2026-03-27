package com.proyecto.config;

public class ApiRoutes {
    private ApiRoutes() {
    }

    public static final String API = "/api/v1";
    public static final String AUTH = API + "/auth";
    public static final String USERS = API + "/users";
    public static final String SELLERS = API + "/sellers";
    public static final String PRODUCTS = API + "/products";
    public static final String PRODUCTS_LISTING = API + "/product-listings";
    public static final String CARTS = API + "/carts";
    public static final String ORDERS = API + "/orders";
    public static final String CATEGORIES = API + "categories";

    public static final String ALL_STRING = "/**";

    // Extender cada una por Id o los paremotros que se continuan en sus controllers

}
