package com.example.proyecto_2_paradigmas.Controller;

import com.example.proyecto_2_paradigmas.Entity.Dependencia;
import com.example.proyecto_2_paradigmas.Service.DependenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dependencia")
public class DependenciaController {

    @Autowired
    private DependenciaService dependenciaService;

    @GetMapping("/{id}")
    public ResponseEntity<Dependencia> obtenerDependencia(@PathVariable Long id) {
        return dependenciaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Dependencia>> obtenerTodasLasDependencias() {
        return ResponseEntity.ok(dependenciaService.obtenerTodas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDependencia(@PathVariable Long id) {
        dependenciaService.eliminarDependencia(id);
        return ResponseEntity.noContent().build();
    }
}
