package com.proyecto.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.client.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
