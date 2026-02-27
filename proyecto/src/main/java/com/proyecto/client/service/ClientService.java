package com.proyecto.client.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.cart.entity.Cart;
import com.proyecto.client.dto.ClientCreateDTO;
import com.proyecto.client.dto.ClientDTO;
import com.proyecto.client.dto.ClientUpdateDTO;
import com.proyecto.client.entity.Client;
import com.proyecto.client.mapper.ClientMapper;
import com.proyecto.client.repository.ClientRepository;
import com.proyecto.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<ClientDTO> findAllClients() {
        List<Client> clientes = clientRepository.findAll();
        return clientMapper.toDTOList(clientes);
    }

    public ClientDTO findClientById(long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Cliente", id));
        return clientMapper.toDTO(client);
    }

    public List<ClientDTO> addClientList(List<ClientCreateDTO> clientList) {
        List<Client> newClients = clientMapper.toEntityList(clientList);
        for (Client cli : newClients) {

            cli.setBalance(BigDecimal.valueOf(500));
            Cart carrito = new Cart();
            carrito.setClient(cli);
            cli.setCart(carrito);
        }
        List<Client> resultado = clientRepository.saveAll(newClients);
        return clientMapper.toDTOList(resultado);
    }

    public ClientDTO updateClient(Long id, ClientUpdateDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));

        Client clientUpdated = clientMapper.updateClientFromDto(dto, client);
        return clientMapper.toDTO(clientRepository.save(clientUpdated));
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id))
            throw new ResourceNotFoundException("Cliente", id);
        clientRepository.deleteById(id);
    }
}
