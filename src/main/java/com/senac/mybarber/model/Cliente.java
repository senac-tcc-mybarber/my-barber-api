package com.senac.mybarber.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true,exclude = "agendamentos")
@Data
@Entity
public class Cliente extends Pessoa {

    @OneToMany
    @JoinColumn(name="idCliente")
    @JsonIgnoreProperties("cliente")
    private List<Agendamento> agendamentos;
}
