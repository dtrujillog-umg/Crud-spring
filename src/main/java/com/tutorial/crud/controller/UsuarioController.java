package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.UsuarioDto;
import com.tutorial.crud.entity.Usuario;
import com.tutorial.crud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> list = usuarioService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id) {

            if (!usuarioService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.getOne(id).get();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos inválidos"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombre(usuarioDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre ya está en uso"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(usuarioDto.getEmail()))
            return new ResponseEntity<>(new Mensaje("El email ya está en uso"), HttpStatus.BAD_REQUEST);
        UsuarioDto usuario = new UsuarioDto();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setTipoUsuario(usuarioDto.getTipoUsuario()); // Agregar esta línea
        usuarioService.save(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario creado"), HttpStatus.CREATED);
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody UsuarioDto usuarioDto,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos inválidos"), HttpStatus.BAD_REQUEST);
        if (!usuarioService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (usuarioService.existsByNombre(usuarioDto.getNombre())
                && usuarioService.getByNombre(usuarioDto.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("El nombre ya está en uso"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(usuarioDto.getEmail())
                && usuarioService.getByEmail(usuarioDto.getEmail()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("El email ya está en uso"), HttpStatus.BAD_REQUEST);

        Optional<Usuario> usuarioOptional = usuarioService.getOne(id);
        if (!usuarioOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Usuario usuario = usuarioOptional.get();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setTipoUsuario(usuarioDto.getTipoUsuario());

        UsuarioDto updatedUsuario = new UsuarioDto(usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), usuario.getTipoUsuario());
        usuarioService.save(updatedUsuario);

        return new ResponseEntity<>(new Mensaje("Usuario actualizado"), HttpStatus.OK);
    }




    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!usuarioService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        usuarioService.delete(id);
        return new ResponseEntity<>(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos inválidos"), HttpStatus.BAD_REQUEST);

        Optional<Usuario> usuarioOptional = usuarioService.getByEmail(usuarioDto.getEmail());
        if (!usuarioOptional.isPresent())
            return new ResponseEntity<>(new Mensaje("Correo electrónico no encontrado"), HttpStatus.UNAUTHORIZED);

        Usuario usuario = usuarioOptional.get();

        // Verificar la contraseña del usuario
        if (!usuario.getPassword().equals(usuarioDto.getPassword()))
            return new ResponseEntity<>(new Mensaje("Contraseña incorrecta"), HttpStatus.UNAUTHORIZED);

        // Realizar la lógica de autenticación y obtener los datos del usuario autenticado
        // ...

        return new ResponseEntity<>(new Mensaje("Inicio de sesión exitoso"), HttpStatus.OK);
    }




}
