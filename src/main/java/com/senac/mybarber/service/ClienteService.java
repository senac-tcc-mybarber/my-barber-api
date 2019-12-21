package com.senac.mybarber.service;

import com.senac.mybarber.model.Cliente;
import com.senac.mybarber.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienterepository;

    public List<Cliente> findAll(){
        return clienterepository.findAll();
    }

    public Optional<Cliente> findById(Long id){
        return clienterepository.findById(id);
    }

    public Cliente create(Cliente cliente){
        return clienterepository.save(cliente);
    }

    public Optional<Cliente> update(Long id, Cliente cliente){
        return clienterepository.findById(id)
                .map(record -> {
                    record.setNome(cliente.getNome());
                    record.setEmail(cliente.getEmail());
                    record.setTelefone(cliente.getTelefone());
                    record.setSenha(cliente.getSenha());
                    return clienterepository.save(record);
                });
    }

    public Optional<Boolean> delete(Long id){
        return clienterepository.findById(id)
                .map(record -> {
                    clienterepository.delete(record);
                    return true;
                });
    }
}
