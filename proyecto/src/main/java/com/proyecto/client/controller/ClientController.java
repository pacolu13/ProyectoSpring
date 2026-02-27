package com.proyecto.client.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.client.dto.*;
import com.proyecto.client.service.ClientService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> findClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable UUID id) {
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
            @PathVariable UUID id,
            @RequestBody ClientUpdateDTO dto) {

        return ResponseEntity.ok(clientService.updateClient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
