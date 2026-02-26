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

import jakarta.transaction.Transactional;

@Service
@SuppressWarnings("null")
public class ServicioCompra {

    private final RepoCompra repoCompra;
    private final RepoCliente repoCliente;
    private final CompraMapper compraMapper;

    public ServicioCompra(RepoCompra repoCompra, RepoCliente repoCliente, CompraMapper compraMapper) {
        this.repoCompra = repoCompra;
        this.repoCliente = repoCliente;
        this.compraMapper = compraMapper;
    }

    @Transactional // O se ejecuta todo o no se ejecuta nada, rollback en caso de error
    public CompraDTO confirmarCompra(Long clienteId) {
        Cliente cliente = repoCliente.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        if (cliente.getCarrito() == null || cliente.getCarrito().getProductosList().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        BigDecimal total = BigDecimal.ZERO;
        List<ProductoCarrito> listaProductos = cliente.getCarrito().getProductosList();

        for (ProductoCarrito producto : listaProductos) {
            if (producto.getProductoVenta().getCantidad() < producto.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para el producto: "
                        + producto.getProductoVenta().getProducto().getNombre());
            }
            Integer cantComprada = producto.getCantidad();
            Integer stockActual = producto.getProductoVenta().getCantidad();
            BigDecimal precioUnitario = producto.getProductoVenta().getPrecio();

            producto.getProductoVenta().setCantidad(stockActual - cantComprada);
            total = total.add(precioUnitario.multiply(BigDecimal.valueOf(cantComprada)));
        }

        if (total.compareTo(cliente.getSaldo()) > 0) {
            throw new IllegalStateException("Saldo insuficiente para realizar la compra");
        }

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setTotal(total);

        for (ProductoCarrito producto : listaProductos) {
            DetalleCompra detalle = new DetalleCompra();
            detalle.setCompra(compra);
            detalle.setProductoVenta(producto.getProductoVenta());
            detalle.setPrecioUnitario(producto.getProductoVenta().getPrecio());
            detalle.setCantidad(producto.getCantidad());
            compra.getDetalles().add(detalle);
        }

        cliente.setSaldo(cliente.getSaldo().subtract(total));
        cliente.getCarrito().getProductosList().clear();

        return compraMapper.toDTO(repoCompra.save(compra));
    }
}
