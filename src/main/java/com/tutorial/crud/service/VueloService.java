package com.tutorial.crud.service;

import com.tutorial.crud.entity.Vuelo;
import com.tutorial.crud.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    public List<Vuelo> list() {
        return vueloRepository.findAll();
    }

    public Optional<Vuelo> getOne(int id) {
        return vueloRepository.findById(id);
    }

    public void save(Vuelo vuelo) {
        vueloRepository.save(vuelo);
    }

    public void delete(int id) {
        vueloRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return vueloRepository.existsById(id);
    }
}
