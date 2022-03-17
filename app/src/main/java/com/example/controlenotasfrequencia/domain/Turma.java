package com.example.controlenotasfrequencia.domain;

import androidx.annotation.NonNull;

import com.example.controlenotasfrequencia.cadastroTurma.enumTurma.Periodo;
import com.example.controlenotasfrequencia.cadastroTurma.enumTurma.Regime;
import com.example.controlenotasfrequencia.cadastroTurma.enumTurma.Turno;
import com.orm.SugarRecord;

public class Turma extends SugarRecord {

    private int codigo;
    private String descricao;
    private Regime regime;
    private Periodo periodo;
    private Turno turno;
    private String dtInicio;
    private String dtTermino;

    public Turma(int codigo, String descricao, Regime regime, Periodo periodo, Turno turno) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.regime = regime;
        this.periodo = periodo;
        this.turno = turno;
    }

    public Turma() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public String getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtTermino() {
        return dtTermino;
    }

    public void setDtTermino(String dtTermino) {
        this.dtTermino = dtTermino;
    }

    @NonNull
    @Override
    public String toString() {
        return descricao;
    }
}
