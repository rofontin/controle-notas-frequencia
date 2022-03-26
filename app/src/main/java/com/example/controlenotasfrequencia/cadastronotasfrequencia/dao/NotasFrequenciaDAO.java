package com.example.controlenotasfrequencia.cadastronotasfrequencia.dao;

import android.util.Log;

import com.example.controlenotasfrequencia.domain.NotasFrequencia;

import java.util.ArrayList;
import java.util.List;

public class NotasFrequenciaDAO {

    public static long salvar(NotasFrequencia notasFrequencia){
        try{
            return notasFrequencia.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar as notas e frequencia "+ex.getMessage());
            return -1;
        }
    }

    public static List<NotasFrequencia> retornaNotasFrequencia(String where, String... whereArgs){
        List<NotasFrequencia> list = new ArrayList<>();
        try{
            list = NotasFrequencia.find(NotasFrequencia.class, where, whereArgs);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de Notas e frequencia: "+ex.getMessage());
        }
        return list;
    }

    public static List<NotasFrequencia> getNotasFrequenciaByAluno(Long idAluno) {
        List<NotasFrequencia> list = new ArrayList<>();
        try {
            list = NotasFrequencia.find(NotasFrequencia.class, "aluno = ?", idAluno.toString());
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de Notas e frequencia: " + ex.getMessage());
        }
        return list;
    }

    public static List<NotasFrequencia> getNotasFrequenciaByTurma(Long idTurma) {
        List<NotasFrequencia> list = new ArrayList<>();
        try {
            list = NotasFrequencia.find(NotasFrequencia.class, "turma = ?", idTurma.toString());
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de Notas e frequencia: " + ex.getMessage());
        }
        return list;
    }

    public static List<NotasFrequencia> getNotasFrequenciaByDisciplina(Long idDisciplina) {
        List<NotasFrequencia> list = new ArrayList<>();
        try {
            list = NotasFrequencia.find(NotasFrequencia.class, "disciplina = ?", idDisciplina.toString());
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de Notas e frequencia: " + ex.getMessage());
        }
        return list;
    }

    public static void deleteInBatch(List<NotasFrequencia> notasFrequencias) {
        try {
            NotasFrequencia.deleteInTx(notasFrequencias);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao apagar as notas e frequencias: " + ex.getMessage());
        }
    }

    public static boolean delete(NotasFrequencia notasFrequencia){
        try{
            return NotasFrequencia.delete(notasFrequencia);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar as notas e frequencia: "+ex.getMessage());
            return false;
        }
    }
}
