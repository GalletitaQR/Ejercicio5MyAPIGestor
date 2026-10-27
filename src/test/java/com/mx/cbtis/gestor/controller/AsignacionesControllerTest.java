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

import com.mx.cbtis.gestor.modelo.Asignaciones;
import com.mx.cbtis.gestor.repository.AsignacionesRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsignacionesControllerTest {

    @Mock
    private AsignacionesRepository asignacionestable;

    @InjectMocks
    private AsignacionesController controller;

    @Test
    @DisplayName("Probar getAsignacion cuando existe")
    void testGetAsignacionExiste() {
        Asignaciones mockAsig = new Asignaciones(1, 10, 20, "2024-05-01");
        when(asignacionestable.findById(1)).thenReturn(Optional.of(mockAsig));

        Asignaciones result = controller.getAsignacion(1);
        assertNotNull(result);
        assertEquals(1, result.getId_asignacion());
        assertEquals(10, result.getId_usuario());
    }

    @Test
    @DisplayName("Probar getAsignacion cuando NO existe")
    void testGetAsignacionNoExiste() {
        when(asignacionestable.findById(99)).thenReturn(Optional.empty());

        Asignaciones result = controller.getAsignacion(99);
        assertNotNull(result);
        assertNull(result.getId_asignacion());
    }

    @Test
    @DisplayName("Probar addAsignacion")
    void testAddAsignacion() {
        Asignaciones asig = new Asignaciones(1, 10, 20, "2024-05-01");
        when(asignacionestable.save(asig)).thenReturn(asig);

        int res = controller.addAsignacion(asig);
        assertEquals(1, res);
        verify(asignacionestable).save(asig);
    }

    @Test
    @DisplayName("Probar updateAsignacion")
    void testUpdateAsignacion() {
        Asignaciones asig = new Asignaciones(1, 10, 20, "2024-05-01");
        when(asignacionestable.save(asig)).thenReturn(asig);

        int res = controller.updateAsignacion(asig);
        assertEquals(1, res);
        verify(asignacionestable).save(asig);
    }

    @Test
    @DisplayName("Probar deleteAsignacion")
    void testDeleteAsignacion() {
        Asignaciones asig = new Asignaciones(1, 10, 20, "2024-05-01");
        doNothing().when(asignacionestable).delete(asig);

        int res = controller.deleteAsignacion(asig);
        assertEquals(1, res);
        verify(asignacionestable).delete(asig);
    }

    @Test
    @DisplayName("Probar getAsignaciones lista")
    void testGetAsignaciones() {
        Asignaciones a1 = new Asignaciones(1, 10, 20, "2024-05-01");
        Asignaciones a2 = new Asignaciones(2, 11, 21, "2024-05-02");
        when(asignacionestable.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Asignaciones> result = controller.getAsignaciones();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
