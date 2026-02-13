package com.proyecto.vendedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.vendedor.entity.Vendedor;

public interface RepoVendedor extends JpaRepository<Vendedor, Long> {

}
