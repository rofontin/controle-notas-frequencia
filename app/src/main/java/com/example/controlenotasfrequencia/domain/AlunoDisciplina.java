package com.example.controlenotasfrequencia.domain;

import com.orm.SugarRecord;

public class AlunoDisciplina extends SugarRecord {

    private Long idAluno;
    private Long idDisciplina;

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
}
