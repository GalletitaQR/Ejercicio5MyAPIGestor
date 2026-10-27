package com.mx.cbtis.gestor.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mx.cbtis.gestor.modelo.Usuario;
import com.mx.cbtis.gestor.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioRepository usuariotable;

    @InjectMocks
    private UsuarioController controller;

    @Test
    @DisplayName("Probar getUsuarioLogin cuando usuario existe")
    void testGetUsuarioLoginExistente() {
        Usuario param = new Usuario();
        param.setCorreo_electronico("test@mail.com");
        param.setContrasena("pass123");

        Usuario mockUser = new Usuario(1, "Enrique", "test@mail.com", "pass123", "ADMIN", "img.png");
        when(usuariotable.findByCorreoPass("test@mail.com", "pass123")).thenReturn(Optional.of(mockUser));

        Usuario result = controller.getUsuarioLogin(param);
        assertNotNull(result);
        assertEquals("Enrique", result.getNombre());
        assertEquals(1, result.getId_usuario());
    }

    @Test
    @DisplayName("Probar getUsuarioLogin cuando usuario NO existe")
    void testGetUsuarioLoginNoExistente() {
        Usuario param = new Usuario();
        param.setCorreo_electronico("none@mail.com");
        param.setContrasena("wrong");

        when(usuariotable.findByCorreoPass("none@mail.com", "wrong")).thenReturn(Optional.empty());

        Usuario result = controller.getUsuarioLogin(param);
        assertNotNull(result);
        assertEquals("000", result.getNombre());
    }

    @Test
    @DisplayName("Probar getUsuario por ID cuando existe")
    void testGetUsuarioExiste() {
        Usuario mockUser = new Usuario(5, "Carlos", "carlos@mail.com", "pass", "DEV", "avatar.jpg");
        when(usuariotable.findById(5)).thenReturn(Optional.of(mockUser));

        Usuario result = controller.getUsuario(5);
        assertNotNull(result);
        assertEquals(5, result.getId_usuario());
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    @DisplayName("Probar getUsuario por ID cuando NO existe")
    void testGetUsuarioNoExiste() {
        when(usuariotable.findById(99)).thenReturn(Optional.empty());

        Usuario result = controller.getUsuario(99);
        assertNotNull(result);
        assertNull(result.getId_usuario());
    }

    @Test
    @DisplayName("Probar addUsuario")
    void testAddUsuario() {
        Usuario usuario = new Usuario(1, "Pedro", "pedro@mail.com", "pass", "DEV", "pic.png");
        when(usuariotable.save(usuario)).thenReturn(usuario);

        int res = controller.addUsuario(usuario);
        assertEquals(1, res);
        verify(usuariotable).save(usuario);
    }

    @Test
    @DisplayName("Probar updateUsuario")
    void testUpdateUsuario() {
        Usuario usuario = new Usuario(1, "Pedro Mod", "pedro@mail.com", "pass", "DEV", "pic.png");
        when(usuariotable.save(usuario)).thenReturn(usuario);

        int res = controller.updateUsuario(usuario);
        assertEquals(1, res);
        verify(usuariotable).save(usuario);
    }

    @Test
    @DisplayName("Probar deleteUsuario")
    void testDeleteUsuario() {
        Usuario usuario = new Usuario(1, "Pedro", "pedro@mail.com", "pass", "DEV", "pic.png");
        doNothing().when(usuariotable).delete(usuario);

        int res = controller.deleteUsuario(usuario);
        assertEquals(1, res);
        verify(usuariotable).delete(usuario);
    }

    @Test
    @DisplayName("Probar getUsuarios lista")
    void testGetUsuarios() {
        Usuario u1 = new Usuario(1, "Pedro", "pedro@mail.com", "pass", "DEV", "pic.png");
        Usuario u2 = new Usuario(2, "Ana", "ana@mail.com", "pass", "ADMIN", "pic2.png");
        when(usuariotable.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Usuario> lista = controller.getUsuarios();
        assertNotNull(lista);
        assertEquals(2, lista.size());
    }
}
