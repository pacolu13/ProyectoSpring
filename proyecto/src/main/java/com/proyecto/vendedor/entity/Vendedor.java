package com.proyecto.vendedor.entity;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.productoVenta.entity.ProductoVenta;
import com.proyecto.usuario.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter             
@NoArgsConstructor  // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos

@Entity
@DiscriminatorValue("VENDEDOR")
public class Vendedor extends User {

    private String cuit;

    @OneToMany(mappedBy="vendedor", cascade= CascadeType.ALL, orphanRemoval= true)
    private List<ProductoVenta> ventas = new ArrayList<>();

}
