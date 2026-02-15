package com.proyecto.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.entity.Cliente;
import com.proyecto.cliente.mapper.ClienteMapper;
import com.proyecto.cliente.repository.RepoCliente;
import com.proyecto.cliente.service.ServicioCliente;

@ExtendWith(MockitoExtension.class)
public class servicioClienteTests {

    @Mock
    private RepoCliente repoCliente;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ServicioCliente servicioCliente;

    @Test
    public void obtenerClientesNullTest() {
        when(repoCliente.findAll()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> servicioCliente.obtenerClientes());
    }

    @Test
    public void obtenerClientesVacioTest() {
        when(repoCliente.findAll()).thenReturn(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> servicioCliente.obtenerClientes());
    }

    @Test
    public void obtenerClientesTest() {
        List<Cliente> clientesMock = ClienteProvider.listaClientesMock();
        List<ClienteDTO> esperado = ClienteProvider.listaClientesMockDTOs();

        when(repoCliente.findAll()).thenReturn(clientesMock);
        when(clienteMapper.toDTOList(clientesMock)).thenReturn(esperado);

        List<ClienteDTO> resultado = servicioCliente.obtenerClientes();

        assertEquals(esperado, resultado);
    }

    @Test
    public void obtenerClientePorIdTest() {
        Cliente cliente = ClienteProvider.clienteMock();
        ClienteDTO esperado = ClienteProvider.clienteMockDTO();

        when(repoCliente.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(esperado);

        ClienteDTO resultado = servicioCliente.obtenerClientePorId(1L);

        assertEquals(esperado, resultado);
    }
}