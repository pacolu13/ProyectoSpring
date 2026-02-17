package com.proyecto.cliente.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.cliente.dto.ClienteCreateDTO;
import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.dto.ClienteUpdateDTO;
import com.proyecto.cliente.entity.Cliente;
import com.proyecto.cliente.mapper.ClienteMapper;
import com.proyecto.cliente.repository.RepoCliente;
import com.proyecto.excepciones.ResourceNotFoundException;

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
        return clienteMapper.toDTOList(clientes);
    }

    public ClienteDTO obtenerClientePorId(long id) {
        Cliente cliente = repoCliente.findById(id)
                .orElseThrow(
                () -> new ResourceNotFoundException("Cliente",id));
        return clienteMapper.toDTO(cliente);
    }

    public ClienteDTO añadirCliente(ClienteCreateDTO cliente) {
        Cliente nuevo = clienteMapper.toEntity(cliente);
        Cliente resultado = repoCliente.save(nuevo);
        return clienteMapper.toDTO(resultado);
    }

    public ClienteDTO  actualizarCliente(Long id, ClienteUpdateDTO dto) {
        Cliente cliente = repoCliente.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));

        Cliente actualizado = clienteMapper.updateProductoFromDto(dto, cliente);
        return clienteMapper.toDTO(repoCliente.save(actualizado));
    }
    
    public void eliminarCliente(Long id) {
        if(!repoCliente.existsById(id))
            throw new ResourceNotFoundException("Cliente",id);
        repoCliente.deleteById(id);
    }
}
