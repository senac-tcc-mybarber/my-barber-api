package com.senac.mybarber.service;

import com.senac.mybarber.model.Agendamento;
import com.senac.mybarber.model.TipoEntidadeOperacaoAgendamento;
import com.senac.mybarber.model.TipoOperacaoAgendamento;
import com.senac.mybarber.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Optional<Agendamento> update(Agendamento agendamento,
                                        TipoEntidadeOperacaoAgendamento entidadeOperacaoAgendamento,
                                        TipoOperacaoAgendamento operacaoAgendamento) {

        Optional<Agendamento> forUpdate = agendamentoRepository.findById(agendamento.getId());
        Optional<Agendamento> updated = null;

        switch (entidadeOperacaoAgendamento) {
            case CLIENTE:
                if (operacaoAgendamento == TipoOperacaoAgendamento.CHECKIN) {
                    updated = forUpdate.map(record -> {
                        record.setCheckInCliente(new Date());
                        return agendamentoRepository.save(record);
                    });
                } else {
                    updated = forUpdate.map(record -> {
                        record.setCheckOutCliente(new Date());
                        return agendamentoRepository.save(record);
                    });
                }
                break;
            case PROFISSIONAL:
                if (operacaoAgendamento == TipoOperacaoAgendamento.CHECKIN) {
                    updated = forUpdate.map(record -> {
                        record.setCheckInProfissional(new Date());
                        return agendamentoRepository.save(record);
                    });
                } else {
                    updated = forUpdate.map(record -> {
                        record.setCheckOutProfissional(new Date());
                        return agendamentoRepository.save(record);
                    });
                }
        }

        return updated;
    }
}
