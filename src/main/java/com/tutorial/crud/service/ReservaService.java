package com.tutorial.crud.service;

import com.tutorial.crud.entity.Reserva;
import com.tutorial.crud.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> getReservaById(int id) {
        return reservaRepository.findById(id);
    }

    public Reserva saveReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteReserva(int id) {
        reservaRepository.deleteById(id);
    }

    public Reserva crearReserva(Reserva reserva) {
        return null;
    }

    public Reserva guardarReserva(Reserva reserva) {
        Reserva o = null;
        return o;
    }

    // Puedes agregar más métodos según tus necesidades

}
