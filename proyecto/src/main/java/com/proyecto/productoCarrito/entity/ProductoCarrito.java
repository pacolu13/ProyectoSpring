package com.proyecto.productoCarrito.entity;

import com.proyecto.carrito.entity.Carrito;
import com.proyecto.productoVenta.entity.ProductoVenta;

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
public class ProductoCarrito {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    
    @OneToOne
    private ProductoVenta productoVenta;

    @ManyToOne
    @JoinColumn(name="carrito_id")
    private Carrito carrito;

    @Override
    public String toString() {
        return "ProductoCarrito [id=" + id + ", cantidad=" + cantidad + "]";
    }


}
