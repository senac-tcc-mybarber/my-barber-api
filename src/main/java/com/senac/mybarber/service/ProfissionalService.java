package com.senac.mybarber.service;

import com.senac.mybarber.model.Profissional;
import com.senac.mybarber.repository.ProfissionalRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    private ProfissionalRepository repository;
    private ServicoService servicoService;
    private SalaoService salaoService;
    private PasswordEncoder passwordEncoder;


    public ProfissionalService(ProfissionalRepository repository, ServicoService servicoService, SalaoService salaoService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.servicoService = servicoService;
        this.salaoService = salaoService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Profissional> findAll() {
        return repository.findAll();
    }

    public Optional<Profissional> findById(Long id) {
        return repository.findById(id);
    }

    public Profissional create(Profissional profissional) {
        String encodedPassword = passwordEncoder.encode(profissional.getSenha());
        profissional.setSenha(encodedPassword);

        return repository.save(profissional);
    }

    public Optional<Profissional> update(Long id, Profissional profissional) {
        return repository.findById(id)
                .map(record -> {
                    record.setNome(profissional.getNome());
                    record.setEmail(profissional.getEmail());
                    record.setTelefone(profissional.getTelefone());
                    record.setSenha(profissional.getSenha());
                    return repository.save(record);
                });
    }

    public Optional<Boolean> delete(Long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.delete(record);
                    return true;
                });
    }

    public Optional<Profissional> associarServico(Long id, List<Long> servicos) {
        return repository.findById(id)
                .map(record -> {

                    Collection associacao = servicoService.findAllById(servicos);

                    record.setServicos(new HashSet<>( associacao));
                    return repository.save(record);
                });
    }

    public Optional<Profissional> associarSalao(Long id, List<Long> saloes) {
        return repository.findById(id)
                .map(record -> {
                    Collection associacao = salaoService.findAllById(saloes);

                    record.setSaloes(new HashSet<>(associacao));
                    return repository.save(record);
                });
    }
}
