package com.senac.mybarber.configuration;

import com.senac.mybarber.model.*;
import com.senac.mybarber.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


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

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Bean
    InitializingBean carregaDados() {
        return () -> {
            List<Servico> servicos = Arrays.asList(
                    new Servico(1l, "Depilacao"),
                    new Servico(2l, "Manicure"),
                    new Servico(3l, "Pedicure"),
                    new Servico(5l, "Pintura de Cabelo"),
                    new Servico(6l, "Escova Progressiva"),
                    new Servico(7l, "Barba")
            );

            servicoRepository.saveAll(servicos);

            List<Salao> saloes = Arrays.asList(
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
            );

            salaoRepository.saveAll(saloes);

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

            Agendamento agendamento = new Agendamento();
            agendamento.setId(1l);
            agendamento.setCliente(cliente);
            agendamento.setProfissional(profissional);
            agendamento.setSalao(saloes.get(0));
            agendamento.setServico(servicos.get(0));
            agendamento.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:00"));
            agendamento.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:30"));

            //agendamento.setIdCliente(cliente.getId());
            //agendamento.setIdProfissional(profissional.getId());
            //agendamento.setIdSalao(saloes.get(0).getId());
            //agendamento.setIdServico(servicos.get(0).getId());
            agendamentoRepository.save(agendamento);
        };
    }
}
