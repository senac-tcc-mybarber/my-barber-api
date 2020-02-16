package com.senac.mybarber.controller;

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
    public List findAll(){
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id){
        return service.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Profissional create(@RequestBody Profissional profissional){
        return service.create(profissional);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Profissional profissional) {
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
    public ResponseEntity associarServico(@PathVariable("id") Long id, @RequestBody RequestAssociacaoServicos request) {
        return service.associarServico(id, request.getServicos())
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/{id}/saloes")
    public ResponseEntity associarSalao(@PathVariable("id") Long id, @RequestBody RequestAssociacaoSaloes request) {
        return service.associarSalao(id, request.getSaloes())
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}

