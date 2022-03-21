package com.example.controlenotasfrequencia.domain;

import com.orm.SugarRecord;

import java.util.Objects;

public class NotasFrequencia extends SugarRecord {

    private int codigo;
    private Long turma;
    private Long aluno;
    private Long disciplina;
    private String frequencia;
    private Double nota;
    private Double resultado;

    public NotasFrequencia(int codigo, Long turma, Long aluno, Long disciplina, String frequencia, Double nota, Double resultado) {
        this.codigo = codigo;
        this.turma = turma;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.frequencia = frequencia;
        this.nota = nota;
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

    public Long getTurma() {
        return turma;
    }

    public void setTurma(Long turma) {
        this.turma = turma;
    }

    public Long getAluno() {
        return aluno;
    }

    public void setAluno(Long aluno) {
        this.aluno = aluno;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Long getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Long disciplina) {
        this.disciplina = disciplina;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotasFrequencia that = (NotasFrequencia) o;
        return codigo == that.codigo && Objects.equals(turma, that.turma) && Objects.equals(aluno, that.aluno) && Objects.equals(frequencia, that.frequencia) && Objects.equals(nota, that.nota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, turma, aluno, frequencia, nota);
    }
}
