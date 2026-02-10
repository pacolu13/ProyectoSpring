package com.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Entities.DetalleCompra;

public interface RepoDetalleCompra extends JpaRepository<DetalleCompra, Long> {

}
