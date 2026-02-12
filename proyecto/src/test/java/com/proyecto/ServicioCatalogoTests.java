package com.proyecto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyecto.DTOs.CatalogoDTO;
import com.proyecto.DataProvider.DataProvider;
import com.proyecto.Repositories.RepoCatalogo;
import com.proyecto.Services.ServicioCatalogo;

@ExtendWith(MockitoExtension.class)
public class ServicioCatalogoTests {

    @Mock
    private RepoCatalogo repoCatalogo;

    @InjectMocks
    private ServicioCatalogo servicioCatalogo;

    // Primero vamos a testear las excepciones

    @Test
    public void obtenerCatalogoNullTest() {
        when(repoCatalogo.findAll()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> servicioCatalogo.obtenerCatalogo());
    }

    @Test
    public void obtenerCatalogoVacioTest() {
        when(repoCatalogo.findAll()).thenReturn(List.of());
        assertThrows(RuntimeException.class, () -> servicioCatalogo.obtenerCatalogo());
    }

    @Test
    public void obtenerCatalogoTest() {

        CatalogoDTO catalogoDTO = new CatalogoDTO(1L, 1L, "Producto de prueba", "Categoría de prueba", 100.0, 10);
        
        when(repoCatalogo.findAll()).thenReturn(List.of(DataProvider.catalogoMock()));
        List<CatalogoDTO> resultado = servicioCatalogo.obtenerCatalogo();
        assertFalse(resultado.isEmpty());
        assertEquals(catalogoDTO, resultado.get(0));
    }

    @Test
    public void obtenerCatalogoPorIdNullTest() {
        when(repoCatalogo.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> servicioCatalogo.obtenerCatalogoPorId(1L));
    }

    @Test
    public void obtenerCatalogoPorIdTest() {
        CatalogoDTO catalogoDTO = new CatalogoDTO(1L, 1L, "Producto de prueba", "Categoría de prueba", 100.0, 10);
        when(repoCatalogo.findById(anyLong())).thenReturn(Optional.of(DataProvider.catalogoMock()));
        CatalogoDTO resultado = servicioCatalogo.obtenerCatalogoPorId(1L);
        assertEquals(catalogoDTO, resultado);
    }

    @Test
    public void obtenerCatalogoPorFiltroTest() {

        CatalogoDTO catalogoDTO = new CatalogoDTO(4L, 4L, "Coca Cola", "Bebidas", 15.0, 50);


        when(repoCatalogo.buscarPorFiltros(
                anyString(),
                anyString(),
                anyDouble(),
                anyDouble())).thenReturn(List.of(DataProvider.catalogoMock()));

        List<CatalogoDTO> resultado = servicioCatalogo.obtenerCatalogoPorFiltro("Coca Cola", "Bebidas", 10.0, 50.0);

        assertEquals(1, resultado.size());
        assertEquals(catalogoDTO, resultado.get(0));
    }

}
