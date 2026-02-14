package com.proyecto.movimientosaldo.entity;

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

@Data // Genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos

@Entity
@Table(name = "movimiento_saldo")
public class MovimientoSaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "monto")
    private BigDecimal monto;
    @Column(name = "tipo")
    private TipoMovimiento tipo;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "saldo_anterior")
    private BigDecimal saldoAnterior;
    @Column(name = "saldo_nuevo")
    private BigDecimal saldoNuevo;
}
