package com.proyecto.producto.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.proyecto.itemcatalogo.entity.ItemCatalogo;

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
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "marca")
    private String marca;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}
