package com.example.controlenotasfrequencia.cadastroTurma.enumTurma;

public enum Regime {
    ANUAL("Anual"),
    SEMESTRAL("Semestral");

    private final String descricao;

    Regime(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static Regime fromString(String text) {
        for (Regime b : Regime.values()) {
            if (b.descricao.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
