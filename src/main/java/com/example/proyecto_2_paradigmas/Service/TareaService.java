package com.example.proyecto_2_paradigmas.Service;

import com.example.proyecto_2_paradigmas.DTO.DependenciaDTO;
import com.example.proyecto_2_paradigmas.Entity.Dependencia;
import com.example.proyecto_2_paradigmas.Entity.Tarea;
import com.example.proyecto_2_paradigmas.Repository.DependenciaRepository;
import com.example.proyecto_2_paradigmas.Repository.TareaRepository;
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
}
