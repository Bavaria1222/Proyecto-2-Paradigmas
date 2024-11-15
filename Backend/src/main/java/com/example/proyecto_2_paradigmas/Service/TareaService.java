package com.example.proyecto_2_paradigmas.Service;

import com.example.proyecto_2_paradigmas.DTO.DependenciaDTO;
import com.example.proyecto_2_paradigmas.Entity.Dependencia;
import com.example.proyecto_2_paradigmas.Entity.Tarea;
import com.example.proyecto_2_paradigmas.Enum.Estado;
import com.example.proyecto_2_paradigmas.Repository.DependenciaRepository;
import com.example.proyecto_2_paradigmas.Repository.TareaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private DependenciaRepository dependenciaRepository;

    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea agregarDependencias(Tarea tarea, List<DependenciaDTO> dependenciasDTO) {
        for (DependenciaDTO dto : dependenciasDTO) {
            Dependencia dependencia = new Dependencia();
            dependencia.setTarea(tarea);
            dependencia.setIdTareaDependiente(dto.getIdTareaDependiente());
            tarea.getDependencias().add(dependencia);
        }
        return tareaRepository.save(tarea);
    }

    public Optional<Tarea> obtenerPorId(Long id) {
        return tareaRepository.findById(id);
    }

    public List<Tarea> obtenerTodas() {
        return tareaRepository.findAll();
    }

    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    @Transactional
    public Tarea cambiarEstadoTarea(Long tareaId, Estado nuevoEstado) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + tareaId));

        // Verificar si todas las dependencias están completadas
        boolean todasDependenciasCompletadas = tarea.getDependencias().stream()
                .allMatch(dependencia -> {
                    Tarea tareaDependiente = tareaRepository.findById(dependencia.getIdTareaDependiente())
                            .orElseThrow(() -> new RuntimeException("Dependencia no encontrada con ID: " + dependencia.getIdTareaDependiente()));
                    return tareaDependiente.getEstado() == Estado.COMPLETADA;
                });

        if (!todasDependenciasCompletadas) {
            throw new RuntimeException("No se puede cambiar el estado porque una o más dependencias no están completadas.");
        }

        // Cambiar el estado de la tarea
        tarea.setEstado(nuevoEstado);
        return tareaRepository.save(tarea);
    }


}
