package com.example.proyecto_2_paradigmas.DTO;

import com.example.proyecto_2_paradigmas.Enum.Estado;
import com.example.proyecto_2_paradigmas.Enum.Prioridad;
import com.example.proyecto_2_paradigmas.Enum.RestriccionClima;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TareaDTO {
    private String nombre;
    private Prioridad prioridad;
    private int tiempoEstimado;
    private RestriccionClima restriccionClima;
    private Estado estado;
    private LocalDateTime horaInicio;
    private LocalDateTime fechaLimite;
}
