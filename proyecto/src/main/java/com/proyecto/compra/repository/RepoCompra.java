package com.proyecto.compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.compra.entity.Compra;

public interface RepoCompra extends JpaRepository<Compra, Long> {

}
