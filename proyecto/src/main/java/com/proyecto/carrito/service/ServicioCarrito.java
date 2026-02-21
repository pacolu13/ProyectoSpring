package com.proyecto.carrito.service;

import org.springframework.stereotype.Service;

import com.proyecto.carrito.dto.CarritoDTO;
import com.proyecto.carrito.entity.Carrito;
import com.proyecto.carrito.mapper.CarritoMapper;
import com.proyecto.carrito.repository.RepoCarrito;
import com.proyecto.cliente.entity.Cliente;
import com.proyecto.cliente.repository.RepoCliente;
import com.proyecto.excepciones.ResourceNotFoundException;
import com.proyecto.productoVenta.entity.ProductoVenta;
import com.proyecto.productoVenta.repository.RepoProductoVenta;

@Service
@SuppressWarnings("null")
public class ServicioCarrito {

    private final RepoCarrito repoCarrito;
    private final RepoCliente repoCliente;
    private final RepoProductoVenta repoProductoVenta;
    private final CarritoMapper carritoMapper;

    public ServicioCarrito(RepoCarrito repoCarrito, CarritoMapper carritoMapper, RepoCliente repoCliente,
            RepoProductoVenta repoProductoVenta) {
        this.repoCarrito = repoCarrito;
        this.carritoMapper = carritoMapper;
        this.repoCliente = repoCliente;
        this.repoProductoVenta = repoProductoVenta;
    }

    public CarritoDTO obtenerCarrito(Long clienteId) {
        Carrito carrito = repoCarrito.findByClienteId(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado"));
        return carritoMapper.toDTO(carrito);
    }

    public CarritoDTO añadirProducto(Long clienteId, Long productoVentaId, Integer cantidad) {
        Cliente cliente = repoCliente.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        ProductoVenta productoVenta = repoProductoVenta.findById(productoVentaId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto Venta no encontrado"));

        Carrito carrito = cliente.getCarrito();

        if (!productoVenta.hayCantidad(cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente");
        }

        carrito.agregarProducto(productoVenta,cantidad);

        return carritoMapper.toDTO(carrito);
    }

}
