package com.senac.mybarber.service;

import com.senac.mybarber.model.Salao;
import com.senac.mybarber.repository.SalaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaoService {

    @Autowired
    private SalaoRepository salaoRepository;

    public List<Salao> findAllById(Iterable<Long> saloes) {
        return salaoRepository.findAllById(saloes);
    }


    public Optional<Salao> findById(Long id) {
        return salaoRepository.findById(id);
    }

    public List findAll() {
        return salaoRepository.findAll();
    }
}
