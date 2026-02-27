package com.proyecto.client.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.cart.entity.Cart;
import com.proyecto.order.entity.Order;
import com.proyecto.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

    private BigDecimal balance;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL) // Crea automaticamente el Carrito
    private Cart cart;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> ordersList = new ArrayList<>();
}
