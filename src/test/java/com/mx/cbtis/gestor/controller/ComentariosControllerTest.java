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

import com.mx.cbtis.gestor.modelo.Comentarios;
import com.mx.cbtis.gestor.repository.ComentariosRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComentariosControllerTest {

    @Mock
    private ComentariosRepository comentariostable;

    @InjectMocks
    private ComentariosController controller;

    @Test
    @DisplayName("Probar getComentario por id_tarea")
    void testGetComentarioPorIdTarea() {
        Comentarios req = new Comentarios();
        req.setId_tarea(5);

        Comentarios c1 = new Comentarios(1, "User1", 5, "Buen avance", "2024-05-01");
        when(comentariostable.findByTarea(5)).thenReturn(Arrays.asList(c1));

        List<Comentarios> list = controller.getComentario(req);
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("Buen avance", list.get(0).getTexto_comentario());
    }

    @Test
    @DisplayName("Probar getComentario por ID cuando existe")
    void testGetComentarioExiste() {
        Comentarios mockCom = new Comentarios(1, "User1", 5, "Comentario 1", "2024-05-01");
        when(comentariostable.findById(1)).thenReturn(Optional.of(mockCom));

        Comentarios res = controller.getComentario(1);
        assertNotNull(res);
        assertEquals(1, res.getId_comentario());
        assertEquals("Comentario 1", res.getTexto_comentario());
    }

    @Test
    @DisplayName("Probar getComentario por ID cuando NO existe")
    void testGetComentarioNoExiste() {
        when(comentariostable.findById(99)).thenReturn(Optional.empty());

        Comentarios res = controller.getComentario(99);
        assertNotNull(res);
        assertNull(res.getId_comentario());
    }

    @Test
    @DisplayName("Probar addComentario")
    void testAddComentario() {
        Comentarios com = new Comentarios(1, "User1", 5, "Nuevo", "2024-05-01");
        when(comentariostable.save(com)).thenReturn(com);

        int res = controller.addComentario(com);
        assertEquals(1, res);
        verify(comentariostable).save(com);
    }

    @Test
    @DisplayName("Probar updateComentario")
    void testUpdateComentario() {
        Comentarios com = new Comentarios(1, "User1", 5, "Modificado", "2024-05-01");
        when(comentariostable.save(com)).thenReturn(com);

        int res = controller.updateComentario(com);
        assertEquals(1, res);
        verify(comentariostable).save(com);
    }

    @Test
    @DisplayName("Probar deleteComentario")
    void testDeleteComentario() {
        Comentarios com = new Comentarios(1, "User1", 5, "A borrar", "2024-05-01");
        doNothing().when(comentariostable).delete(com);

        int res = controller.deleteComentario(com);
        assertEquals(1, res);
        verify(comentariostable).delete(com);
    }

    @Test
    @DisplayName("Probar getComentarios lista")
    void testGetComentarios() {
        Comentarios c1 = new Comentarios(1, "User1", 5, "Texto 1", "2024");
        Comentarios c2 = new Comentarios(2, "User2", 5, "Texto 2", "2024");
        when(comentariostable.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Comentarios> res = controller.getComentarios();
        assertNotNull(res);
        assertEquals(2, res.size());
    }
}
