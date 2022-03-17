package com.example.controlenotasfrequencia.domain;

import com.orm.SugarRecord;

public class AlunoDisciplina extends SugarRecord {

    private int idAluno;
    private int idDisciplina;

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
}
