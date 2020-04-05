package com.senac.mybarber.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoTest {


    @Test
    public void  checkIn_profissional_sem_cliente() {
        Agendamento agendamento = new Agendamento();
        agendamento.checkInProfissional();

        assertEquals(StatusAgendamento.AGUARDANDO_CLIENTE, agendamento.getStatus());
    }

    @Test
    public void  checkIn_profissional_com_cliente() {
        Agendamento agendamento = new Agendamento();
        agendamento.checkInCliente();
        agendamento.checkInProfissional();

        assertEquals(StatusAgendamento.EM_ANDAMENTO, agendamento.getStatus());
    }

    @Test
    public void checkIn_cliente_sem_profissional() {
        Agendamento agendamento = new Agendamento();
        agendamento.checkInCliente();

        assertEquals(StatusAgendamento.AGUARDANDO_PROFISSIONAL, agendamento.getStatus());
    }

    @Test
    public void checkIn_cliente_com_profissional() {
        Agendamento agendamento = new Agendamento();
        agendamento.checkInProfissional();
        agendamento.checkInCliente();

        assertEquals(StatusAgendamento.EM_ANDAMENTO, agendamento.getStatus());
    }
}