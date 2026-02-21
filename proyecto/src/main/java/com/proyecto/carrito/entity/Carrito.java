package com.proyecto.carrito.entity;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.cliente.entity.Cliente;
import com.proyecto.productoCarrito.entity.ProductoCarrito;

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
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_Id")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "carrito")
    private List<ProductoCarrito> productosList = new ArrayList<>();
}