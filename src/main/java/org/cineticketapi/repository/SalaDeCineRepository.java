package org.cineticketapi.repository;

import org.cineticketapi.model.SalaDeCine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaDeCineRepository extends JpaRepository<SalaDeCine, Long> {
    Optional<SalaDeCine> findByIdSala(Long idSala);
    Optional<SalaDeCine> findByNombre(String nombre);
}
