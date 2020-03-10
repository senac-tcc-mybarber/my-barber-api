package com.senac.mybarber.configuration;

import com.senac.mybarber.model.*;
import com.senac.mybarber.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
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

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Bean
        InitializingBean carregaDados() {
                return () -> {
                        long id = 1;

                        final Servico s1 = new Servico(1l, "Depilacao", 10l);
                        final Servico s2 = new Servico(2l, "Manicure", 20l);
                        final Servico s3 = new Servico(3l, "Pedicure", 30l);
                        final Servico s4 = new Servico(5l, "Pintura de Cabelo", 40l);
                        final Servico s5 = new Servico(6l, "Escova Progressiva", 50l);
                        final Servico s6 = new Servico(7l, "Barba", 60l);

                        final Salao sa1 = new Salao(1l, "Salao do Shopping", "62.408.761/0001-34", "Barra da Tijuca",
                                        "Av das Americas 96975", "1", "2");
                        final Salao sa2 = new Salao(2l, "Salao do Centro", "45.298.705/0001-75", "Centro",
                                        "Rua da Quitanda 35", "1", "2");
                        final Salao sa3 = new Salao(3l, "Salao dos Turistas", "33.366.825/0001-83", "Copacabana",
                                        "Av Atlantica 342", "1", "2");
                        final Salao sa4 = new Salao(4l, "Salao da Lapa", "10.162.885/0001-78", "Centro",
                                        "Rua da Lapa 1", "1", "2");

                        final Cliente cli1 = new Cliente();
                        cli1.setId(id++);
                        cli1.setUsername("username1");
                        cli1.setNome("Jose da Silva");
                        cli1.setEmail("jose@gmail.com");
                        cli1.setTelefone("2199219384723948");
                        cli1.setSenha(passwordEncoder.encode("1234"));

                        final Cliente cli2 = new Cliente();
                        cli2.setId(id++);
                        cli2.setUsername("username2");
                        cli2.setNome("Maria da Silva");
                        cli2.setEmail("maria@gmail.com");
                        cli2.setTelefone("2199219384723948");
                        cli2.setSenha(passwordEncoder.encode("1234"));

                        final Cliente cli3 = new Cliente();
                        cli3.setId(id++);
                        cli3.setUsername("username3");
                        cli3.setNome("Luiz da Silva");
                        cli3.setEmail("luiz@gmail.com");
                        cli3.setTelefone("2199219384723948");
                        cli3.setSenha(passwordEncoder.encode("1234"));

                        final Profissional p1 = new Profissional();
                        p1.setId(id++);
                        p1.setUsername("username4");
                        p1.setNome("Alucard da Silva");
                        p1.setEmail("alucard@gmail.com");
                        p1.setTelefone("123456789");
                        p1.setSenha(passwordEncoder.encode("1234"));
                        final Set<Servico> setServico1 = new HashSet();
                        setServico1.add(s1);
                        setServico1.add(s2);
                        p1.setServicos(setServico1);
                        final Set<Salao> setSalao1 = new HashSet();
                        setSalao1.add(sa1);
                        setSalao1.add(sa2);
                        p1.setSaloes(setSalao1);

                        final Profissional p2 = new Profissional();
                        p2.setId(id++);
                        p2.setUsername("username5");
                        p2.setNome("Jose Maria");
                        p2.setEmail("josemaria@gmail.com");
                        p2.setTelefone("123456789");
                        p2.setSenha(passwordEncoder.encode("1234"));
                        final Set<Servico> setServico2 = new HashSet();
                        setServico2.add(s3);
                        setServico2.add(s4);
                        p2.setServicos(setServico2);
                        final Set<Salao> setSalao2 = new HashSet();
                        setSalao2.add(sa2);
                        setSalao2.add(sa3);
                        p2.setSaloes(setSalao2);

                        final Profissional p3 = new Profissional();
                        p3.setId(id++);
                        p3.setUsername("username6");
                        p3.setNome("Alphonse Eric");
                        p3.setEmail("alphonse@gmail.com");
                        p3.setTelefone("123456789");
                        p3.setSenha(passwordEncoder.encode("1234"));
                        final Set<Servico> setServico3 = new HashSet();
                        setServico3.add(s5);
                        setServico3.add(s1);
                        p3.setServicos(setServico3);
                        final Set<Salao> setSalao3 = new HashSet();
                        setSalao3.add(sa3);
                        setSalao3.add(sa4);
                        p3.setSaloes(setSalao3);

                        final Profissional p4 = new Profissional();
                        p4.setId(id++);
                        p4.setUsername("username7");
                        p4.setNome("Dr. Mundo");
                        p4.setEmail("mundo@gmail.com");
                        p4.setTelefone("123456789");
                        p4.setSenha(passwordEncoder.encode("1234"));
                        final Set<Servico> setServico4 = new HashSet();
                        setServico4.add(s4);
                        setServico4.add(s2);
                        p4.setServicos(setServico4);
                        final Set<Salao> setSalao4 = new HashSet();
                        setSalao4.add(sa4);
                        setSalao4.add(sa1);
                        p4.setSaloes(setSalao4);

                        final List<Servico> servicos = Arrays.asList(s1, s2, s3, s4, s5, s6);
                        final List<Salao> saloes = Arrays.asList(sa1, sa2, sa3, sa4);
                        final List<Cliente> clientes = Arrays.asList(cli1, cli2, cli3);
                        final List<Profissional> profissionais = Arrays.asList(p1, p2, p3, p4);

                        final Agendamento ag1 = new Agendamento();
                        ag1.setId(1l);
                        ag1.setCliente(clientes.get(1));
                        ag1.setProfissional(profissionais.get(1));
                        ag1.setSalao(saloes.get(1));
                        ag1.setServico(servicos.get(1));
                        ag1.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("13/03/2020 16:00"));
                        ag1.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("13/03/2020 16:30"));
                        ag1.setStatus(StatusAgendamento.AGENDADO);

                        final Agendamento ag2 = new Agendamento();
                        ag2.setId(2l);
                        ag2.setCliente(clientes.get(2));
                        ag2.setProfissional(profissionais.get(2));
                        ag2.setSalao(saloes.get(2));
                        ag2.setServico(servicos.get(2));
                        ag2.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:00"));
                        ag2.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:30"));
                        ag2.setStatus(StatusAgendamento.AGUARDANDO_CLIENTE);

                        final Agendamento ag3 = new Agendamento();
                        ag3.setId(3l);
                        ag3.setCliente(clientes.get(0));
                        ag3.setProfissional(profissionais.get(3));
                        ag3.setSalao(saloes.get(3));
                        ag3.setServico(servicos.get(3));
                        ag3.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:00"));
                        ag3.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:30"));
                        ag3.setStatus(StatusAgendamento.AGUARDANDO_PROFISSIONAL);


                        final Agendamento ag4 = new Agendamento();
                        ag4.setId(4l);
                        ag4.setCliente(clientes.get(0));
                        ag4.setProfissional(profissionais.get(2));
                        ag4.setSalao(saloes.get(1));
                        ag4.setServico(servicos.get(1));
                        ag4.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 17:00"));
                        ag4.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 18:30"));
                        ag4.setStatus(StatusAgendamento.CANCELADO);


                        final Agendamento ag5 = new Agendamento();
                        ag5.setId(5l);
                        ag5.setCliente(clientes.get(0));
                        ag5.setProfissional(profissionais.get(3));
                        ag5.setSalao(saloes.get(3));
                        ag5.setServico(servicos.get(1));
                        ag5.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/07/2020 16:00"));
                        ag5.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/07/2020 16:30"));
                        ag5.setStatus(StatusAgendamento.CONCLUIDO);


                        final Agendamento ag6 = new Agendamento();
                        ag6.setId(6l);
                        ag6.setCliente(clientes.get(0));
                        ag6.setProfissional(profissionais.get(1));
                        ag6.setSalao(saloes.get(2));
                        ag6.setServico(servicos.get(2));
                        ag6.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/020/2020 16:30"));
                        ag6.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/20/2020 19:30"));
                        ag6.setStatus(StatusAgendamento.EM_ANDAMENTO);


                        final List<Agendamento> agendamentos = Arrays.asList(ag1, ag2, ag3, ag4, ag5, ag6);

                         servicoRepository.saveAll(servicos);
                         salaoRepository.saveAll(saloes);
                         clienteRepository.saveAll(clientes);
                         profissionalRepository.saveAll(profissionais);
                         agendamentoRepository.saveAll(agendamentos);
                };
        }
}
