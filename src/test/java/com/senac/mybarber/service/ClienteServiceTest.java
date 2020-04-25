package com.senac.mybarber.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.senac.mybarber.model.Cliente;
import com.senac.mybarber.repository.ClienteRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;
    private ClienteService service;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @BeforeEach
    public void setUp() {
        service = new ClienteService(repository, encoder);
    }

    @Test
    public void teste_cliente_criado_com_senha_codificada() {

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setUsername("username1");
        cliente.setNome("Jose da Silva");
        cliente.setEmail("jose@gmail.com");
        cliente.setTelefone("2199219384723948");
        cliente.setSenha("123");

        when(repository.save(any(Cliente.class))).then(
                invocation -> invocation.getArgument(0)
        );

        Cliente clienteSalvo = service.create(cliente);

        Assertions.assertTrue( encoder.matches("123", clienteSalvo.getSenha()),
                               () -> "Senha nao esta codificada" );
    }
}
