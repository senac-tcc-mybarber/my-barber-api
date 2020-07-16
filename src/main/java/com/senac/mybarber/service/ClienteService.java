package com.senac.mybarber.service;

import com.senac.mybarber.model.Cliente;
import com.senac.mybarber.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

@Service
public class ClienteService {

    private ClienteRepository clienterepository;
    private PasswordEncoder passwordEncoder;


    public ClienteService(ClienteRepository clienterepository, PasswordEncoder passwordEncoder) {
        this.clienterepository = clienterepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Cliente> findAll(){
        return clienterepository.findAll();
    }

    public Optional<Cliente> findById(Long id){
        return clienterepository.findById(id);
    }

    public Cliente create(Cliente cliente){
        String encodedPassword = passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(encodedPassword);

        return clienterepository.save(cliente);
    }

    public Optional<Cliente> update(Long id, Cliente cliente){
        return clienterepository.findById(id)
                .map(record -> {
                    record.setNome(cliente.getNome());
                    record.setEmail(cliente.getEmail());
                    record.setTelefone(cliente.getTelefone());
                    if (StringUtils.isNotBlank(cliente.getSenha())) {
                        record.setSenha(passwordEncoder.encode(cliente.getSenha()));
                    }
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
