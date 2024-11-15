package com.example.proyecto_2_paradigmas.Entity;

import com.example.proyecto_2_paradigmas.Enum.Estado;
import com.example.proyecto_2_paradigmas.Enum.RestriccionClima;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "prioridad_id", nullable = false)
    private Prioridad prioridad;

    private int tiempoEstimado;

    @Enumerated(EnumType.STRING)
    private RestriccionClima restriccionClima = RestriccionClima.NO_APLICA;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;

    private LocalDateTime horaInicio;

    private LocalDateTime fechaLimite;

    @Setter
    @ManyToOne
    @JoinColumn(name = "planificacion_id")
    @JsonIgnore
    private Planificacion planificacion;

    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Dependencia> dependencias = new ArrayList<>();

    public void agregarDependencia(Long idTareaDependiente) {
        Dependencia dependencia = new Dependencia();
        dependencia.setTarea(this);
        dependencia.setIdTareaDependiente(idTareaDependiente);
        dependencias.add(dependencia);
    }
}
