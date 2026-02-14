package com.proyecto.producto.dto;

import java.time.LocalDateTime;

public class ProductoDTO extends ProductoAbstractDTO {
    private Boolean activo;
    private LocalDateTime fechaCreacion;

    public ProductoDTO() {
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime gefechaCreacion() {
        return fechaCreacion;
    }

    public void setfechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    
}
