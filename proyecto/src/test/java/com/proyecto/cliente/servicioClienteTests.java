package com.proyecto.cliente;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.entity.Cliente;
import com.proyecto.cliente.mapper.ClienteMapper;
import com.proyecto.cliente.repository.RepoCliente;
import com.proyecto.cliente.service.ServicioCliente;
import com.proyecto.excepciones.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class servicioClienteTests {

    @MockitoBean
    private RepoCliente repoCliente;

    @MockitoBean
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ServicioCliente servicioCliente;

    // Metodo con error
    
    @Test
    public void obtenerClientePorIdVacioTest(){
        when(repoCliente.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,() -> servicioCliente.obtenerClientePorId(1L));        
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

/* 
    @Test
    public void obtenerClientePorIdTest() {
        Cliente cliente = ClienteProvider.clienteMock();
        ClienteDTO esperado = ClienteProvider.clienteMockDTO();

        when(repoCliente.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(esperado);

        ClienteDTO resultado = servicioCliente.obtenerClientePorId(1L);

        assertEquals(esperado, resultado);
    }
        */
}