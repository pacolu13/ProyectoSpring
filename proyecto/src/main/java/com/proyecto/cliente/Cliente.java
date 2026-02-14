package com.proyecto.cliente;

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
@Table(name = "cliente") 
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "contraseña")
    private String contrasenia;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "saldo")
    private BigDecimal saldo;
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    public void recargarSaldo(BigDecimal monto){
        BigDecimal nuevoSaldo = getSaldo().add(monto);
        setSaldo(nuevoSaldo);
    }

    public void descontarSaldo(BigDecimal monto){
        BigDecimal nuevoSaldo = getSaldo().subtract(monto);
        setSaldo(nuevoSaldo);
    }

    public boolean verificarSaldoSuficiente(BigDecimal monto){
        return getSaldo().compareTo(monto) >= 0;
    }
}
