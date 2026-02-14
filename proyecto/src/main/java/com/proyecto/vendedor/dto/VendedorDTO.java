package com.proyecto.vendedor.dto;

import java.time.LocalDateTime;

public class VendedorDTO {
    private Long id;
    private String nombre;
    private String email;
    private String cuit;
    private Boolean activo;
    private LocalDateTime fechaRegistro;

    public VendedorDTO(Long id, String nombre, String email, String cuit, Boolean activo,
            LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cuit = cuit;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }

    public VendedorDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
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
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        VendedorDTO other = (VendedorDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (fechaRegistro == null) {
            if (other.fechaRegistro != null)
                return false;
        } else if (!fechaRegistro.equals(other.fechaRegistro))
            return false;
        return true;
    }

    

}
