package com.example.proyecto_2_paradigmas.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClimaDTO {
    private String tipo; // Ejemplo: soleado, lluvioso, etc.
    private LocalDateTime horaInicio; // Hora de inicio del clima
    private LocalDateTime horaFin; // Hora de fin del clima
    private Long diaSemanaId; // Solo enviamos el ID del día de la semana
}
