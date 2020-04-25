package com.senac.mybarber.model;

import static com.senac.mybarber.model.StatusAgendamento.AGUARDANDO_CLIENTE;
import static com.senac.mybarber.model.StatusAgendamento.AGUARDANDO_PROFISSIONAL;
import static com.senac.mybarber.model.StatusAgendamento.EM_ANDAMENTO;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Agendamento {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="idCliente")
    @JsonIgnoreProperties("agendamentos")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="idProfissional")
    @JsonIgnoreProperties("agendamentos")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name="idSalao")
    private Salao salao;

    @OneToOne
    @JoinColumn(name="idServico")
    private Servico servico;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date inicioServico;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date fimServico;

    private StatusAgendamento status;

    @Setter(PRIVATE)
    private Date checkInCliente;

    @Setter(PRIVATE)
    private Date checkInProfissional;

    @Setter(PRIVATE)
    private Date finalizacao;

    public void checkInCliente() {
        if(isNull(checkInCliente)) {
            checkInCliente = new Date();
        }

        if(status == AGUARDANDO_CLIENTE) {
            status = EM_ANDAMENTO;
        } else {
            status = AGUARDANDO_PROFISSIONAL;
        }
    }

    public void checkInProfissional() {
        if(isNull(checkInProfissional)) {
            checkInProfissional = new Date();
        }

        if (status == AGUARDANDO_PROFISSIONAL) {
            status = EM_ANDAMENTO;
        } else {
            status = AGUARDANDO_CLIENTE;
        }
    }
}

