package com.proyecto.cliente.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ClienteDTO  extends ClienteAbstractDTO {
    private Long id;
    private BigDecimal saldo;
    private Boolean activo;
    private LocalDateTime fechaRegistro;

    public ClienteDTO() {

    }

    public ClienteDTO(Long id, String nombre, String apellido, String email, String telefono, BigDecimal saldo,
            Boolean activo, LocalDateTime fechaRegistr, String contrasenia) {
        this.id = id;
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setTelefono(telefono);
        setContrasenia(contrasenia);
        this.saldo = saldo;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((getNombre() == null) ? 0 : getNombre().hashCode());
        result = prime * result + ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClienteDTO other = (ClienteDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (getNombre() == null) {
            if (other.getNombre() != null)
                return false;
        } else if (!getNombre().equals(other.getNombre()))
            return false;
        if (fechaRegistro == null) {
            if (other.fechaRegistro != null)
                return false;
        } else if (!fechaRegistro.equals(other.fechaRegistro))
            return false;
        return true;
    }

}
