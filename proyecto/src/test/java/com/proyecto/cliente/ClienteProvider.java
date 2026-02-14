package com.proyecto.cliente;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.entity.Cliente;

public class ClienteProvider {

    public static Cliente clienteMock() {
        Cliente cliente = new Cliente();
        return cliente;
    }

    public static List<Cliente> listaClientesMock() {
        List<Cliente> clientes = new ArrayList<>();
        return clientes;
    }

    public static List<Cliente> clienteFiltroMock() {
        List<Cliente> clientes = new ArrayList<>();
        return clientes;
    }

    public static ClienteDTO clienteMockDTO() {
        ClienteDTO cliente = new ClienteDTO();
        return cliente;
    }

    public static List<ClienteDTO> listaClientesMockDTOs() {
        List<ClienteDTO> clientes = new ArrayList<>();
        return clientes;
    }

    public static List<ClienteDTO> clienteFiltroMockDTOs() {
        List<ClienteDTO> clienteDTOs = new ArrayList<>();
        return clienteDTOs;
    }

}
