package com.proyecto.cliente.dto;

public class ClienteUpdateDTO extends ClienteAbstractDTO {
    
    public ClienteUpdateDTO(){}

    public ClienteUpdateDTO(String nombre, String apellido, String email, String telefono) {
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setTelefono(telefono);
    }
    
    @Override
    public String toString() {
        return "ClienteUpdateDTO [nombre=" + getNombre()
            + ", apellido=" + getApellido() 
            + ", email=" + getEmail() 
            + ", telefono=" + getTelefono() + "]";
    }

}
