package com.proyecto.cliente.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.carrito.entity.Carrito;
import com.proyecto.compra.entity.Compra;
import com.proyecto.usuario.entity.User;

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
@DiscriminatorValue("CLIENTE")
public class Cliente extends User {

    private BigDecimal saldo;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL) // Crea automaticamente el Carrito
    private Carrito carrito;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras = new ArrayList<>();
}
