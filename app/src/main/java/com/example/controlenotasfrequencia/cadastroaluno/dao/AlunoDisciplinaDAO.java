package com.example.controlenotasfrequencia.cadastroaluno.dao;

import android.util.Log;

import com.example.controlenotasfrequencia.domain.AlunoDisciplina;

import java.util.ArrayList;
import java.util.List;

public class AlunoDisciplinaDAO {

    public static long salvar(AlunoDisciplina alunoDisciplina) {
        try {
            return alunoDisciplina.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar o relacionamento: " + ex.getMessage());
            return -1;
        }
    }

    public static List<AlunoDisciplina> getAlunoDisciplinaByAluno(Long idAluno) {
        List<AlunoDisciplina> list = new ArrayList<>();
        try {
            list = AlunoDisciplina.find(AlunoDisciplina.class, "aluno = ?", idAluno.toString());
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de AlunoDisciplina: " + ex.getMessage());
        }
        return list;
    }

    public static List<AlunoDisciplina> getAlunoDisciplinaByDisciplina(Long idDisciplina) {
        List<AlunoDisciplina> list = new ArrayList<>();
        try {
            list = AlunoDisciplina.find(AlunoDisciplina.class, "disciplina = ?", idDisciplina.toString());
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de AlunoDisciplina: " + ex.getMessage());
        }
        return list;
    }

    public static void deleteInBatch(List<AlunoDisciplina> alunoDisciplinas) {
        try {
            AlunoDisciplina.deleteInTx(alunoDisciplinas);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao apagar o aluno: " + ex.getMessage());
        }
    }
}
