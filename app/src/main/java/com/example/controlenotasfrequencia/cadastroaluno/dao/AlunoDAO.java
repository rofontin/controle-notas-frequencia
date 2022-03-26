package com.example.controlenotasfrequencia.cadastroaluno.dao;

import android.util.Log;

import com.example.controlenotasfrequencia.domain.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public static long salvar(Aluno aluno){
        try{
            return aluno.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o aluno: "+ex.getMessage());
            return -1;
        }
    }

    public static Aluno getAluno(Long id){
         try{
            return Aluno.findById(Aluno.class, id);
         }catch (Exception ex){
             Log.e("Erro", "Erro ao retornar o aluno: "+ex.getMessage());
             return null;
         }
    }
    public static Aluno getByRa(int ra) {
        try {
            List<Aluno> alunoList = getAll("ra = ?", new String[]{String.valueOf(ra)}, "");
            if (!alunoList.isEmpty()) {
                return alunoList.get(0);
            }
            return null;
        } catch (Exception ex) {
            Log.e("Erro", " Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }

    public static List<Aluno> retornaAlunos(String where, String[]whereArgs, String orderBy){
        List<Aluno> list = new ArrayList<>();
        try{
            list = Aluno.find(Aluno.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de alunos: "+ex.getMessage());
        }
        return list;
    }

    public static List<Aluno> getAlunoByTurma(Long idTurma) {
        List<Aluno> list = new ArrayList<>();
        try {
            list = Aluno.find(Aluno.class, "turma = ?", idTurma.toString());
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de alunos: " + ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Aluno aluno){
        try{
            return Aluno.delete(aluno);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o aluno: "+ex.getMessage());
            return false;
        }
    }

    public static List<Aluno> getAll(String where, String[] whereArgs, String orderBy) {
        List<Aluno> list = new ArrayList<>();
        try {
            list = Aluno.find(Aluno.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }
}
