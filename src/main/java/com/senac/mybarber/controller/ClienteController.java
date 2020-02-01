package com.senac.mybarber.controller;

import com.senac.mybarber.model.Cliente;
import com.senac.mybarber.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/clientes"})
public class ClienteController {

    @Autowired
    private ClienteService clienteservice;

    @GetMapping
    public List findAll(){
        return clienteservice.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id){
        return clienteservice.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente){
        return clienteservice.create(cliente);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        return clienteservice.update(id, cliente)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(clienteservice.delete(id).orElse(false)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
