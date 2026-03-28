package com.proyecto.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.proyecto.categories.entity.Category;
import com.proyecto.categories.repository.CategoryRepository;
import com.proyecto.rol.entity.Rol;
import com.proyecto.rol.entity.RolEnum;
import com.proyecto.rol.repository.RolRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements ApplicationRunner {

    private final RolRepository rolRepository;
    private final CategoryRepository categoryRepository;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initRoles();
        initCategories();
    }

    private void initRoles() {
        for (RolEnum rolEnum : RolEnum.values()) {
            if (rolRepository.findByName(rolEnum).isEmpty()) {
                Rol rol = new Rol();
                rol.setName(rolEnum);
                rolRepository.save(rol);
                log.info("Rol creado: {}", rolEnum.name());
            }
        }
    }

    @SuppressWarnings("null")
    private void initCategories() {
        String[] categories = restTemplate.getForObject(
            "https://fakestoreapi.com/products/categories",
            String[].class
        );
        
        for (String category : categories) {
            if (categoryRepository.findByName(category).isEmpty()) {
                Category cat = new Category();
                cat.setName(category);
                categoryRepository.save(cat);
                log.info("Categoría creada: {}", category);
            }
        }
    }
}
