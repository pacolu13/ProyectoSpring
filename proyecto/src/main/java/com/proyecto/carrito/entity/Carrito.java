package com.proyecto.carrito.entity;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.cliente.entity.Cliente;
import com.proyecto.productoCarrito.entity.ProductoCarrito;
import com.proyecto.productoVenta.entity.ProductoVenta;

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

    //Sin el formato cascade no se generan las entidades anidadas
    
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoCarrito> productosList = new ArrayList<>();

    public void agregarProducto(ProductoVenta productoVenta, Integer cantidad) {
        ProductoCarrito itemExistente = getProductosList()
                .stream()
                .filter(pc -> pc.getProductoVenta().getId().equals(productoVenta.getId()))
                .findFirst()
                .orElse(null);

        if (itemExistente != null) {
            itemExistente.setCantidad(itemExistente.getCantidad() + cantidad);
        } else {
            ProductoCarrito nuevo = new ProductoCarrito(); 
            nuevo.setCarrito(this);
            nuevo.setProductoVenta(productoVenta);
            nuevo.setCantidad(cantidad);
            getProductosList().add(nuevo);
        }
    }
}