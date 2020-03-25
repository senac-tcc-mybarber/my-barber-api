package com.senac.mybarber.service;

import com.senac.mybarber.model.Agendamento;
import com.senac.mybarber.model.StatusAgendamento;
import com.senac.mybarber.model.TipoEntidadeOperacaoAgendamento;
import com.senac.mybarber.model.TipoOperacaoAgendamento;
import com.senac.mybarber.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.senac.mybarber.model.StatusAgendamento.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository repository) {
        agendamentoRepository = repository;
	}

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


    public Optional<Agendamento> checkInProfissional(Long id) {
        return agendamentoRepository.findById(id)
                .map( record -> {
                    record.checkInProfissional();
                    return agendamentoRepository.save(record);
            }
        );
    }

    public Optional<Agendamento> checkInCliente(Long id) {
        return agendamentoRepository.findById(id)
                .map( record -> {
                    record.checkInCliente();
                    return agendamentoRepository.save(record);
                }
        );
    }

}
