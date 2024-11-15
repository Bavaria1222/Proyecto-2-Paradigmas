package com.example.proyecto_2_paradigmas.Service;

import com.example.proyecto_2_paradigmas.Entity.Planificacion;
import com.example.proyecto_2_paradigmas.Entity.Tarea;
import com.example.proyecto_2_paradigmas.Repository.PlanificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanificacionService {

    @Autowired
    private PlanificacionRepository planificacionRepository;

    /**
     * Guarda una planificación.
     */
    public Planificacion guardarPlanificacion(Planificacion planificacion) {
        return planificacionRepository.save(planificacion);
    }

    /**
     * Agrega una tarea a una planificación existente.
     */
    public Planificacion agregarTareaAPlanificacion(Planificacion planificacion, Tarea tarea) {
        planificacion.getTareas().add(tarea);
        return planificacionRepository.save(planificacion);
    }

    public Optional<Planificacion> obtenerPorId(Long id) {
        return planificacionRepository.findById(id);
    }

    public List<Planificacion> obtenerTodas() {
        return planificacionRepository.findAll();
    }

    public void eliminarPlanificacion(Long id) {
        planificacionRepository.deleteById(id);
    }
}
