package com.proyecto.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.user.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
