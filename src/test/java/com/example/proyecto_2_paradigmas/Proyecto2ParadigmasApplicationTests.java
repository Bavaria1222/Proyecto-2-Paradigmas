package com.example.proyecto_2_paradigmas;

import com.example.proyecto_2_paradigmas.Prolog.PrologEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Proyecto2ParadigmasApplicationTests {

    private PrologEngine engine;

    @BeforeEach
    void setUp() {
        // Inicializa el motor Prolog antes de cada prueba
        engine = new PrologEngine();
    }

    @Test
    void contextLoads() {
        // Verifica que el contexto de Spring se cargue correctamente
    }

    @Test
    void testTareaExiste() {
        // Prueba para verificar que la tarea 'desayunar' esté definida en Prolog
        boolean tareaExiste = engine.verificarTarea("desayunar");
        assertTrue(tareaExiste, "La tarea 'desayunar' debería estar definida en task.pl");
    }

    @Test
    void testTareaNoExiste() {
        // Prueba para verificar que una tarea inexistente no esté definida en Prolog
        boolean tareaExiste = engine.verificarTarea("tarea_inexistente");
        assertFalse(tareaExiste, "La tarea 'tarea_inexistente' no debería estar definida en task.pl");
    }
}
