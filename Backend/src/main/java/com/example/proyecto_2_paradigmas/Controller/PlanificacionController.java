package com.example.proyecto_2_paradigmas.Controller;

import com.example.proyecto_2_paradigmas.DTO.PlanificacionDTO;
import com.example.proyecto_2_paradigmas.DTO.TareaDTO;
import com.example.proyecto_2_paradigmas.Entity.Planificacion;
import com.example.proyecto_2_paradigmas.Entity.Tarea;
import com.example.proyecto_2_paradigmas.Service.PlanificacionService;
import com.example.proyecto_2_paradigmas.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planificacion")
public class PlanificacionController {

    @Autowired
    private PlanificacionService planificacionService;

    @Autowired
    private TareaService tareaService;

    /**
     * Endpoint para crear una nueva planificación.
     */
    @PostMapping
    public ResponseEntity<Planificacion> crearPlanificacion(@RequestBody PlanificacionDTO planificacionDTO) {
        // Crear una nueva planificación basada en el DTO recibido
        Planificacion planificacion = new Planificacion();
        planificacion.setFecha(planificacionDTO.getFecha());

        // Guardar la planificación en la base de datos
        Planificacion planificacionGuardada = planificacionService.guardarPlanificacion(planificacion);
        return ResponseEntity.ok(planificacionGuardada);
    }


    /**
     * Endpoint para obtener una planificación por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Planificacion> obtenerPlanificacion(@PathVariable Long id) {
        return planificacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para obtener todas las planificaciones.
     */
    @GetMapping
    public ResponseEntity<List<Planificacion>> obtenerTodasLasPlanificaciones() {
        return ResponseEntity.ok(planificacionService.obtenerTodas());
    }

    /**
     * Endpoint para eliminar una planificación por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlanificacion(@PathVariable Long id) {
        planificacionService.eliminarPlanificacion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/agregarTareas")
    public ResponseEntity<Planificacion> agregarTareasAPlanificacion(
            @PathVariable Long id,
            @RequestBody List<Long> tareaIds
    ) {
        Optional<Planificacion> optionalPlanificacion = planificacionService.obtenerPorId(id);
        if (optionalPlanificacion.isPresent()) {
            Planificacion planificacion = optionalPlanificacion.get();

            // Busca las tareas por sus IDs y las asocia a la planificación
            List<Tarea> tareas = tareaIds.stream()
                    .map(tareaId -> tareaService.obtenerPorId(tareaId)) // Asume que tienes un servicio de Tarea
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            // Agrega las tareas a la planificación y establece la relación bidireccional
            for (Tarea tarea : tareas) {
                tarea.setPlanificacion(planificacion); // Configura la planificación en cada tarea
            }
            planificacion.getTareas().addAll(tareas); // Agrega las tareas a la planificación

            Planificacion planificacionActualizada = planificacionService.guardarPlanificacion(planificacion);
            return ResponseEntity.ok(planificacionActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
