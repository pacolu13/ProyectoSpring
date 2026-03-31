package com.proyecto.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initRoles();
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
}
