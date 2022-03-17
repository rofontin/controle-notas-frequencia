package com.example.controlenotasfrequencia.cadastronotasfrequencia.dao;

import android.util.Log;

import com.example.controlenotasfrequencia.domain.NotasFrequencia;
import com.example.controlenotasfrequencia.domain.Disciplina;

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

    public static NotasFrequencia getDisciplina(int id){
        try{
            return NotasFrequencia.findById(NotasFrequencia.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar as notas e frequencia "+ex.getMessage());
            return null;
        }
    }

    public static List<NotasFrequencia> retornaNotasFrequencia(String where, String[]whereArgs, String orderBy){
        List<NotasFrequencia> list = new ArrayList<>();
        try{
            list = NotasFrequencia.find(NotasFrequencia.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de Notas e frequencia: "+ex.getMessage());
        }
        return list;
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
