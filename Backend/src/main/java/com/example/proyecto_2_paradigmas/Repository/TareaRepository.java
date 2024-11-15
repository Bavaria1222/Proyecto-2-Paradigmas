package com.example.proyecto_2_paradigmas.Repository;

import com.example.proyecto_2_paradigmas.Entity.Tarea;
import com.example.proyecto_2_paradigmas.Enum.Estado;
import com.example.proyecto_2_paradigmas.Enum.Prioridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByEstado(Estado estado);
}
