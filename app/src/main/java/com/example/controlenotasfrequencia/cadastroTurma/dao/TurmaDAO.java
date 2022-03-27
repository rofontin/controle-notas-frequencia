package com.example.controlenotasfrequencia.cadastroTurma.dao;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.Turma;

import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {
    public static long salvar(Turma turma) {
        try {
            return turma.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar o turma: " + ex.getMessage());
            return -1;
        }
    }

    public static Turma getTurma(Long id) {
        try {
            return Turma.findById(Turma.class, id);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar o turma: " + ex.getMessage());
            return null;
        }
    }
    @Nullable
    public static Turma getByCodigo(int codigo) {
        try {
            List<Turma> list = getAll("codigo = ?", new String[]{String.valueOf(codigo)}, "");
            if (!list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (Exception ex) {
            Log.e("Erro", " Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Turma> retornaTurmas(String where, String[] whereArgs, String orderBy) {
        List<Turma> list = new ArrayList<>();
        try {
            list = Turma.find(Turma.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de turmas: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Turma turma) {
        try {
            return Turma.delete(turma);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao apagar a turma: " + ex.getMessage());
            return false;
        }

    }
    public static List<Turma> getAll(String where, String[] whereArgs, String orderBy) {
        List<Turma> list = new ArrayList<>();
        try {
            list = Turma.find(Turma.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de turmas: " + ex.getMessage());
        }
        return list;
    }

}
