package com.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Entities.Vendedor;

public interface RepoVendedor extends JpaRepository<Vendedor, Long> {

}
