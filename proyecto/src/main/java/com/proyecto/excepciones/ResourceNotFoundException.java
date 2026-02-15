package com.proyecto.excepciones;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    // Constructor conveniente para entidades
    public ResourceNotFoundException(String entity, Long id) {
        super(String.format("%s con ID %d no encontrado", entity, id));
    }
}
