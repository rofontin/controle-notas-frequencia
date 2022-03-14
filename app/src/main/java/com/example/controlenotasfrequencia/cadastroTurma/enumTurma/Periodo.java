package com.example.controlenotasfrequencia.cadastroTurma.enumTurma;

public enum Periodo {
    SEMESTRE_1 ("1º Semestre"),
    SEMESTRE_2 ("2º Semestre"),

    BIMESTRE_1 ("1º Bimestre"),
    BIMESTRE_2 ("2º Bimestre"),
    BIMESTRE_3 ("3º Bimestre"),
    BIMESTRE_4 ("4º Bimestre");

    private final String descricao;

    Periodo(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static Periodo fromString(String text) {
        for (Periodo b : Periodo.values()) {
            if (b.descricao.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
