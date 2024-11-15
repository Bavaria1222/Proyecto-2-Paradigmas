package com.example.proyecto_2_paradigmas.Controller;

import com.example.proyecto_2_paradigmas.DTO.DiaSemanaDTO;
import com.example.proyecto_2_paradigmas.DTO.ClimaDTO;
import com.example.proyecto_2_paradigmas.Entity.DiaSemana;
import com.example.proyecto_2_paradigmas.Entity.Clima;
import com.example.proyecto_2_paradigmas.Service.DiaSemanaService;
import com.example.proyecto_2_paradigmas.Service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/diasemana")
public class DiaSemanaController {

    @Autowired
    private DiaSemanaService diaSemanaService;

    @Autowired
    private ClimaService climaService;

    /**
     * Endpoint para crear un día de la semana.
     */
    @PostMapping
    public ResponseEntity<DiaSemana> crearDiaSemana(@RequestBody DiaSemanaDTO diaSemanaDTO) {
        DiaSemana diaSemana = new DiaSemana();
        diaSemana.setNombre(diaSemanaDTO.getNombre());
        diaSemana.setFecha(diaSemanaDTO.getFecha());

        DiaSemana nuevoDiaSemana = diaSemanaService.guardarDiaSemana(diaSemana);
        return ResponseEntity.ok(nuevoDiaSemana);
    }

    /**
     * Endpoint para agregar un clima a un día de la semana existente.
     */
    @PostMapping("/AgregarClima")
    public ResponseEntity<Clima> agregarClima(@RequestBody ClimaDTO climaDTO) {
        Long diaSemanaId = climaDTO.getDiaSemanaId();

        DiaSemana diaSemana = diaSemanaService.obtenerDiaSemanaPorId(diaSemanaId)
                .orElseThrow(() -> new RuntimeException("Día de la semana no encontrado con ID: " + diaSemanaId));

        Clima clima = new Clima();
        clima.setTipo(climaDTO.getTipo());
        clima.setHoraInicio(climaDTO.getHoraInicio());
        clima.setHoraFin(climaDTO.getHoraFin());
        clima.setDiaSemana(diaSemana);

        Clima nuevoClima = climaService.guardarClima(clima);
        return ResponseEntity.ok(nuevoClima);
    }



    /**
     * Endpoint para obtener todos los días de la semana.
     */
    @GetMapping
    public ResponseEntity<List<DiaSemana>> obtenerTodosLosDias() {
        List<DiaSemana> diasSemana = diaSemanaService.obtenerTodosLosDias();
        return ResponseEntity.ok(diasSemana);
    }

    /**
     * Endpoint para obtener un día de la semana por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiaSemana> obtenerDiaSemana(@PathVariable Long id) {
        return diaSemanaService.obtenerDiaSemanaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
