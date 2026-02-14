package com.proyecto.cliente;

import java.util.ArrayList;
import java.util.List;

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

}
