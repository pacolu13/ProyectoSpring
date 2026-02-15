package com.proyecto.cliente.dto;

public class ClienteCreateDTO extends ClienteAbstractDTO {

    public ClienteCreateDTO() {
    }

    public ClienteCreateDTO(String nombre, String apellido, String email, String telefono, String contrasenia) {
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
