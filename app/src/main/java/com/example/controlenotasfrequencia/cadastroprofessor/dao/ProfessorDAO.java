package com.example.controlenotasfrequencia.cadastroprofessor.dao;

import android.util.Log;

import com.example.controlenotasfrequencia.domain.Aluno;
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

    public static Professor getProfessor(Long id) {
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

    public static List<Professor> getProfessoresByTurma(Long idTurma) {
        List<Professor> list = new ArrayList<>();
        try {
            list = Professor.find(Professor.class, "turma = ?", idTurma.toString());
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de professores: " + ex.getMessage());
        }
        return list;
    }

    public static List<Professor> retornaProfessoresSemDisciplinas() {
        return Professor.findWithQuery(Professor.class, "SELECT PROFESSOR.* FROM PROFESSOR LEFT JOIN " +
                "DISCIPLINA ON PROFESSOR.ID = DISCIPLINA.PROFESSOR WHERE DISCIPLINA.PROFESSOR IS NULL");
    }
    public static List<Professor> getAll(String where, String[] whereArgs, String orderBy) {
        List<Professor> list = new ArrayList<>();
        try {
            list = Professor.find(Professor.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }

    public static Professor getByNome(String nome) {
            try {
                List<Professor> professorList = getAll("nome = ?", new String[]{String.valueOf(nome)}, "");
                if (!professorList.isEmpty()) {
                    return professorList.get(0);
                }
                return null;
            } catch (Exception ex) {
                Log.e("Erro", " Erro ao retornar: " + ex.getMessage());
                return null;
            }
        }
    }

