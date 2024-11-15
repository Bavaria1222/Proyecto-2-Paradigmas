package com.example.proyecto_2_paradigmas.Repository;

import com.example.proyecto_2_paradigmas.Entity.Clima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimaRepository extends JpaRepository<Clima, Long> {

}
