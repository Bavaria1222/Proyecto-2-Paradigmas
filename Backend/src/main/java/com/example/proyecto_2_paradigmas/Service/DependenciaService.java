package com.example.proyecto_2_paradigmas.Service;

import com.example.proyecto_2_paradigmas.Entity.Dependencia;
import com.example.proyecto_2_paradigmas.Repository.DependenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependenciaService {

    @Autowired
    private DependenciaRepository dependenciaRepository;

    public Dependencia guardarDependencia(Dependencia dependencia) {
        return dependenciaRepository.save(dependencia);
    }

    public List<Dependencia> guardarDependencias(List<Dependencia> dependencias) {
        return dependenciaRepository.saveAll(dependencias);
    }

    public Optional<Dependencia> obtenerPorId(Long id) {
        return dependenciaRepository.findById(id);
    }

    public List<Dependencia> obtenerTodas() {
        return dependenciaRepository.findAll();
    }

    public void eliminarDependencia(Long id) {
        dependenciaRepository.deleteById(id);
    }
}
