package com.proyecto.cliente;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.client.dto.ClientDTO;
import com.proyecto.client.entity.Client;

public class ClientProvider {

    public static Client clienteMock() {
        Client cliente = new Client();
        return cliente;
    }

    public static List<Client> listaClientesMock() {
        List<Client> clientes = new ArrayList<>();
        return clientes;
    }

    public static List<Client> clienteFiltroMock() {
        List<Client> clientes = new ArrayList<>();
        return clientes;
    }

    public static List<ClientDTO> listaClientesMockDTOs() {
        List<ClientDTO> clientes = new ArrayList<>();
        return clientes;
    }

    public static List<ClientDTO> clienteFiltroMockDTOs() {
        List<ClientDTO> clienteDTOs = new ArrayList<>();
        return clienteDTOs;
    }

}
