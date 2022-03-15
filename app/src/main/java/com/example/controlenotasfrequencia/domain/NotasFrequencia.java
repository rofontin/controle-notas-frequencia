package com.example.controlenotasfrequencia.domain;

import com.orm.SugarRecord;

import java.util.Objects;

public class NotasFrequencia extends SugarRecord {

    private int codigo;
    private String turma;
    private String aluno;
    private String frequencia;
    private String notas;
    private String resultado;


    public NotasFrequencia(int codigo, String turma, String aluno, String frequencia, String notas) {
        this.codigo = codigo;
        this.turma = turma;
        this.aluno = aluno;
        this.frequencia = frequencia;
        this.notas = notas;
        this.resultado = resultado;

    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public NotasFrequencia() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotasFrequencia that = (NotasFrequencia) o;
        return codigo == that.codigo && Objects.equals(turma, that.turma) && Objects.equals(aluno, that.aluno) && Objects.equals(frequencia, that.frequencia) && Objects.equals(notas, that.notas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, turma, aluno, frequencia, notas);
    }
}
