package com.proyecto.producto.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.proyecto.productoVenta.entity.ProductoVenta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor  // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos

@Entity
@Table(name = "productos") 
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    private String descripcion;
    private String marca;
    private String categoria;
    private String imagen;
    private Boolean activo = true;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoVenta> ventasProductos;
}
