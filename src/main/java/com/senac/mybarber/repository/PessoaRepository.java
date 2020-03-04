package com.senac.mybarber.repository;

import com.senac.mybarber.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Long> {
    Pessoa findByUsername(String username);
}
