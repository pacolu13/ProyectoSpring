package com.proyecto.Entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.*;

@Data               // Genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor  // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
@Entity
@Table(name = "catalogos")
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne // Relación muchos a uno con Producto
    @JoinColumn(name = "id_producto")
    private Producto producto;
    @Column(name = "id_vendedor")
    private Long idVendedor;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "stock")
    private Integer stock;
}
