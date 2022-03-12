package com.example.controlenotasfrequencia.domain;

import com.orm.SugarRecord;

import java.util.Objects;

public class Disciplina extends SugarRecord {

    private int codigo;
    private String nome;
    private String cargaHoraria;
    private String professor;

    public Disciplina(int codigo, String nome, String cargaHoraria, String professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
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

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
