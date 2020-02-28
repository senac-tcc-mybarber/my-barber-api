package com.senac.mybarber.service;

import com.senac.mybarber.model.Agendamento;
import com.senac.mybarber.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> findAll(){
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> findById(Long id){
        return agendamentoRepository.findById(id);
    }

    public List<Agendamento> findAllById(Iterable<Long> servicos) {
        return agendamentoRepository.findAllById(servicos);
    }

    public Agendamento create(Agendamento agendamento){
        return agendamentoRepository.save(agendamento);
    }
}
