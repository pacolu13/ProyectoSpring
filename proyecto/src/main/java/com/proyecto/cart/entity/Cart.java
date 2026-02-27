package com.proyecto.cart.entity;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.cartProduct.entity.CartProduct;
import com.proyecto.client.entity.Client;
import com.proyecto.productListing.entity.ProductListing;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("CARRITO")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Sin el formato cascade no se generan las entidades anidadas

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> productsList = new ArrayList<>();

    public void addProduct(ProductListing productListing, Integer quantity) {
        CartProduct ExistItem = getProductsList()
                .stream()
                .filter(pc -> pc.getProductListing().getId().equals(productListing.getId()))
                .findFirst()
                .orElse(null);

        if (ExistItem != null) {
            ExistItem.setQuantity(ExistItem.getQuantity() + quantity);
        } else {
            CartProduct newCartProduct = new CartProduct();
            newCartProduct.setCart(this);
            newCartProduct.setProductListing(productListing);
            newCartProduct.setQuantity(quantity);
            getProductsList().add(newCartProduct);
        }
    }
}