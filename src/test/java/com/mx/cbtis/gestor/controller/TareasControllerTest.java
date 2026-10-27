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

import com.mx.cbtis.gestor.modelo.Tareas;
import com.mx.cbtis.gestor.repository.TareasRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TareasControllerTest {

    @Mock
    private TareasRepository tareastable;

    @InjectMocks
    private TareasController controller;

    @Test
    @DisplayName("Probar getTarea por id_proyecto")
    void testGetTareaPorIdProyecto() {
        Tareas req = new Tareas();
        req.setId_proyecto(10);

        Tareas t1 = new Tareas(1, "Tarea 1", "Desc", "2024-06-01", "Lista 1", "Pendiente", 10);
        when(tareastable.findByIdProyecto(10)).thenReturn(Arrays.asList(t1));

        List<Tareas> result = controller.getTarea(req);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Tarea 1", result.get(0).getNombre());
    }

    @Test
    @DisplayName("Probar getTarea por id_tarea cuando existe")
    void testGetTareaExiste() {
        Tareas mockT = new Tareas(5, "Tarea 5", "Desc", "2024", "L", "E", 1);
        when(tareastable.findById(5)).thenReturn(Optional.of(mockT));

        Tareas result = controller.getTarea(5);
        assertNotNull(result);
        assertEquals(5, result.getId_tarea());
        assertEquals("Tarea 5", result.getNombre());
    }

    @Test
    @DisplayName("Probar getTarea por id_tarea cuando NO existe")
    void testGetTareaNoExiste() {
        when(tareastable.findById(99)).thenReturn(Optional.empty());

        Tareas result = controller.getTarea(99);
        assertNotNull(result);
        assertNull(result.getId_tarea());
    }

    @Test
    @DisplayName("Probar addTareas")
    void testAddTareas() {
        Tareas t = new Tareas(1, "Nueva", "Desc", "2024", "L", "E", 1);
        when(tareastable.save(t)).thenReturn(t);

        int res = controller.addTareas(t);
        assertEquals(1, res);
        verify(tareastable).save(t);
    }

    @Test
    @DisplayName("Probar updateTareas")
    void testUpdateTareas() {
        Tareas t = new Tareas(1, "Modificada", "Desc", "2024", "L", "E", 1);
        when(tareastable.save(t)).thenReturn(t);

        int res = controller.updateTareas(t);
        assertEquals(1, res);
        verify(tareastable).save(t);
    }

    @Test
    @DisplayName("Probar deleteTareas")
    void testDeleteTareas() {
        Tareas t = new Tareas(1, "Borrar", "Desc", "2024", "L", "E", 1);
        doNothing().when(tareastable).delete(t);

        int res = controller.deleteTareas(t);
        assertEquals(1, res);
        verify(tareastable).delete(t);
    }

    @Test
    @DisplayName("Probar getTareas lista")
    void testGetTareas() {
        Tareas t1 = new Tareas(1, "T1", "Desc", "2024", "L", "E", 1);
        Tareas t2 = new Tareas(2, "T2", "Desc", "2024", "L", "E", 1);
        when(tareastable.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Tareas> result = controller.getTareas();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
