package com.tutorial.crud.service;

import com.tutorial.crud.dto.ItinerarioDto;
import com.tutorial.crud.entity.Itinerario;
import com.tutorial.crud.entity.Reserva;
import com.tutorial.crud.entity.Usuario;
import com.tutorial.crud.repository.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItinerarioService {
    private final ItinerarioRepository itinerarioRepository;

    @Autowired
    public ItinerarioService(ItinerarioRepository itinerarioRepository) {
        this.itinerarioRepository = itinerarioRepository;
    }

    public List<Itinerario> getAllItinerarios() {
        return itinerarioRepository.findAll();
    }

    public Optional<Itinerario> getItinerarioById(int id) {
        return itinerarioRepository.findById(id);
    }

    public Itinerario createItinerario(ItinerarioDto itinerarioDto, Usuario usuario, java.sql.Date fechaCreacion) {
        Itinerario itinerario = new Itinerario(usuario, fechaCreacion);
        return itinerarioRepository.save(itinerario);
    }



    public void deleteItinerario(int id) {
        itinerarioRepository.deleteById(id);
    }

    public void agregarReserva(int idItinerario, Reserva reserva) {
        Optional<Itinerario> optionalItinerario = itinerarioRepository.findById(idItinerario);
        optionalItinerario.ifPresent(itinerario -> {
            itinerario.agregarReserva(reserva);
            itinerarioRepository.save(itinerario);
        });
    }

    public void eliminarReserva(int idItinerario, Reserva reserva) {
        Optional<Itinerario> optionalItinerario = itinerarioRepository.findById(idItinerario);
        optionalItinerario.ifPresent(itinerario -> {
            itinerario.eliminarReserva(reserva);
            itinerarioRepository.save(itinerario);
        });
    }
}
