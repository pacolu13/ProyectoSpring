package com.proyecto.carrito.entity;

import java.util.List;

import com.proyecto.cliente.entity.Cliente;
import com.proyecto.productoCarrito.entity.ProductoCarrito;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@DiscriminatorValue("CARRITO")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Cliente cliente;
    
    @OneToMany(mappedBy = "carrito")
    private List<ProductoCarrito> productosList;

}
