package com.proyecto.itemcarrito.entity;

import com.proyecto.carrito.entity.Carrito;

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
@Table(name="item_carrito")
public class ItemCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "cantidad")
    private Integer cantidad;

}
