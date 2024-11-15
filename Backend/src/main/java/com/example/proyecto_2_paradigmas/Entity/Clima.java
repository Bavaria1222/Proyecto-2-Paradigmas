package com.example.proyecto_2_paradigmas.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "climas")
public class Clima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Ejemplo: soleado, lluvioso, etc.

    private LocalDateTime horaInicio; // Hora de inicio del clima
    private LocalDateTime horaFin;    // Hora de fin del clima

    @ManyToOne
    @JoinColumn(name = "dia_semana_id", nullable = false)
    @JsonBackReference
    private DiaSemana diaSemana;
}
