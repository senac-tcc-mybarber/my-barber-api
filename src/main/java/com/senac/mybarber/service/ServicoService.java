package com.senac.mybarber.service;

import java.util.List;
import java.util.Optional;

import com.senac.mybarber.model.Servico;
import com.senac.mybarber.repository.ServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {
    
    @Autowired
    private ServicoRepository servicorepository;

    public List<Servico> findAll(){
        return servicorepository.findAll();
    }

    public Optional<Servico> findById(Long id){
        return servicorepository.findById(id);
    }
}