package com.example.controlenotasfrequencia.domain;

import com.orm.SugarRecord;

import java.util.Objects;

public class Professor extends SugarRecord {

    private int registro;
    private String nome;
    private String cpf;
    private String dtNasc;
    private String dtAdesao;
    private String turma;
    private String disciplina;

    public Professor() {

    }

    public Professor(int registro, String nome, String cpf, String dtNasc, String dtAdesao, String turma, String disciplina) {
        this.registro = registro;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.dtAdesao = dtAdesao;
        this.turma = turma;
        this.disciplina = disciplina;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getDtAdesao() {
        return dtAdesao;
    }

    public void setDtAdesao(String dtAdesao) {
        this.dtAdesao = dtAdesao;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return registro == professor.registro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registro);
    }

    @Override
    public String toString() {
        return nome;
    }
}
