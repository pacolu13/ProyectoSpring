package com.proyecto.productoVenta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.productoVenta.dto.ProductoVentaCreateDTO;
import com.proyecto.productoVenta.dto.ProductoVentaDTO;
import com.proyecto.productoVenta.repository.RepoProductoVenta;

@Service
public class ServicioProductoVenta {

    private final RepoProductoVenta repoProductoVenta;

    public ServicioProductoVenta(RepoProductoVenta repoProductoVenta){
        this.repoProductoVenta = repoProductoVenta;
    }

    public List<ProductoVentaDTO> obtenerProductosVentas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerProductosVentas'");
    }

    public ProductoVentaDTO obtenerProductoVentaPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerProductoVentaPorId'");
    }

    public List<ProductoVentaDTO> añadirListaProductoVenta(List<ProductoVentaCreateDTO> listaProductosVenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'añadirListaProductoVenta'");
    }


}
