package com.proyecto.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.Repositories.RepoVendedor;

@Service
public class ServicioVendedor {

    private final RepoVendedor repoVendedor;

    public ServicioVendedor(RepoVendedor repoVendedor) {
        this.repoVendedor = repoVendedor;
    }
}
