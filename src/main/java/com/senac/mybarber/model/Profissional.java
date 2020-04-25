package com.senac.mybarber.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true,exclude = "agendamentos")
public class Profissional extends Pessoa{

    @ManyToMany
    @JoinTable(name="associacao_servicos")
    private Set<Servico> servicos;

    @ManyToMany
    @JoinTable(name="associacao_saloes")
    private Set<Salao> saloes;

    @OneToMany(mappedBy = "profissional")
    @JsonIgnoreProperties("profissional")
    private List<Agendamento> agendamentos;

}
