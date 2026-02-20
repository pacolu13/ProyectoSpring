package com.proyecto.usuario.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // ¿ En que se diferencia?
    private UUID id;

    @ManyToMany(mappedBy = "rolesLista")
    private List<User> usuarios;
}
