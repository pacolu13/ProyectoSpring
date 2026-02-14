package com.proyecto.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.proyecto.cliente.entity.Cliente;
import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.repository.RepoCliente;
import com.proyecto.cliente.service.ServicioCliente;

public class servicioClienteTests {

    @Mock
    private RepoCliente repoCliente;

    @InjectMocks
    private ServicioCliente servicioCliente;

    @Test
    public void obtenerClientesNullTest() {
        when(repoCliente.findAll()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> servicioCliente.obtenerClientes());
    }

    @Test
    public void obtenerClientesVacioTest(){
        when(repoCliente.findAll()).thenReturn(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> servicioCliente.obtenerClientes());
    }


    @Test
    public void obtenerClientesTest(){
        List<Cliente> clientesMock = ClienteProvider.listaClientesMock();

        when(repoCliente.findAll()).thenReturn(clientesMock);
        List<ClienteDTO> resultado = servicioCliente.obtenerClientes();

        //Realizar la conversion a DTO para comparar

        assertEquals(clientesMock, resultado);
    }

    @Test
    public void obtenerClientePorIdTest(){
        Cliente cliente = ClienteProvider.clienteMock();

        //Realizar el Mapper

        when(repoCliente.findById(anyLong())).thenReturn(Optional.of(cliente));

        ClienteDTO resultado = servicioCliente.obtenerClientePorId(1L);

        //Realizar la comparacion con el cliente casteado a DTO

        assertEquals(cliente.getId(), resultado.getId());
    }

}