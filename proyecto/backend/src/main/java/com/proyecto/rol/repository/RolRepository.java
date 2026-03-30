package com.proyecto.rol.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.rol.entity.Rol;
import com.proyecto.rol.entity.RolEnum;

public interface RolRepository extends JpaRepository<Rol, UUID> {

    Optional<Rol> findByName(RolEnum rolEnum);

    List<Rol> findAllByNameIn(List<Rol> roles);

}
