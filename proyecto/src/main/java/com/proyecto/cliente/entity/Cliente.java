package com.proyecto.cliente.entity;

import java.math.BigDecimal;

import com.proyecto.usuario.entity.User;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor  // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends User {
    
    private BigDecimal saldo;
    
     
}
