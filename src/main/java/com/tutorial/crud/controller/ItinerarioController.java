package com.tutorial.crud.controller;

import com.tutorial.crud.dto.ItinerarioDto;
import com.tutorial.crud.entity.Itinerario;
import com.tutorial.crud.entity.Reserva;
import com.tutorial.crud.entity.Usuario;
import com.tutorial.crud.service.ItinerarioService;
import com.tutorial.crud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itinerarios")
public class ItinerarioController {
    private final ItinerarioService itinerarioService;
    private final UsuarioService usuarioService;

    @Autowired
    public ItinerarioController(ItinerarioService itinerarioService, UsuarioService usuarioService) {
        this.itinerarioService = itinerarioService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Itinerario>> getAllItinerarios() {
        List<Itinerario> itinerarios = itinerarioService.getAllItinerarios();
        return new ResponseEntity<>(itinerarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itinerario> getItinerarioById(@PathVariable int id) {
        Optional<Itinerario> optionalItinerario = itinerarioService.getItinerarioById(id);
        if (optionalItinerario.isPresent()) {
            Itinerario itinerario = optionalItinerario.get();
            return new ResponseEntity<>(itinerario, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Itinerario> createItinerario(@RequestBody ItinerarioDto itinerarioDto) {
        Optional<Usuario> optionalUsuario = usuarioService.getUsuarioById(itinerarioDto.getIdUsuario());
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            // Convertir la cadena de texto de la fecha a un objeto java.sql.Date
            java.sql.Date fechaCreacion = java.sql.Date.valueOf(itinerarioDto.getFechaCreacion());

            Itinerario itinerario = itinerarioService.createItinerario(itinerarioDto, usuario, fechaCreacion);
            return new ResponseEntity<>(itinerario, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItinerario(@PathVariable int id) {
        itinerarioService.deleteItinerario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/reservas")
    public ResponseEntity<Void> agregarReserva(@PathVariable int id, @RequestBody Reserva reserva) {
        itinerarioService.agregarReserva(id, reserva);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/reservas")
    public ResponseEntity<Void> eliminarReserva(@PathVariable int id, @RequestBody Reserva reserva) {
        itinerarioService.eliminarReserva(id, reserva);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
