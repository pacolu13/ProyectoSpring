package com.proyecto.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.categories.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
