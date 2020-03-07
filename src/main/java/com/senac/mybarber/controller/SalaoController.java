package com.senac.mybarber.controller;

import com.senac.mybarber.model.Salao;
import com.senac.mybarber.service.SalaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/saloes"})
public class SalaoController {

    @Autowired
    private SalaoService salaoService;

    @GetMapping
    public List<Salao> findAll(){
        return salaoService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Salao> findById(@PathVariable Long id){
        return salaoService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}