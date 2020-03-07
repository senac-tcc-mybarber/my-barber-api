package com.senac.mybarber.controller;

import com.senac.mybarber.model.Servico;
import com.senac.mybarber.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/servicos"})
public class ServicoController {

    @Autowired
    private ServicoService servicoservice;

    @GetMapping
    public List<Servico> findAll(){
        return servicoservice.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Servico> findById(@PathVariable Long id){
        return servicoservice.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
}