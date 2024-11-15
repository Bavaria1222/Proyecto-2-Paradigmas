package com.example.proyecto_2_paradigmas.Controller;

import com.example.proyecto_2_paradigmas.DTO.PrioridadDTO;
import com.example.proyecto_2_paradigmas.Entity.Prioridad;
import com.example.proyecto_2_paradigmas.Service.PrioridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prioridad")
public class PrioridadController {

    @Autowired
    private PrioridadService prioridadService;

    @GetMapping("/{id}")
    public ResponseEntity<Prioridad> obtenerPrioridad(@PathVariable Long id) {
        return prioridadService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Prioridad>> obtenerTodasLasPrioridades() {
        return ResponseEntity.ok(prioridadService.obtenerTodas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrioridad(@PathVariable Long id) {
        try {
            prioridadService.eliminarPrioridad(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Prioridad> crearPrioridad(@RequestBody PrioridadDTO prioridadDTO) {
        // Convertir PrioridadDTO a Prioridad
        Prioridad prioridad = new Prioridad();
        prioridad.setDescripcion(prioridadDTO.getDescripcion());

        // Guardar la prioridad usando el servicio
        Prioridad prioridadGuardada = prioridadService.guardarPrioridad(prioridad);
        return ResponseEntity.ok(prioridadGuardada);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Prioridad> actualizarPrioridad(@PathVariable Long id, @RequestBody PrioridadDTO ActualizarPrioridadDTO) {
        // Verificar si la prioridad existe
        Prioridad prioridadExistente = prioridadService.obtenerPorId(id)
                .orElse(null);
        if (prioridadExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar la descripción de la prioridad
        prioridadExistente.setDescripcion(ActualizarPrioridadDTO.getDescripcion());

        // Guardar la prioridad actualizada
        Prioridad prioridadActualizada = prioridadService.guardarPrioridad(prioridadExistente);
        return ResponseEntity.ok(prioridadActualizada);
    }
}
