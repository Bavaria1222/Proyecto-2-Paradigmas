package com.example.proyecto_2_paradigmas.DTO;

import com.example.proyecto_2_paradigmas.Enum.TipoDependencia;
import lombok.Data;

@Data
public class DependenciaDTO {
    private Long idTareaDependiente;
    private Long climaDependienteId;
    private TipoDependencia tipoDependencia;
}
