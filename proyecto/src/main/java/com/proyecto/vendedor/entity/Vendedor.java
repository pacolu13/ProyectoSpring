package com.proyecto.vendedor.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.compra.entity.Compra;

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
@Table(name = "vendedores") 
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "email")
    private String email;
    @Column(name = "contraseña")
    private String contrasenia;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // Estudiar relaciones

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> comprasList = new ArrayList<>();

    /* 
    public ItemCatalogo actualizarPrecio(Long itemId, BigDecimal nuevoPrecio){
        if(itemId == null)
            throw new IllegalArgumentException("La ID no puede ser nulo");

        if(nuevoPrecio == null || nuevoPrecio.equals(0))
            throw new IllegalArgumentException("El nuevo precio no puede estar vacio");

        for(ItemCatalogo item: itemsCatalogoList)
            if(item.getId().equals(itemId)){
                item.setPrecio(nuevoPrecio);
                return item;
            }
        throw new IllegalArgumentException("No se encontro el Item solicitado");
    } 
        */
}
