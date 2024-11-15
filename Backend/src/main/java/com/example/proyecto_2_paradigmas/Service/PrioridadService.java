package com.example.proyecto_2_paradigmas.Service;

import com.example.proyecto_2_paradigmas.Entity.Prioridad;
import com.example.proyecto_2_paradigmas.Repository.PrioridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrioridadService {

    @Autowired
    private PrioridadRepository prioridadRepository;

    public Optional<Prioridad> obtenerPorId(Long id) {
        return prioridadRepository.findById(id);
    }

    public List<Prioridad> obtenerTodas() {
        return prioridadRepository.findAll();
    }

    public void eliminarPrioridad(Long id) {
        if (prioridadRepository.existsById(id)) {
            prioridadRepository.deleteById(id);
        } else {
            throw new RuntimeException("Prioridad no encontrada con ID: " + id);
        }
    }

    public Prioridad guardarPrioridad(Prioridad prioridad) {
        return prioridadRepository.save(prioridad);
    }
    public Optional<Prioridad> obtenerPrioridadPorDescripcion(String descripcion) {
        return prioridadRepository.findByDescripcion(descripcion);
    }


}
