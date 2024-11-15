package com.example.proyecto_2_paradigmas.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "dias_semana")
public class DiaSemana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Nombre del día (por ejemplo, "Lunes")
    private LocalDateTime fecha; // Fecha específica del día

    @OneToMany(mappedBy = "diaSemana", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Manejo de la serialización JSON
    private List<Clima> climas = new ArrayList<>();
}
