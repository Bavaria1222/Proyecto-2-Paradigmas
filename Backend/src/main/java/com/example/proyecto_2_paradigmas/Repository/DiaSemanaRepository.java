package com.example.proyecto_2_paradigmas.Repository;

import com.example.proyecto_2_paradigmas.Entity.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaSemanaRepository  extends JpaRepository<DiaSemana, Long> {

    Optional<DiaSemana> findByNombre(String nombre);

    Optional<DiaSemana> findByFecha(java.time.LocalDate fecha);

}
