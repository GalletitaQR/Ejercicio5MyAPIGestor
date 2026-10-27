package com.mx.cbtis.gestor.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TareasTest {

    @Test
    @DisplayName("Probar constructor con parámetros y getters de Tareas")
    void testTareasConstructorConParametros() {
        Tareas tarea = new Tareas(1, "Diseñar UI", "Crear mockups en Figma", "2024-06-30", "Por Hacer", "Pendiente", 10);

        assertEquals(1, tarea.getId_tarea());
        assertEquals("Diseñar UI", tarea.getNombre());
        assertEquals("Crear mockups en Figma", tarea.getDescripcion());
        assertEquals("2024-06-30", tarea.getFecha_vencimiento());
        assertEquals("Por Hacer", tarea.getLista());
        assertEquals("Pendiente", tarea.getEstado());
        assertEquals(10, tarea.getId_proyecto());
    }

    @Test
    @DisplayName("Probar constructor vacío y setters de Tareas")
    void testTareasSettersYGetters() {
        Tareas tarea = new Tareas();
        tarea.setId_tarea(2);
        tarea.setNombre("Implementar API");
        tarea.setDescripcion("Construir controladores REST");
        tarea.setFecha_vencimiento("2024-07-15");
        tarea.setLista("En Proceso");
        tarea.setEstado("En Curso");
        tarea.setId_proyecto(20);

        assertEquals(2, tarea.getId_tarea());
        assertEquals("Implementar API", tarea.getNombre());
        assertEquals("Construir controladores REST", tarea.getDescripcion());
        assertEquals("2024-07-15", tarea.getFecha_vencimiento());
        assertEquals("En Proceso", tarea.getLista());
        assertEquals("En Curso", tarea.getEstado());
        assertEquals(20, tarea.getId_proyecto());
    }

    @Test
    @DisplayName("Probar valores nulos en Tareas")
    void testTareasValoresNulos() {
        Tareas tarea = new Tareas();
        assertNull(tarea.getId_tarea());
        assertNull(tarea.getNombre());
        assertNull(tarea.getDescripcion());
        assertNull(tarea.getFecha_vencimiento());
        assertNull(tarea.getLista());
        assertNull(tarea.getEstado());
        assertNull(tarea.getId_proyecto());
    }
}
