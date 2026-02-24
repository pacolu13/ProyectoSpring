package com.proyecto.productoVenta.entity;

import java.math.BigDecimal;

import com.proyecto.detalleCompra.entity.DetalleCompra;
import com.proyecto.producto.entity.Producto;
import com.proyecto.vendedor.entity.Vendedor;

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
public class ProductoVenta {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private BigDecimal precio;

    @OneToOne
    @JoinColumn(name = "detalle_compra_id")
    private DetalleCompra detalleCompra;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    public boolean hayCantidad(Integer cantidad) {
        return getCantidad() >= cantidad;
    }
    
}
