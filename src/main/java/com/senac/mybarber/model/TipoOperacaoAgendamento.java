package com.senac.mybarber.model;

public enum TipoOperacaoAgendamento {
    CHECKIN(1), CHECKOUT(2);

    private final int valor;

    TipoOperacaoAgendamento(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }
}
