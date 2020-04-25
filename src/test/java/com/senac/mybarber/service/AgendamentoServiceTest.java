package com.senac.mybarber.service;

import com.senac.mybarber.model.Agendamento;
import com.senac.mybarber.model.Cliente;
import com.senac.mybarber.model.Profissional;
import com.senac.mybarber.model.Salao;
import com.senac.mybarber.model.Servico;
import com.senac.mybarber.model.StatusAgendamento;
import com.senac.mybarber.repository.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @Mock
    AgendamentoRepository repository;

    AgendamentoService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new AgendamentoService(repository);
    }

    @Test
    public void teste_metodo_find_agendamento_by_id() {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setInicioServico(new Date());
        agendamento.setFimServico(new Date());
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        Optional<Agendamento> agendamentoOptional = Optional.of(agendamento);

        when(service.findById(1L)).thenReturn(agendamentoOptional);
        assertEquals(service.findById(1L), agendamentoOptional);
    }

    @Test
    public void teste_metodo_find_all_agendamento() throws ParseException {
        Agendamento ag1 = new Agendamento();
        ag1.setId(1l);
        ag1.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:00"));
        ag1.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:30"));
        ag1.setStatus(StatusAgendamento.AGENDADO);

        Agendamento ag2 = new Agendamento();
        ag2.setId(2l);
        ag2.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:00"));
        ag2.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:30"));
        ag2.setStatus(StatusAgendamento.AGENDADO);

        Agendamento ag3 = new Agendamento();
        ag3.setId(3l);
        ag3.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:00"));
        ag3.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 16:30"));
        ag3.setStatus(StatusAgendamento.AGENDADO);

        Agendamento ag4 = new Agendamento();
        ag4.setId(4l);
        ag4.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 17:00"));
        ag4.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/03/2020 18:30"));
        ag4.setStatus(StatusAgendamento.AGENDADO);

        Agendamento ag5 = new Agendamento();
        ag5.setId(5l);
        ag5.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/07/2020 16:00"));
        ag5.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/07/2020 16:30"));
        ag5.setStatus(StatusAgendamento.AGENDADO);

        Agendamento ag6 = new Agendamento();
        ag6.setId(6l);
        ag6.setInicioServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/020/2020 16:30"));
        ag6.setFimServico(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/20/2020 19:30"));
        ag6.setStatus(StatusAgendamento.AGENDADO);

        List<Agendamento> actual = Arrays.asList(ag1, ag2, ag3, ag4, ag5, ag6);

        when(service.findAll()).thenReturn(actual);
        List<Agendamento> expected = service.findAll();
        assertEquals(actual, expected);
    }

    @Test
    public void teste_metodo_create_agendamento() {

        Cliente cliente = new Cliente();
        Servico servico = new Servico();
        Profissional profissional = new Profissional();
        Salao salao = new Salao();
        Agendamento agendamento = new Agendamento();

        cliente.setId(1L);
        servico.setId(1L);
        profissional.setId(1L);
        salao.setId(1L);

        agendamento.setId(1L);
        agendamento.setCliente(cliente);
        agendamento.setServico(servico);
        agendamento.setProfissional(profissional);
        agendamento.setSalao(salao);
        agendamento.setInicioServico(new Date());
        agendamento.setFimServico(new Date());
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        when(repository.save(any(Agendamento.class))).then(
                invocation -> invocation.getArgument(0)
        );

        Agendamento agendamentoRetorno = service.create(agendamento);

        assertEquals(agendamentoRetorno, agendamento);
    }
}