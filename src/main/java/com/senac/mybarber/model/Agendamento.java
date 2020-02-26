package com.senac.mybarber.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Agendamento {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name="idCliente")
    @JsonIgnoreProperties("agendamentos")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name="idProfissional")
    @JsonIgnoreProperties("agendamentos")
    private Profissional profissional;

    @OneToOne
    @JoinColumn(name="idSalao")
    private Salao salao;

    @OneToOne
    @JoinColumn(name="idServico")
    private Servico servico;

    private Date inicioServico;
    private Date fimServico;
    private Date checkInCliente;
    private Date checkInProfissional;
    private Date checkoutCliente;
    private Date checkoutProfissional;
}

