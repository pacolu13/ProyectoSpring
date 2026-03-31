package com.proyecto.auth.dto;

import java.util.List;

public record RegisterDTO(
    String username,
    String password,
    String email,
    List<String> roles
) {

}
