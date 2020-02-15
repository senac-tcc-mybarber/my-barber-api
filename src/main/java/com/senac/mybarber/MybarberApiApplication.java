package com.senac.mybarber;

import com.senac.mybarber.model.Salao;
import com.senac.mybarber.model.Servico;
import com.senac.mybarber.repository.SalaoRepository;
import com.senac.mybarber.repository.ServicoRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class MybarberApiApplication {

	@Autowired
	private SalaoRepository salaoRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	public static void main(String[] args) {
		SpringApplication.run(MybarberApiApplication.class, args);
	}


	@Bean
	InitializingBean carregaDados() {
		return () -> {
			servicoRepository.saveAll(
					Arrays.asList(
							new Servico(1l, "Depilacao"),
							new Servico(2l, "Manicure"),
							new Servico(3l, "Pedicure"),
							new Servico(4l, "Corte de Cabelo"),
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
		};
	}
}
