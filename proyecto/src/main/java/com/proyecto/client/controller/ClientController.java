package com.proyecto.client.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.client.dto.*;
import com.proyecto.client.service.ClientService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> findClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id) {
        ClientDTO cliente = clientService.findClientById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<List<ClientDTO>> addClientList(@RequestBody List<ClientCreateDTO> clientList) {
        List<ClientDTO> resultado = clientService.addClientList(clientList);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable Long id,
            @RequestBody ClientUpdateDTO dto) {

        return ResponseEntity.ok(clientService.updateClient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
