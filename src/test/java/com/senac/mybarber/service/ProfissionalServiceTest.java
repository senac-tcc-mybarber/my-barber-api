package com.senac.mybarber.service;

import com.senac.mybarber.model.Profissional;
import com.senac.mybarber.repository.ProfissionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    public void setUp(){
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

        when(repository.save(any(Profissional.class))).then(
                invocation -> invocation.getArgument(0)
        );

        Profissional profissionalSalvo = service.create(profissional);

        assertTrue(encoder.matches("123", profissionalSalvo.getSenha()));
    }
}