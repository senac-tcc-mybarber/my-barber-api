package com.senac.mybarber.repository;

import com.senac.mybarber.model.Salao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaoRepository extends JpaRepository<Salao, Long> {
}
