package com.example.proyecto_2_paradigmas.Repository;

import com.example.proyecto_2_paradigmas.Entity.Clima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Repository
public interface ClimaRepository extends JpaRepository<Clima, Long> {

    boolean existsByHoraInicioAndHoraFinAndDiaSemanaId(
            LocalDateTime horaInicio, LocalDateTime horaFin, Long diaSemanaId);
}
