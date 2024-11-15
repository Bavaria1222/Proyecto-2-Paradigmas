package com.example.proyecto_2_paradigmas.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class DiaSemanaDTO {
    private String nombre;
    private LocalDateTime fecha;

}
