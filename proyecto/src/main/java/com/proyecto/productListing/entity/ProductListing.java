package com.proyecto.productListing.entity;

import java.math.BigDecimal;

import com.proyecto.orderDetails.entity.OrderDetails;
import com.proyecto.product.entity.Product;
import com.proyecto.seller.entity.Seller;

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
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class ProductListing {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    public boolean hayCantidad(Integer cantidad) {
        return getQuantity() >= cantidad;
    }
    
}
