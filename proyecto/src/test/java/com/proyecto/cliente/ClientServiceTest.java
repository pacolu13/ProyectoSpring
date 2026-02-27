package com.proyecto.cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.proyecto.client.dto.ClientDTO;
import com.proyecto.client.entity.Client;
import com.proyecto.client.mapper.ClientMapper;
import com.proyecto.client.repository.ClientRepository;
import com.proyecto.client.service.ClientService;
import com.proyecto.exceptions.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @MockitoBean
    private ClientRepository repoCliente;

    @MockitoBean
    private ClientMapper clienteMapper;

    @InjectMocks
    private ClientService servicioCliente;

    // Metodo con error

    @Test
    public void obtenerClientePorIdVacioTest() {
        when(repoCliente.findById(UUID.randomUUID())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> servicioCliente.findClientById(UUID.randomUUID()));
    }

    @Test
    public void obtenerClientesTest() {
        List<Client> clientesMock = ClientProvider.listaClientesMock();
        List<ClientDTO> esperado = ClientProvider.listaClientesMockDTOs();

        when(repoCliente.findAll()).thenReturn(clientesMock);
        when(clienteMapper.toDTOList(clientesMock)).thenReturn(esperado);

        List<ClientDTO> resultado = servicioCliente.findAllClients();

        assertEquals(esperado, resultado);
    }

    /*
     * @Test
     * public void obtenerClientePorIdTest() {
     * Cliente cliente = ClienteProvider.clienteMock();
     * ClienteDTO esperado = ClienteProvider.clienteMockDTO();
     * 
     * when(repoCliente.findById(anyLong())).thenReturn(Optional.of(cliente));
     * when(clienteMapper.toDTO(cliente)).thenReturn(esperado);
     * 
     * ClienteDTO resultado = servicioCliente.obtenerClientePorId(1L);
     * 
     * assertEquals(esperado, resultado);
     * }
     */
}