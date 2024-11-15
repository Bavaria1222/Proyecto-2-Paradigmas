package com.example.proyecto_2_paradigmas.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificacionDTO {
    private LocalDate fecha;
}
