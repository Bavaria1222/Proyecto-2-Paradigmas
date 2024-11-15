package com.example.proyecto_2_paradigmas.Controller;

import com.example.proyecto_2_paradigmas.Entity.Clima;
import com.example.proyecto_2_paradigmas.Service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clima")

public class ClimaController {

    @Autowired
    private ClimaService climaService;


    @GetMapping
    public ResponseEntity<List<Clima>> getAllClimas() {
        return ResponseEntity.ok(climaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clima> getClimaById(@PathVariable Long id) {
        return climaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Clima> saveClima(@RequestBody Clima clima) {
        return Optional.ofNullable(clima)
                .map(climaService::guardarClima)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClima(@PathVariable Long id) {
        return climaService.obtenerPorId(id)
                .map(existingClima -> {
                    climaService.eliminarClima(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
