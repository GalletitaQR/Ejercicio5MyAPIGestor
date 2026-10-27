package com.mx.cbtis.gestor.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProyectosTest {

    @Test
    @DisplayName("Probar constructor con parámetros y getters de Proyectos")
    void testProyectosConstructorConParametros() {
        Proyectos proyecto = new Proyectos(1, "Sistema ERP", "Gestion empresarial", "2024-01-01", "2024-12-31", "En Progreso", "fondo.png");

        assertEquals(1, proyecto.getId_proyecto());
        assertEquals("Sistema ERP", proyecto.getNombre());
        assertEquals("Gestion empresarial", proyecto.getDescripcion());
        assertEquals("2024-01-01", proyecto.getFecha_inicio());
        assertEquals("2024-12-31", proyecto.getFecha_finalizacion());
        assertEquals("En Progreso", proyecto.getEstado_proyecto());
        assertEquals("fondo.png", proyecto.getFondo());
    }

    @Test
    @DisplayName("Probar constructor vacío y setters de Proyectos")
    void testProyectosSettersYGetters() {
        Proyectos proyecto = new Proyectos();
        proyecto.setId_proyecto(2);
        proyecto.setNombre("App Móvil");
        proyecto.setDescripcion("App para clientes");
        proyecto.setFecha_inicio("2024-03-01");
        proyecto.setFecha_finalizacion("2024-09-30");
        proyecto.setEstado_proyecto("Completado");
        proyecto.setFondo("banner.jpg");

        assertEquals(2, proyecto.getId_proyecto());
        assertEquals("App Móvil", proyecto.getNombre());
        assertEquals("App para clientes", proyecto.getDescripcion());
        assertEquals("2024-03-01", proyecto.getFecha_inicio());
        assertEquals("2024-09-30", proyecto.getFecha_finalizacion());
        assertEquals("Completado", proyecto.getEstado_proyecto());
        assertEquals("banner.jpg", proyecto.getFondo());
    }

    @Test
    @DisplayName("Probar valores nulos en Proyectos")
    void testProyectosValoresNulos() {
        Proyectos proyecto = new Proyectos();
        assertNull(proyecto.getId_proyecto());
        assertNull(proyecto.getNombre());
        assertNull(proyecto.getDescripcion());
        assertNull(proyecto.getFecha_inicio());
        assertNull(proyecto.getFecha_finalizacion());
        assertNull(proyecto.getEstado_proyecto());
        assertNull(proyecto.getFondo());
    }
}
