package com.example.controlenotasfrequencia.cadastroprofessor.dao;

import android.util.Log;

import com.example.controlenotasfrequencia.domain.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public static long salvar(Professor professor) {
        try {
            return professor.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar o professor: " + ex.getMessage());
            return -1;
        }
    }

    public static Professor getProfessor(int id) {
        try {
            return Professor.findById(Professor.class, id);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar o professor: " + ex.getMessage());
            return null;
        }
    }

    public static List<Professor> retornaProfessores(String where, String[] whereArgs, String orderBy) {
        List<Professor> list = new ArrayList<>();
        try {
            list = Professor.find(Professor.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de professores: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Professor professor) {
        try {
            return Professor.delete(professor);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao apagar o profesor: " + ex.getMessage());
            return false;
        }

    }
}
