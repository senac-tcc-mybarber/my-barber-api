package com.senac.mybarber.controller;

import com.senac.mybarber.model.Agendamento;
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
    public List<Agendamento>findAll(){
        return agendamentoService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Agendamento> findById(@PathVariable final Long id) {
        return agendamentoService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agendamento create(@RequestBody final Agendamento agendamento){
        return agendamentoService.create(agendamento);
    }

    @PutMapping(path = {"/{id}/checkin-profissional"})
    public ResponseEntity<Agendamento> checkinProfissional(@PathVariable("id") final Long id) {
        return agendamentoService.checkInProfissional(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = {"/{id}/checkin-cliente"})
    public ResponseEntity<Agendamento> checkinCliente(@PathVariable("id") final Long id) {
        return agendamentoService.checkInCliente(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = {"/{id}/concluir"})
    public ResponseEntity<Agendamento> concluirAtendimento(@PathVariable("id") final Long id) {
        return agendamentoService.concluirAtendimento(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}