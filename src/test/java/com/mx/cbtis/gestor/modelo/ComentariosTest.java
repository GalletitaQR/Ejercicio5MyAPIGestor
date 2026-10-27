package com.mx.cbtis.gestor.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComentariosTest {

    @Test
    @DisplayName("Probar constructor con parámetros y getters de Comentarios")
    void testComentariosConstructorConParametros() {
        Comentarios comentario = new Comentarios(1, "User1", 10, "Este es un comentario", "2024-05-10 14:30:00");

        assertEquals(1, comentario.getId_comentario());
        assertEquals("User1", comentario.getId_usuario());
        assertEquals(10, comentario.getId_tarea());
        assertEquals("Este es un comentario", comentario.getTexto_comentario());
        assertEquals("2024-05-10 14:30:00", comentario.getFecha_hora());
    }

    @Test
    @DisplayName("Probar constructor vacío y setters de Comentarios")
    void testComentariosSettersYGetters() {
        Comentarios comentario = new Comentarios();
        comentario.setId_comentario(2);
        comentario.setId_usuario("User2");
        comentario.setId_tarea(20);
        comentario.setTexto_comentario("Comentario actualizado");
        comentario.setFecha_hora("2024-06-12 10:00:00");

        assertEquals(2, comentario.getId_comentario());
        assertEquals("User2", comentario.getId_usuario());
        assertEquals(20, comentario.getId_tarea());
        assertEquals("Comentario actualizado", comentario.getTexto_comentario());
        assertEquals("2024-06-12 10:00:00", comentario.getFecha_hora());
    }

    @Test
    @DisplayName("Probar valores nulos en Comentarios")
    void testComentariosValoresNulos() {
        Comentarios comentario = new Comentarios();
        assertNull(comentario.getId_comentario());
        assertNull(comentario.getId_usuario());
        assertNull(comentario.getId_tarea());
        assertNull(comentario.getTexto_comentario());
        assertNull(comentario.getFecha_hora());
    }
}
