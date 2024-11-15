package com.example.proyecto_2_paradigmas.Entity;

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
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    @JsonIgnore
    private Tarea tarea;

    // Almacenamos solo el ID de la tarea dependiente
    private Long idTareaDependiente;
}
