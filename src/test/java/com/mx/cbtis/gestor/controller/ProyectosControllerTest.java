package com.mx.cbtis.gestor.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mx.cbtis.gestor.modelo.Proyectos;
import com.mx.cbtis.gestor.repository.ProtectosRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProyectosControllerTest {

    @Mock
    private ProtectosRepository proyectostable;

    @InjectMocks
    private ProyectosController controller;

    @Test
    @DisplayName("Probar getProyecto por id_proyecto cuando existe")
    void testGetProyectoExiste() {
        Proyectos mockP = new Proyectos(1, "Proyecto 1", "Desc", "2024-01-01", "2024-12-31", "Activo", "fondo.png");
        when(proyectostable.findById(1)).thenReturn(Optional.of(mockP));

        Proyectos result = controller.getProyecto(1);
        assertNotNull(result);
        assertEquals(1, result.getId_proyecto());
        assertEquals("Proyecto 1", result.getNombre());
    }

    @Test
    @DisplayName("Probar getProyecto por id_proyecto cuando NO existe")
    void testGetProyectoNoExiste() {
        when(proyectostable.findById(99)).thenReturn(Optional.empty());

        Proyectos result = controller.getProyecto(99);
        assertNotNull(result);
        assertNull(result.getId_proyecto());
    }

    @Test
    @DisplayName("Probar addProyecto")
    void testAddProyecto() {
        Proyectos p = new Proyectos(1, "Nuevo", "Desc", "2024", "2024", "Activo", "f.png");
        when(proyectostable.save(p)).thenReturn(p);

        int res = controller.addProyecto(p);
        assertEquals(1, res);
        verify(proyectostable).save(p);
    }

    @Test
    @DisplayName("Probar updateProyecto")
    void testUpdateProyecto() {
        Proyectos p = new Proyectos(1, "Modificado", "Desc", "2024", "2024", "Activo", "f.png");
        when(proyectostable.save(p)).thenReturn(p);

        int res = controller.updateProyecto(p);
        assertEquals(1, res);
        verify(proyectostable).save(p);
    }

    @Test
    @DisplayName("Probar deleteProyecto")
    void testDeleteProyecto() {
        Proyectos p = new Proyectos(1, "Borrar", "Desc", "2024", "2024", "Activo", "f.png");
        doNothing().when(proyectostable).delete(p);

        int res = controller.deleteProyecto(p);
        assertEquals(1, res);
        verify(proyectostable).delete(p);
    }

    @Test
    @DisplayName("Probar getProyectos lista")
    void testGetProyectos() {
        Proyectos p1 = new Proyectos(1, "P1", "Desc", "2024", "2024", "Activo", "f1.png");
        Proyectos p2 = new Proyectos(2, "P2", "Desc", "2024", "2024", "Activo", "f2.png");
        when(proyectostable.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Proyectos> result = controller.getProyectos();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
