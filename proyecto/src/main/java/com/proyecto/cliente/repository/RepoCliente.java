package com.proyecto.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.cliente.entity.Cliente;

public interface RepoCliente extends JpaRepository<Cliente, Long> {

}
