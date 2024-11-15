package com.example.proyecto_2_paradigmas.Controller;

import com.example.proyecto_2_paradigmas.DTO.ClimaDTO;
import com.example.proyecto_2_paradigmas.Entity.Clima;
import com.example.proyecto_2_paradigmas.Entity.DiaSemana;
import com.example.proyecto_2_paradigmas.Service.ClimaService;
import com.example.proyecto_2_paradigmas.Service.DiaSemanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clima")

public class ClimaController {

    @Autowired
    private ClimaService climaService;
    @Autowired
    DiaSemanaService diaSemanaService;



    @GetMapping
    public ResponseEntity<List<Clima>> getAllClimas() {
        return ResponseEntity.ok(climaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clima> getClimaById(@PathVariable Long id) {
        return climaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Clima> saveClima(@RequestBody ClimaDTO climaDTO) {
        DiaSemana diaSemana = diaSemanaService.obtenerDiaSemanaPorId(climaDTO.getDiaSemanaId())
                .orElseThrow(() -> new RuntimeException("Día de la semana no encontrado"));

        Clima clima = new Clima();
        clima.setTipo(climaDTO.getTipo());
        clima.setHoraInicio(climaDTO.getHoraInicio());
        clima.setHoraFin(climaDTO.getHoraFin());
        clima.setDiaSemana(diaSemana);

        Clima nuevoClima = climaService.guardarClima(clima);
        return ResponseEntity.ok(nuevoClima);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClima(@PathVariable Long id) {
        return climaService.obtenerPorId(id)
                .map(existingClima -> {
                    climaService.eliminarClima(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
