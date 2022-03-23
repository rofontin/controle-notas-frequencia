package com.example.controlenotasfrequencia.domain;

import com.orm.SugarRecord;

public class AlunoDisciplina extends SugarRecord {

    private Long aluno;
    private Long disciplina;

    public AlunoDisciplina () {

    }

    public AlunoDisciplina(Long aluno, Long disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public Long getAluno() {
        return aluno;
    }

    public void setAluno(Long aluno) {
        this.aluno = aluno;
    }

    public Long getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Long disciplina) {
        this.disciplina = disciplina;
    }
}
