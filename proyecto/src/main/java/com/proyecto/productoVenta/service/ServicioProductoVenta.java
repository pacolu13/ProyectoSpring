package com.proyecto.productoVenta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.excepciones.ResourceNotFoundException;
import com.proyecto.producto.entity.Producto;
import com.proyecto.producto.repository.RepoProducto;
import com.proyecto.productoVenta.dto.ProductoVentaCreateDTO;
import com.proyecto.productoVenta.dto.ProductoVentaDTO;
import com.proyecto.productoVenta.entity.ProductoVenta;
import com.proyecto.productoVenta.mapper.ProductoVentaMapper;
import com.proyecto.productoVenta.repository.RepoProductoVenta;
import com.proyecto.vendedor.entity.Vendedor;
import com.proyecto.vendedor.repository.RepoVendedor;

@Service
@SuppressWarnings("null")
public class ServicioProductoVenta {

    private final RepoProducto repoProducto;
    private final RepoVendedor repoVendedor;
    private final RepoProductoVenta repoProductoVenta;
    private final ProductoVentaMapper productoVentaMapper;

    public ServicioProductoVenta(RepoProductoVenta repoProductoVenta, ProductoVentaMapper productoVentaMapper,
            RepoProducto repoProducto, RepoVendedor repoVendedor) {
        this.repoProductoVenta = repoProductoVenta;
        this.productoVentaMapper = productoVentaMapper;
        this.repoProducto = repoProducto;
        this.repoVendedor = repoVendedor;
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
        List<ProductoVenta> productosVentaGuardar = listaProductosVenta.stream()
                .map(dto -> {
                    Producto producto = repoProducto.findById(dto.productoId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Producto no encontrado: " + dto.productoId()));
                    Vendedor vendedor = repoVendedor.findById(dto.vendedorId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Vendedor no encontrado: " + dto.vendedorId()));

                    ProductoVenta entity = productoVentaMapper.toEntity(dto);
                    entity.setProducto(producto);
                    entity.setVendedor(vendedor);
                    return entity;
                })
                .toList();

        List<ProductoVenta> guardados = repoProductoVenta.saveAll(productosVentaGuardar);
        return productoVentaMapper.toDTOlList(guardados);
    }
}
