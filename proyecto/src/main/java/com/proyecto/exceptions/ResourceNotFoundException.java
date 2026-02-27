package com.proyecto.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    // Constructor conveniente para entidades
    public ResourceNotFoundException(String entity, UUID id) {
        super(String.format("%s con ID %d no encontrado", entity, id));
    }
}
