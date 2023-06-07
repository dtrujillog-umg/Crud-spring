package com.tutorial.crud.service;

import com.tutorial.crud.dto.UsuarioDto;
import com.tutorial.crud.entity.Usuario;
import com.tutorial.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getOne(int id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> getByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public Optional<Usuario> getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void save(Usuario usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto.getNombre(), usuarioDto.getEmail(), usuarioDto.getPassword());
        usuario.setTipoUsuario(usuarioDto.getTipoUsuario());
        usuarioRepository.save(usuario);
    }


    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return usuarioRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return usuarioRepository.existsByNombre(nombre);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Optional<Usuario> getUsuarioById(int idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public void update(int id, UsuarioDto usuarioDto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioDto.getNombre());
            usuario.setEmail(usuarioDto.getEmail());
            usuario.setPassword(usuarioDto.getPassword());
            usuario.setTipoUsuario(usuarioDto.getTipoUsuario());
            usuarioRepository.save(usuario);
        }
    }


    public Usuario autenticarUsuario(String nombreUsuario, String contrasena) {
        return null;
    }
}
