package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    // Puedes agregar métodos adicionales personalizados si es necesario
    // Aquí puedes definir consultas personalizadas o métodos para operaciones específicas en la entidad "Reserva"
}
