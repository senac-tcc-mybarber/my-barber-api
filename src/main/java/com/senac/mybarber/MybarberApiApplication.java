package com.senac.mybarber;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.senac.mybarber.repository.ServicoRepository;
import com.senac.mybarber.model.Servico;

import java.util.Arrays;

@SpringBootApplication
public class MybarberApiApplication {

	@Autowired
	private ServicoRepository servicoRepository;

	public static void main(String[] args) {
		SpringApplication.run(MybarberApiApplication.class, args);
	}


	@Bean
	InitializingBean carregaServicos() {
		return () -> servicoRepository.saveAll(
				Arrays.asList(
						new Servico(1l,"Depilacao"),
						new Servico(2l,"Manicure"),
						new Servico(3l,"Pedicure"),
						new Servico(4l,"Corte de Cabelo"),
						new Servico(5l,"Pintura de Cabelo"),
						new Servico(6l,"Escova Progressiva"),
						new Servico(7l,"Barba")
				)
		);
	}
}
