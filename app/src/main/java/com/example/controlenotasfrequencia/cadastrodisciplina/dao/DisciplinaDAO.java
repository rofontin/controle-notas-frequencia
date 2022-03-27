package com.example.controlenotasfrequencia.cadastrodisciplina.dao;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.Disciplina;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DisciplinaDAO {

    public static long salvar(Disciplina disciplina){
        try{
            return disciplina.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar a disciplina: "+ex.getMessage());
            return -1;
        }
    }

    public static Disciplina getDisciplina(Long id){
        try{
            return Disciplina.findById(Disciplina.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar a disciplina: "+ex.getMessage());
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Optional<Disciplina> getDisciplinaByProfessor(Long idProfessor){
        try{
            return Disciplina.find(Disciplina.class, "professor = ?", idProfessor.toString()).stream().findFirst();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de disciplinas: "+ex.getMessage());
        }
        return Optional.empty();
    }

    public static List<Disciplina> retornaDisciplina(String where, String[]whereArgs, String orderBy){
        List<Disciplina> list = new ArrayList<>();
        try{
            list = Disciplina.find(Disciplina.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de disciplinas: "+ex.getMessage());
        }
        return list;
    }

    public static List<Disciplina> retornaDisciplina(String where, String... whereArgs){
        List<Disciplina> list = new ArrayList<>();
        try{
            list = Disciplina.find(Disciplina.class, where, whereArgs);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de disciplinas: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Disciplina disciplina){
        try{
            return Disciplina.delete(disciplina);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar a disciplina: "+ex.getMessage());
            return false;
        }

    }
    public static List<Disciplina> getAll(String where, String[] whereArgs, String orderBy) {
        List<Disciplina> list = new ArrayList<>();
        try {
            list = Disciplina.find(Disciplina.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista: " + ex.getMessage());
        }
        return list;
    }


    public static Disciplina getByNome(String nome) {
        try {
            List<Disciplina> disciplinaList = getAll("nome = ?", new String[]{String.valueOf(nome)}, "");
            if (!disciplinaList.isEmpty()) {
                return disciplinaList.get(0);
            }
            return null;
        } catch (Exception ex) {
            Log.e("Erro", " Erro ao retornar: " + ex.getMessage());
            return null;
        }
    }
}
