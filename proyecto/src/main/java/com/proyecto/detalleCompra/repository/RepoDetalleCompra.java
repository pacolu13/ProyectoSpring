package com.proyecto.detalleCompra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.detalleCompra.entity.DetalleCompra;

public interface RepoDetalleCompra extends JpaRepository<DetalleCompra, Long> {

}
