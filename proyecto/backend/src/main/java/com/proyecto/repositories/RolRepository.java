package com.proyecto.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.models.Rol;
import com.proyecto.models.RolEnum;

public interface RolRepository extends JpaRepository<Rol, UUID> {

    Optional<Rol> findByName(RolEnum rolEnum);

    List<Rol> findAllByNameIn(List<String> roles);


}
