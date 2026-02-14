package com.proyecto.cliente.dto;

public class ClienteCreateDTO extends ClienteAbstractDTO {

    public ClienteCreateDTO() {
    }

    public ClienteCreateDTO(Long id, String nombre, String apellido, String email, String telefono, String contrasenia) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setTelefono(telefono);
        setContrasenia(contrasenia);
    }

    @Override
    public String toString() {
        return "ClienteCreateDTO [nombre=" + getNombre()
                + ", apellido=" + getApellido()
                + ", email=" + getEmail()
                + ", telefono=" + getTelefono() + "]";
    }

}
