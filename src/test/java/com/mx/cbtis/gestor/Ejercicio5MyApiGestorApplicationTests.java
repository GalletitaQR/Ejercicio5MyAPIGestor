package com.mx.cbtis.gestor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class Ejercicio5MyApiGestorApplicationTests {

	@Test
	void contextLoads() {
		Ejercicio5MyApiGestorApplication app = new Ejercicio5MyApiGestorApplication();
		assertNotNull(app);
	}

}
