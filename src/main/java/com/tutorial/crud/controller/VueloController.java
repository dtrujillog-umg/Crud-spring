package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.VueloDto;
import com.tutorial.crud.entity.Vuelo;
import com.tutorial.crud.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vuelo")
@CrossOrigin(origins = "http://localhost:4200")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @GetMapping("/lista")
    public ResponseEntity<List<Vuelo>> list() {
        List<Vuelo> list = vueloService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Vuelo> getById(@PathVariable("id") int id) {
        if (!vueloService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Vuelo vuelo = vueloService.getOne(id).get();
        return new ResponseEntity<>(vuelo, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@Valid @RequestBody VueloDto vueloDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos inválidos"), HttpStatus.BAD_REQUEST);
        Vuelo vuelo = new Vuelo(vueloDto.getNumeroVuelo(), vueloDto.getOrigen(), vueloDto.getDestino(),
                vueloDto.getFechaSalida(), vueloDto.getHoraSalida(), vueloDto.getFechaLlegada(),
                vueloDto.getHoraLlegada());
        vueloService.save(vuelo);
        return new ResponseEntity<>(new Mensaje("Vuelo creado"), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody VueloDto vueloDto,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos inválidos"), HttpStatus.BAD_REQUEST);
        if (!vueloService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Vuelo vuelo = vueloService.getOne(id).get();
        vuelo.setNumeroVuelo(vueloDto.getNumeroVuelo());
        vuelo.setOrigen(vueloDto.getOrigen());
        vuelo.setDestino(vueloDto.getDestino());
        vuelo.setFechaSalida(vueloDto.getFechaSalida());
        vuelo.setHoraSalida(vueloDto.getHoraSalida());
        vuelo.setFechaLlegada(vueloDto.getFechaLlegada());
        vuelo.setHoraLlegada(vueloDto.getHoraLlegada());

        vueloService.save(vuelo);
        return new ResponseEntity<>(new Mensaje("Vuelo actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!vueloService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        vueloService.delete(id);
        return new ResponseEntity<>(new Mensaje("Vuelo eliminado"), HttpStatus.OK);
    }
}
