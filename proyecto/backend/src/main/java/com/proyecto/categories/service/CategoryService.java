package com.proyecto.categories.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.categories.entity.Category;
import com.proyecto.categories.repository.CategoryRepository;
import com.proyecto.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        Category response = categoryRepository.save(category);
        return response;
    }

    public Category updateCategory(Long id, String categoryName) {
        Category response = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
        return response;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
