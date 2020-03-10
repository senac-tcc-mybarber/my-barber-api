package com.senac.mybarber.model;

import java.util.Arrays;
import java.util.Optional;

public enum StatusAgendamento {
    AGENDADO(1),
    AGUARDANDO_CLIENTE(2),
    AGUARDANDO_PROFISSIONAL(3),
    EM_ANDAMENTO(4),
    CONCLUIDO(5),
    CANCELADO(6);

    private final int value;

    StatusAgendamento(int value) {
        this.value = value;
    }

    public static Optional<StatusAgendamento> valueOf(int value) {
        return Arrays.stream(values())
            .filter(legNo -> legNo.value == value)
            .findFirst();
    }
}
