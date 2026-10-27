package com.mx.cbtis.gestor.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    @DisplayName("Probar constructor con parámetros y getters de Usuario")
    void testUsuarioConstructorConParametros() {
        Usuario usuario = new Usuario(1, "Enrique Perez", "enrique@example.com", "pass123", "ADMIN", "avatar.png");

        assertEquals(1, usuario.getId_usuario());
        assertEquals("Enrique Perez", usuario.getNombre());
        assertEquals("enrique@example.com", usuario.getCorreo_electronico());
        assertEquals("pass123", usuario.getContrasena());
        assertEquals("ADMIN", usuario.getRol());
        assertEquals("avatar.png", usuario.getImagen_usuario());
    }

    @Test
    @DisplayName("Probar constructor vacío y setters de Usuario")
    void testUsuarioSettersYGetters() {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(2);
        usuario.setNombre("Maria Lopez");
        usuario.setCorreo_electronico("maria@example.com");
        usuario.setContrasena("secret456");
        usuario.setRol("DEVELOPER");
        usuario.setImagen_usuario("user2.jpg");

        assertEquals(2, usuario.getId_usuario());
        assertEquals("Maria Lopez", usuario.getNombre());
        assertEquals("maria@example.com", usuario.getCorreo_electronico());
        assertEquals("secret456", usuario.getContrasena());
        assertEquals("DEVELOPER", usuario.getRol());
        assertEquals("user2.jpg", usuario.getImagen_usuario());
    }

    @Test
    @DisplayName("Probar valores nulos en Usuario")
    void testUsuarioValoresNulos() {
        Usuario usuario = new Usuario();
        assertNull(usuario.getId_usuario());
        assertNull(usuario.getNombre());
        assertNull(usuario.getCorreo_electronico());
        assertNull(usuario.getContrasena());
        assertNull(usuario.getRol());
        assertNull(usuario.getImagen_usuario());
    }
}
