package com.proyecto.itemcatalogo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.proyecto.producto.entity.Producto;
import com.proyecto.vendedor.entity.Vendedor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor  // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos


@Entity
@Table(name = "item_catalogo")
public class ItemCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name ="precio")
    private BigDecimal precio;
    @Column(name ="stock")
    private Integer stock;
    @Column(name ="disponible")
    private Boolean disponible;
    @Column(name ="fecha_agregado")
    private LocalDateTime fechaAgregado;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    private Vendedor vendedor;
    
}
