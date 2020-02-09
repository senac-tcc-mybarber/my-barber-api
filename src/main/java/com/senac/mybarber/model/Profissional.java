package com.senac.mybarber.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Profissional extends Pessoa{

    @ManyToMany
    @JoinTable(name="associacao_servicos")
    private Set<Servico> servicos;
}
