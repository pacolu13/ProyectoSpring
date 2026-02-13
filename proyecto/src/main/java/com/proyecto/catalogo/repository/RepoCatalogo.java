package com.proyecto.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.proyecto.catalogo.entity.Catalogo;

public interface RepoCatalogo extends JpaRepository<Catalogo, Long>, JpaSpecificationExecutor<Catalogo> {

}
