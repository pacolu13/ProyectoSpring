package com.proyecto.DTOs;

import java.util.List;


public record RegisterDTO(
    String username,
    String password,
    String email,
    List<String> roles
) {

}
