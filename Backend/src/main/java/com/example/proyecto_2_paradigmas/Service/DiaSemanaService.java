package com.example.proyecto_2_paradigmas.Service;

import com.example.proyecto_2_paradigmas.Entity.DiaSemana;
import com.example.proyecto_2_paradigmas.Repository.DiaSemanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DiaSemanaService {

    @Autowired
    private DiaSemanaRepository diaSemanaRepository;

    // Guardar un día de la semana
    public DiaSemana guardarDiaSemana(DiaSemana diaSemana) {
        return diaSemanaRepository.save(diaSemana);
    }

    // Obtener todos los días de la semana
    public List<DiaSemana> obtenerTodosLosDias() {
        return diaSemanaRepository.findAll();
    }

    // Obtener un día de la semana por ID
    public Optional<DiaSemana> obtenerDiaSemanaPorId(Long id) {
        return diaSemanaRepository.findById(id);
    }

    // Actualizar un día de la semana
    public DiaSemana actualizarDiaSemana(Long id, DiaSemana diaSemanaActualizado) {
        return diaSemanaRepository.findById(id).map(diaSemana -> {
            diaSemana.setNombre(diaSemanaActualizado.getNombre());
            diaSemana.setFecha(diaSemanaActualizado.getFecha());
            diaSemana.setClimas(diaSemanaActualizado.getClimas());
            return diaSemanaRepository.save(diaSemana);
        }).orElseThrow(() -> new RuntimeException("Día de la semana no encontrado con ID: " + id));
    }

    // Eliminar un día de la semana por ID
    public void eliminarDiaSemana(Long id) {
        if (diaSemanaRepository.existsById(id)) {
            diaSemanaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Día de la semana no encontrado con ID: " + id);
        }
    }

    // Obtener un día de la semana por nombre
    public Optional<DiaSemana> obtenerDiaSemanaPorNombre(String nombre) {
        return diaSemanaRepository.findByNombre(nombre);
    }

    // Obtener un día de la semana por fecha
    public Optional<DiaSemana> obtenerDiaSemanaPorFecha(LocalDate fecha) {
        return diaSemanaRepository.findByFecha(fecha);
    }
}
