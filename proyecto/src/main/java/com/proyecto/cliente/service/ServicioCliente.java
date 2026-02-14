package com.proyecto.cliente.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.cliente.dto.ClienteCreateDTO;
import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.entity.Cliente;
import com.proyecto.cliente.mapper.ClienteMapper;
import com.proyecto.cliente.repository.RepoCliente;

@Service
public class ServicioCliente {

    private final RepoCliente repoCliente;
    private final ClienteMapper clienteMapper;

    public ServicioCliente(RepoCliente repoCliente, ClienteMapper clienteMapper) {
        this.repoCliente = repoCliente;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> obtenerClientes() {
        List<Cliente> clientes = repoCliente.findAll();
        if (clientes.isEmpty() || clientes == null) {
            throw new RuntimeException("Error al obtener clientes");
        }
        return clienteMapper.toDTOList(clientes);
    }

    public ClienteDTO obtenerClientePorId(long id) {
        Cliente cliente = repoCliente.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id:" + id));
        return clienteMapper.toDTO(cliente);
    }

    public ClienteDTO añadirCliente(ClienteCreateDTO cliente) {

        Cliente guardar = new Cliente();
        guardar.setNombre(cliente.getNombre());
        guardar.setApellido(cliente.getApellido());
        guardar.setEmail(cliente.getEmail());
        guardar.setTelefono(cliente.getTelefono());
        guardar.setSaldo(BigDecimal.ZERO);
        guardar.setFechaRegistro(LocalDateTime.now());
        Cliente clienteGuardado = repoCliente.save(guardar);

        ClienteDTO respuesta = new ClienteDTO();
        respuesta.setNombre(clienteGuardado.getNombre());
        respuesta.setApellido(clienteGuardado.getApellido());
        return respuesta;
    }

}
