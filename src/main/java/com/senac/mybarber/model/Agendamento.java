package com.senac.mybarber.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static com.senac.mybarber.model.StatusAgendamento.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

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

        if (status == AGUARDANDO_CLIENTE) {
            status = EM_ANDAMENTO;
        } else {
            status = AGUARDANDO_CLIENTE;
        }
    }
}

