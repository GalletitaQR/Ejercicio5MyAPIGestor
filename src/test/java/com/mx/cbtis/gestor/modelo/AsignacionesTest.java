package com.mx.cbtis.gestor.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AsignacionesTest {

    @Test
    @DisplayName("Probar constructor con parámetros y getters de Asignaciones")
    void testAsignacionesConstructorConParametros() {
        Asignaciones asignacion = new Asignaciones(1, 10, 20, "2024-05-10");

        assertEquals(1, asignacion.getId_asignacion());
        assertEquals(10, asignacion.getId_usuario());
        assertEquals(20, asignacion.getId_tarea());
        assertEquals("2024-05-10", asignacion.getFecha_asignacion());
    }

    @Test
    @DisplayName("Probar constructor vacío y setters de Asignaciones")
    void testAsignacionesSettersYGetters() {
        Asignaciones asignacion = new Asignaciones();
        asignacion.setId_asignacion(2);
        asignacion.setId_usuario(15);
        asignacion.setId_tarea(25);
        asignacion.setFecha_asignacion("2024-06-15");

        assertEquals(2, asignacion.getId_asignacion());
        assertEquals(15, asignacion.getId_usuario());
        assertEquals(25, asignacion.getId_tarea());
        assertEquals("2024-06-15", asignacion.getFecha_asignacion());
    }

    @Test
    @DisplayName("Probar valores nulos en Asignaciones")
    void testAsignacionesValoresNulos() {
        Asignaciones asignacion = new Asignaciones();
        assertNull(asignacion.getId_asignacion());
        assertNull(asignacion.getId_usuario());
        assertNull(asignacion.getId_tarea());
        assertNull(asignacion.getFecha_asignacion());
    }
}
