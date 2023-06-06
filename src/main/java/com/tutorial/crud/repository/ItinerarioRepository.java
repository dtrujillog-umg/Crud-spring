package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario, Integer> {
    // Aquí puedes agregar métodos personalizados de consulta si los necesitas
}
