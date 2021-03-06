package com.senac.mybarber.controller;

import com.senac.mybarber.model.Agendamento;
import com.senac.mybarber.model.Profissional;
import com.senac.mybarber.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/profissionais"})
public class ProfissionalController {

    @Autowired
    private ProfissionalService service;

    @GetMapping
    public List<Profissional> findAll(){
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Profissional> findById(@PathVariable Long id){
        return service.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"/{id}/agendamentos"})
    public List<Agendamento> findAllByProfissional(@PathVariable Long id) {
        return service.findById(id).get().getAgendamentos();
    }

    @PostMapping
    public Profissional create(@RequestBody Profissional profissional){
        return service.create(profissional);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Profissional> update(@PathVariable("id") Long id, @RequestBody Profissional profissional) {
        return service.update(id, profissional)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(service.delete(id).orElse(false)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value="/{id}/servicos")
    public ResponseEntity<Profissional> associarServico(@PathVariable("id") Long id, @RequestBody RequestAssociacaoServicos request) {
        return service.associarServico(id, request.getServicos())
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/{id}/saloes")
    public ResponseEntity<Profissional> associarSalao(@PathVariable("id") Long id, @RequestBody RequestAssociacaoSaloes request) {
        return service.associarSalao(id, request.getSaloes())
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}

