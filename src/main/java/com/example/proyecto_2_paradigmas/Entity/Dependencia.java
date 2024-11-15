package com.example.proyecto_2_paradigmas.Entity;

import com.example.proyecto_2_paradigmas.Enum.TipoDependencia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dependencias")
public class Dependencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    @JsonIgnore
    private Tarea tarea;


    private Long idTareaDependiente;

    @ManyToOne
    @JoinColumn(name = "clima_id")
    private Clima climaDependiente;

    @Enumerated(EnumType.STRING)
    private TipoDependencia tipoDependencia;

    // Método para validar que solo una dependencia esté presente
    @PrePersist
    @PreUpdate
    public void validarDependencia() {
        if (tipoDependencia == TipoDependencia.TAREA && idTareaDependiente == null) {
            throw new IllegalArgumentException("El ID de la tarea dependiente debe estar definido para una dependencia de tipo TAREA");
        }
        if (tipoDependencia == TipoDependencia.CLIMA && climaDependiente == null) {
            throw new IllegalArgumentException("El clima dependiente debe estar definido para una dependencia de tipo CLIMA");
        }
    }
}
