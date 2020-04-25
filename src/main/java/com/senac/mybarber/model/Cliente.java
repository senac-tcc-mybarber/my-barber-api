package com.senac.mybarber.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true,exclude = "agendamentos")
@Data
@Entity
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private List<Agendamento> agendamentos;
}
