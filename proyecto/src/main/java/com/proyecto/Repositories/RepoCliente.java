package com.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Entities.Cliente;

public interface RepoCliente extends JpaRepository<Cliente, Long> {

}
