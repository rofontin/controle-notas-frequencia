package com.example.controlenotasfrequencia.domain;

import com.orm.SugarRecord;

import java.util.Objects;

public class NotasFrequencia extends SugarRecord {

    private int codigo;
    private String turma;
    private String aluno;
    private String disciplina;
    private String frequencia;
    private String notas;
    private String resultado;

    public NotasFrequencia(int codigo, String turma, String aluno, String disciplina, String frequencia, String notas, String resultado) {
        this.codigo = codigo;
        this.turma = turma;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.frequencia = frequencia;
        this.notas = notas;
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

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
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
