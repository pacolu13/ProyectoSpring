package com.proyecto.cliente.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.proyecto.cliente.dto.ClienteCreateDTO;
import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.entity.Cliente;

@Component
public class ClienteMapper {

    // Entity → DTO
    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null)
            return null;

        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setSaldo(cliente.getSaldo());
        dto.setActivo(cliente.isActivo());
        dto.setFechaRegistro(cliente.getFechaRegistro());

        return dto;
    }

    // DTO → Entity
    public Cliente toEntity(ClienteCreateDTO dto) {
        if (dto == null)
            return null;

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setSaldo(BigDecimal.ZERO);
        cliente.setActivo(true);
        cliente.setFechaRegistro(LocalDateTime.now());

        return cliente;
    }

    // Lista Entity → Lista DTO
    public List<ClienteDTO> toDTOList(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
