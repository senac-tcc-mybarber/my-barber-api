package com.senac.mybarber.model;

public enum TipoEntidadeOperacaoAgendamento {
    CLIENTE(1), PROFISSIONAL(2);

    private final int valor;

    TipoEntidadeOperacaoAgendamento(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }
}
