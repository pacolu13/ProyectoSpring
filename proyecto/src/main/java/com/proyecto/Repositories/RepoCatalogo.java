package com.proyecto.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.Entities.Catalogo;

public interface RepoCatalogo extends JpaRepository<Catalogo, Long> {

    @Query("""
            SELECT c FROM Catalogo c
            WHERE (:nombre IS NULL OR LOWER(c.producto.nombre) = LOWER(:nombre))
              AND (:categoria IS NULL OR LOWER(c.producto.categoria) = LOWER(:categoria))
              AND (:precioMin IS NULL OR c.precio >= :precioMin)
              AND (:precioMax IS NULL OR c.precio <= :precioMax)
            """)

    List<Catalogo> buscarPorFiltros(
            @Param("nombre") String nombre,
            @Param("categoria") String categoria,
            @Param("precioMin") Double precioMin,
            @Param("precioMax") Double precioMax);

}
