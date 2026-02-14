package com.proyecto.compra.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor  // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos

@Entity
@Table(name = "compra") 
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "estado")
    private EstadoCompra estado;

}