package com.example.proyecto_2_paradigmas.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PrioridadDTO {
    @JsonIgnore
    private Long id;
    private String descripcion;
}
