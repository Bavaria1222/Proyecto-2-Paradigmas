package com.example.proyecto_2_paradigmas.Service;

import com.example.proyecto_2_paradigmas.Entity.Clima;
import com.example.proyecto_2_paradigmas.Repository.ClimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClimaService {
    @Autowired
    private ClimaRepository climaRepository;

    public List<Clima> obtenerTodos() {
        return climaRepository.findAll();
    }

    public Optional<Clima> obtenerPorId(Long id) {
        return climaRepository.findById(id);
    }

    public Clima guardarClima(Clima clima) {
        return climaRepository.save(clima);
    }

    public void eliminarClima(Long id) {
        climaRepository.deleteById(id);
    }
}
