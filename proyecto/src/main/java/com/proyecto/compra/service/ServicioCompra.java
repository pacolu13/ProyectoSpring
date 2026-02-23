package com.proyecto.compra.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.cliente.entity.Cliente;
import com.proyecto.cliente.repository.RepoCliente;
import com.proyecto.compra.dto.CompraDTO;
import com.proyecto.compra.entity.Compra;
import com.proyecto.compra.mapper.CompraMapper;
import com.proyecto.compra.repository.RepoCompra;
import com.proyecto.detalleCompra.entity.DetalleCompra;
import com.proyecto.excepciones.ResourceNotFoundException;
import com.proyecto.productoCarrito.entity.ProductoCarrito;

@Service
public class ServicioCompra {

    private final RepoCompra repoCompra;
    private final RepoCliente repoCliente;
    private final CompraMapper compraMapper;

    public ServicioCompra(RepoCompra repoCompra, RepoCliente repoCliente, CompraMapper compraMapper) {
        this.repoCompra = repoCompra;
        this.repoCliente = repoCliente;
        this.compraMapper = compraMapper;
    }

    public CompraDTO confirmarCompra(Long clienteId) {
        Cliente cliente = repoCliente.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        BigDecimal total = BigDecimal.ZERO;
        Compra compra = new Compra();
        List<ProductoCarrito> listaProductos = cliente.getCarrito().getProductosList();

        for (ProductoCarrito producto : listaProductos) {
            BigDecimal precioUnitario = producto.getProductoVenta().getPrecio();
            total = total.add(precioUnitario.multiply(BigDecimal.valueOf(producto.getCantidad())));
        }

        if (total.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalStateException("El carrito está vacío");
        }

        if (total.compareTo(cliente.getSaldo()) > 0) {
            throw new IllegalStateException("Saldo insuficiente para realizar la compra");
        }

        cliente.setSaldo(cliente.getSaldo().subtract(total));
        compra.setCliente(cliente);
        compra.setTotal(total);

        for (ProductoCarrito producto : listaProductos) {
            DetalleCompra detalle = new DetalleCompra();
            detalle.setCompra(compra);
            detalle.setProductoVenta(producto.getProductoVenta());
            detalle.setCantidad(producto.getCantidad());
            compra.getDetalles().add(detalle);
        }

        return compraMapper.toDTO(repoCompra.save(compra));
    }

    /*
        Falta verificar stock de cada producto y actualizarlo despues de confirmar la compra
        ademas, se deberia limpiar el carrito del cliente despues de la compra.
    */
}
