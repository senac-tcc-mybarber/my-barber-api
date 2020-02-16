package com.senac.mybarber.configuration;

import com.senac.mybarber.model.Cliente;
import com.senac.mybarber.model.Profissional;
import com.senac.mybarber.model.Salao;
import com.senac.mybarber.model.Servico;
import com.senac.mybarber.repository.ClienteRepository;
import com.senac.mybarber.repository.ProfissionalRepository;
import com.senac.mybarber.repository.SalaoRepository;
import com.senac.mybarber.repository.ServicoRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class DataInitialization {

    @Autowired
    private SalaoRepository salaoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @Bean
    InitializingBean carregaDados() {
        return () -> {
            servicoRepository.saveAll(
                    Arrays.asList(
                            new Servico(1l, "Depilacao"),
                            new Servico(2l, "Manicure"),
                            new Servico(3l, "Pedicure"),
                            new Servico(5l, "Pintura de Cabelo"),
                            new Servico(6l, "Escova Progressiva"),
                            new Servico(7l, "Barba")
                    )
            );

            salaoRepository.saveAll(
                    Arrays.asList(
                            new Salao(1l,
                                    "Salao do Shopping",
                                    "62.408.761/0001-34",
                                    "Barra da Tijuca",
                                    "Av das Americas 96975",
                                    1,
                                    2),
                            new Salao(2l,
                                    "Salao do Centro",
                                    "45.298.705/0001-75",
                                    "Centro",
                                    "Rua da Quitanda 35",
                                    1,
                                    2),
                            new Salao(3l,
                                    "Salao dos Turistas",
                                    "33.366.825/0001-83",
                                    "Copacabana",
                                    "Av Atlantica 342",
                                    1,
                                    2)
                    )
            );

            Cliente cliente = new Cliente();
            cliente.setId(1l);
            cliente.setNome("Jose da Silva");
            cliente.setEmail("zeh@gmail.com");
            cliente.setTelefone("2199219384723948");
            cliente.setSenha("senha");
            clienteRepository.save(cliente);


            Profissional profissional = new Profissional();
            profissional.setId(1l);
            profissional.setNome("Joao de Sousa");
            profissional.setEmail("jo@gmail.com");
            profissional.setTelefone("2123419384723948");
            profissional.setSenha("senha");
            profissionalRepository.save(profissional);
        };
    }
}
