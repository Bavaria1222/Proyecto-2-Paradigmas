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
        Tarea tarea = new Tarea();
        tarea.setNombre(tareaDTO.getNombre());
        tarea.setTiempoEstimado(tareaDTO.getTiempoEstimado());
        tarea.setRestriccionClima(tareaDTO.getRestriccionClima());
        tarea.setEstado(tareaDTO.getEstado());
        tarea.setHoraInicio(tareaDTO.getHoraInicio());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());

        // Buscar y asignar prioridad por defecto si no existe una
        Optional<Prioridad> prioridadPorDefecto = prioridadService.obtenerPrioridadPorDescripcion("BAJA");
        prioridadPorDefecto.ifPresent(tarea::setPrioridad);

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

    @PostMapping("/{id}/prioridad/{prioridadId}")
    public ResponseEntity<Tarea> asignarPrioridad(@PathVariable Long id, @PathVariable Long prioridadId) {
        Optional<Tarea> tareaOpt = tareaService.obtenerPorId(id);
        Optional<Prioridad> prioridadOpt = prioridadService.obtenerPorId(prioridadId);

        if (tareaOpt.isEmpty() || prioridadOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Tarea tarea = tareaOpt.get();
        tarea.setPrioridad(prioridadOpt.get());
        Tarea tareaActualizada = tareaService.guardarTarea(tarea);
        return ResponseEntity.ok(tareaActualizada);
    }
}
