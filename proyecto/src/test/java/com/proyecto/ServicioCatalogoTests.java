package com.proyecto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyecto.DataProvider.DataProvider;
import com.proyecto.Entities.Catalogo;
import com.proyecto.Repositories.RepoCatalogo;
import com.proyecto.Services.ServicioCatalogo;

@ExtendWith(MockitoExtension.class)
public class ServicioCatalogoTests {

    @Mock
    private RepoCatalogo repoCatalogo;
    
    @InjectMocks
    private ServicioCatalogo servicioCatalogo;


    @Test
    public void obtenerCatalogoTest(){
        
        Catalogo catalogoMock = DataProvider.catalogoMock();
        when(servicioCatalogo.obtenerCatalogo()).thenReturn(List.of(catalogoMock)); 
        // Verificar que el método findAll del repositorio devuelve 
        // la lista de catálogos mockeada
        assertEquals(catalogoMock,catalogos.get(0));

    }

}
