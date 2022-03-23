package com.example.controlenotasfrequencia.domain;

import com.orm.SugarRecord;

import java.util.Objects;

public class Disciplina extends SugarRecord {

    private int codigo;
    private String nome;
    private int cargaHoraria;
    private Long professor;

    public Disciplina(int codigo, String nome, int cargaHoraria, Long professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }

    public Disciplina() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return codigo == that.codigo && Objects.equals(nome, that.nome) && Objects.equals(cargaHoraria, that.cargaHoraria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, cargaHoraria);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Long getProfessor() {
        return professor;
    }

    public void setProfessor(Long professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return nome;
    }
}
