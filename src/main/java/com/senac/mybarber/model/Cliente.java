package com.senac.mybarber.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Cliente extends Pessoa {

    @OneToMany
    @JoinColumn(name="idCliente")
    private List<Agendamento> agendamentos;
}
