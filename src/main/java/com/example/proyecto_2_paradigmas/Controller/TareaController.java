package com.example.proyecto_2_paradigmas.Controller;

import com.example.proyecto_2_paradigmas.DTO.DependenciaDTO;
import com.example.proyecto_2_paradigmas.DTO.TareaDTO;
import com.example.proyecto_2_paradigmas.DTO.PrioridadDTO;
import com.example.proyecto_2_paradigmas.Entity.Prioridad;
import com.example.proyecto_2_paradigmas.Entity.Tarea;
import com.example.proyecto_2_paradigmas.Service.PrioridadService;
import com.example.proyecto_2_paradigmas.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private PrioridadService prioridadService;

    /**
     * Endpoint para crear una nueva tarea sin dependencias.
     */
    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody TareaDTO tareaDTO) {
        // Convertir TareaDTO a Tarea
        Optional<Prioridad> prioridadOpt = prioridadService.obtenerPorId(tareaDTO.getPrioridad().getId());
        if (prioridadOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Tarea tarea = new Tarea();
        tarea.setNombre(tareaDTO.getNombre());
        tarea.setPrioridad(prioridadOpt.get());
        tarea.setTiempoEstimado(tareaDTO.getTiempoEstimado());
        tarea.setRestriccionClima(tareaDTO.getRestriccionClima());
        tarea.setEstado(tareaDTO.getEstado());
        tarea.setHoraInicio(tareaDTO.getHoraInicio());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());

        // Guardar la tarea usando el servicio
        Tarea tareaGuardada = tareaService.guardarTarea(tarea);
        return ResponseEntity.ok(tareaGuardada);
    }

    /**
     * Endpoint para agregar dependencias a una tarea existente.
     */
    @PostMapping("/{id}/dependencias")
    public ResponseEntity<Tarea> agregarDependencias(@PathVariable Long id, @RequestBody List<DependenciaDTO> dependenciaDTO) {
        Optional<Tarea> tareaOpt = tareaService.obtenerPorId(id);
        if (tareaOpt.isPresent()) {
            Tarea tareaConDependencias = tareaService.agregarDependencias(tareaOpt.get(), dependenciaDTO);
            return ResponseEntity.ok(tareaConDependencias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para obtener una tarea por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Long id) {
        return tareaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para obtener todas las tareas.
     */
    @GetMapping
    public ResponseEntity<List<Tarea>> obtenerTodasLasTareas() {
        return ResponseEntity.ok(tareaService.obtenerTodas());
    }

    /**
     * Endpoint para eliminar una tarea por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }
}
