package com.senac.mybarber.controller;

import java.util.List;

import com.senac.mybarber.model.Agendamento;
import com.senac.mybarber.model.TipoEntidadeOperacaoAgendamento;
import com.senac.mybarber.model.TipoOperacaoAgendamento;
import com.senac.mybarber.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List findAll(){
        return agendamentoService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id) {
        return agendamentoService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agendamento create(@RequestBody Agendamento agendamento){
        return agendamentoService.create(agendamento);
    }

    @PutMapping(path = {"/checkin/profissional"})
    public ResponseEntity checkinProfissional(@RequestBody Agendamento agendamento) {
        return agendamentoService.update(agendamento,
                TipoEntidadeOperacaoAgendamento.PROFISSIONAL,
                TipoOperacaoAgendamento.CHECKIN)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = {"/checkin/cliente"})
    public ResponseEntity checkinCliente(@RequestBody Agendamento agendamento) {
        return agendamentoService.update(agendamento,
                TipoEntidadeOperacaoAgendamento.CLIENTE,
                TipoOperacaoAgendamento.CHECKIN)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = {"/checkout/profissional"})
    public ResponseEntity checkoutProfissional(@RequestBody Agendamento agendamento) {
        return agendamentoService.update(agendamento,
                TipoEntidadeOperacaoAgendamento.PROFISSIONAL,
                TipoOperacaoAgendamento.CHECKOUT)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = {"/checkout/cliente"})
    public ResponseEntity checkoutCliente(@RequestBody Agendamento agendamento) {
        return agendamentoService.update(agendamento,
                TipoEntidadeOperacaoAgendamento.CLIENTE,
                TipoOperacaoAgendamento.CHECKOUT)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}