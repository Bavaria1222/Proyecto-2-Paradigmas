package com.example.proyecto_2_paradigmas.Service;

import com.example.proyecto_2_paradigmas.Entity.Clima;
import com.example.proyecto_2_paradigmas.Exception.ClimaConflictException;
import com.example.proyecto_2_paradigmas.Repository.ClimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClimaService {

    @Autowired
    private ClimaRepository climaRepository;

    // Guardar clima con validación de horarios
    public Clima guardarClima(Clima clima) {
        boolean existeConflicto = climaRepository.existsByHoraInicioAndHoraFinAndDiaSemanaId(
                clima.getHoraInicio(), clima.getHoraFin(), clima.getDiaSemana().getId()
        );
        if (existeConflicto) {
            throw new ClimaConflictException("Ya existe un clima con las mismas horas de inicio y fin para este día.");
        }
        return climaRepository.save(clima);
    }


    // Verificar si existe un clima con las mismas horas de inicio y fin en el mismo día
    public boolean existeClimaConHoras(Long diaSemanaId, LocalDateTime horaInicio, LocalDateTime horaFin) {
        return climaRepository.existsByHoraInicioAndHoraFinAndDiaSemanaId(horaInicio, horaFin, diaSemanaId);
    }

    // Obtener todos los climas
    public List<Clima> obtenerTodos() {
        return climaRepository.findAll();
    }

    // Obtener clima por ID
    public Optional<Clima> obtenerPorId(Long id) {
        return climaRepository.findById(id);
    }

    // Eliminar clima por ID
    public void eliminarClima(Long id) {
        if (climaRepository.existsById(id)) {
            climaRepository.deleteById(id);
        } else {
            throw new RuntimeException("El clima con ID " + id + " no existe.");
        }
    }
}
