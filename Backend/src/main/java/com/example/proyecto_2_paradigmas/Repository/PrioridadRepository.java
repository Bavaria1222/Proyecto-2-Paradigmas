package com.example.proyecto_2_paradigmas.Repository;

import com.example.proyecto_2_paradigmas.Entity.Prioridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrioridadRepository extends JpaRepository<Prioridad, Long> {
    Optional<Prioridad> findByDescripcion(String descripcion);
}
