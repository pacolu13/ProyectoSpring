package com.proyecto.productoVenta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.excepciones.ResourceNotFoundException;
import com.proyecto.productoVenta.dto.ProductoVentaCreateDTO;
import com.proyecto.productoVenta.dto.ProductoVentaDTO;
import com.proyecto.productoVenta.entity.ProductoVenta;
import com.proyecto.productoVenta.mapper.ProductoVentaMapper;
import com.proyecto.productoVenta.repository.RepoProductoVenta;

@Service
@SuppressWarnings("null")
public class ServicioProductoVenta {

    private final RepoProductoVenta repoProductoVenta;
    private final ProductoVentaMapper productoVentaMapper;

    public ServicioProductoVenta(RepoProductoVenta repoProductoVenta, ProductoVentaMapper productoVentaMapper) {
        this.repoProductoVenta = repoProductoVenta;
        this.productoVentaMapper = productoVentaMapper;
    }

    public List<ProductoVentaDTO> obtenerProductosVentas() {
        List<ProductoVenta> productosVentaList = repoProductoVenta.findAll();
        return productoVentaMapper.toDTOlList(productosVentaList);
    }

    public ProductoVentaDTO obtenerProductoVentaPorId(Long id) {
        ProductoVenta prod = repoProductoVenta.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto Venta no encontrado"));
        return productoVentaMapper.toDTO(prod);
    }

    public List<ProductoVentaDTO> añadirListaProductoVenta(List<ProductoVentaCreateDTO> listaProductosVenta) {
        List<ProductoVenta> productosVentaGuardar = productoVentaMapper.toEntityList(listaProductosVenta);
        List<ProductoVenta> productosVentaGuardados = repoProductoVenta.saveAll(productosVentaGuardar);
        return productoVentaMapper.toDTOlList(productosVentaGuardados);
    }

}
