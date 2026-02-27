package com.proyecto.cartProduct.entity;

import com.proyecto.cart.entity.Cart;
import com.proyecto.productListing.entity.ProductListing;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("PRODUCTOCARRITO")
public class CartProduct {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    
    @OneToOne
    private ProductListing productListing;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @Override
    public String toString() {
        return "ProductoCarrito [id=" + id + ", cantidad=" + quantity + "]";
    }


}
