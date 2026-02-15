package com.proyecto.cliente.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ClienteDTO extends ClienteAbstractDTO {

    private BigDecimal saldo;
    private Boolean activo;
    private LocalDateTime fechaRegistro;

    public ClienteDTO() {

    }

    public ClienteDTO(Long id, String nombre, String apellido, String email, String telefono, String contrasenia, BigDecimal saldo, Boolean activo, LocalDateTime fechaRegistro) {
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setTelefono(telefono);
        setContrasenia(contrasenia);
        this.saldo = saldo;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;

    }
}
