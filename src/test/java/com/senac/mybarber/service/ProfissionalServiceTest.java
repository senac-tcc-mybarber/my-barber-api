package com.senac.mybarber.service;

import com.senac.mybarber.model.Profissional;
import com.senac.mybarber.model.Salao;
import com.senac.mybarber.model.Servico;
import com.senac.mybarber.repository.ProfissionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyIterableOf;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProfissionalServiceTest {

    @Mock
    ProfissionalRepository repository;

    @Mock
    ServicoService servicoService;

    @Mock
    SalaoService salaoService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    ProfissionalService service;

    @BeforeEach
    public void setUp() {
        service = new ProfissionalService(repository, servicoService, salaoService, encoder);
    }

    @Test
    public void teste_profissional_criado_com_senha_codificada() {
        final Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setUsername("username4");
        profissional.setNome("Alucard da Silva");
        profissional.setEmail("alucard@gmail.com");
        profissional.setTelefone("123456789");
        profissional.setSenha("123");

        when(repository.save(any(Profissional.class))).then(invocation -> invocation.getArgument(0));

        final Profissional profissionalSalvo = service.create(profissional);

        assertTrue(encoder.matches("123", profissionalSalvo.getSenha()));
    }

    @Test
    public void teste_profissional_associar_salao() {
        final Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setUsername("username4");
        profissional.setNome("Alucard da Silva");
        profissional.setEmail("alucard@gmail.com");
        profissional.setTelefone("123456789");
        profissional.setSenha("123");

        final Salao sa1 = new Salao(1l, "Salao do Shopping", "62.408.761/0001-34", "Barra da Tijuca","Av das Americas 96975", "1", "2");

        List<Long> lista = new ArrayList<Long>();
        lista.add(sa1.getId());

        List<Salao> listaSalao = new ArrayList<Salao>();
        listaSalao.add(sa1);

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(profissional));
        when(salaoService.findAllById(anyIterable())).thenReturn(listaSalao);
        when(repository.save(any(Profissional.class))).then(invocation -> invocation.getArgument(0));

        final Optional<Profissional> profissionalAssociado = service.associarSalao(profissional.getId(), lista);

        assertTrue(profissionalAssociado.isPresent() && 
        !profissionalAssociado.get().getSaloes().isEmpty() &&
        profissionalAssociado.get().getSaloes().contains(sa1)
        );
    }

    @Test
    public void teste_profissional_associar_service() {
        final Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setUsername("username4");
        profissional.setNome("Alucard da Silva");
        profissional.setEmail("alucard@gmail.com");
        profissional.setTelefone("123456789");
        profissional.setSenha("123");

        final Servico s1 = new Servico(1l, "Depilacao", 10l);

        List<Long> lista = new ArrayList<Long>();
        lista.add(s1.getId());

        List<Servico> listaServico = new ArrayList<Servico>();
        listaServico.add(s1);

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(profissional));
        when(servicoService.findAllById(anyIterable())).thenReturn(listaServico);
        when(repository.save(any(Profissional.class))).then(invocation -> invocation.getArgument(0));

        final Optional<Profissional> profissionalAssociado = service.associarServico(profissional.getId(), lista);

        assertTrue(profissionalAssociado.isPresent() && 
        !profissionalAssociado.get().getServicos().isEmpty() &&
        profissionalAssociado.get().getServicos().contains(s1)
        );
    }
}