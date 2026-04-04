package com.proyecto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.config.ExceptionFactory;
import com.proyecto.models.Category;
import com.proyecto.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
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
                .orElseThrow(() -> ExceptionFactory.createCategoryNotFoundException());
        return response;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
