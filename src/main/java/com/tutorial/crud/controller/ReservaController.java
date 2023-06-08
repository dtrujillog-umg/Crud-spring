package com.tutorial.crud.controller;

import com.tutorial.crud.dto.ReservaDto;
import com.tutorial.crud.entity.Reserva;
import com.tutorial.crud.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable int id) {
        Optional<Reserva> reserva = reservaService.getReservaById(id);
        return reserva.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaDto reservaDto) {
        // Crear una nueva instancia de Reserva
        Reserva reserva = new Reserva();

        // Asignar los valores del DTO a la reserva
        reserva.setUsuario(reservaDto.getUsuario());
        reserva.setVuelo(reservaDto.getVuelo());
        reserva.setFechaReserva(Date.valueOf(reservaDto.getFechaReserva()));

        // Convertir la cadena de la hora en un objeto java.sql.Time
        Time horaReserva = Time.valueOf(reservaDto.getHoraReserva());

        // Asignar el objeto java.sql.Time a la reserva
        reserva.setHoraReserva(horaReserva);

        // Guardar la reserva en la base de datos
        Reserva reservaGuardada = reservaService.guardarReserva(reserva);

        return new ResponseEntity<>(reservaGuardada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable int id, @RequestBody ReservaDto reservaDto) {
        Optional<Reserva> existingReserva = reservaService.getReservaById(id);
        if (existingReserva.isPresent()) {
            Reserva reserva = existingReserva.get();
            reserva.setUsuario(reservaDto.getUsuario());
            reserva.setId(reservaDto.getVuelo().getId());
            reserva.setFechaReserva(new Date(reservaDto.getParsedDate().getTime()));
            reserva.setHoraReserva(reservaDto.getHoraReserva());

            Reserva updatedReserva = reservaService.saveReserva(reserva);
            return new ResponseEntity<>(updatedReserva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable int id) {
        reservaService.deleteReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
