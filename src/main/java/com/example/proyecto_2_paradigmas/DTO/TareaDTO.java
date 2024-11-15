package com.example.proyecto_2_paradigmas.DTO;

import com.example.proyecto_2_paradigmas.Enum.Estado;
import com.example.proyecto_2_paradigmas.Enum.Prioridad;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TareaDTO {
    private String nombre;
    private Prioridad prioridad;
    private int tiempoEstimado;
    private String requisitos;
    private Estado estado;
    private LocalDateTime horaInicio;
    private LocalDateTime fechaLimite;
}
