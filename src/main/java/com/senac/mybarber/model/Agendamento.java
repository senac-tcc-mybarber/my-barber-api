package com.senac.mybarber.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Agendamento {
    @Id
    @GeneratedValue
    private long id;
    private long idCliente;
    private long idProfissional;
    private long idSalao;
    private long idServico;

    private Date inicioServico;
    private Date fimServico;
    private Date checkInCliente;
    private Date checkInProfissional;
    private Date checkoutCliente;
    private Date checkoutProfissional;
}

