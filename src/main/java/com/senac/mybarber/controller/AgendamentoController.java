package com.senac.mybarber.controller;

import com.senac.mybarber.model.Agendamento;
import com.senac.mybarber.model.StatusAgendamento;
import com.senac.mybarber.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity findById(@PathVariable final Long id) {
        return agendamentoService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agendamento create(@RequestBody final Agendamento agendamento){
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        return agendamentoService.create(agendamento);
    }

    @PutMapping
    public Agendamento update(@RequestBody final Agendamento agendamento){
        return agendamentoService.update(agendamento);
    }

    @PutMapping(path = {"/{id}/checkin-profissional"})
    public ResponseEntity checkinProfissional(@PathVariable("id") final Long id) {
        return agendamentoService.checkInProfissional(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = {"/{id}/checkin-cliente"})
    public ResponseEntity checkinCliente(@PathVariable("id") final Long id) {
        return agendamentoService.checkInCliente(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = {"/{id}/finalizacao"})
    public ResponseEntity checkoutCliente(@RequestBody final Agendamento agendamento) {
        return null;
    }
}