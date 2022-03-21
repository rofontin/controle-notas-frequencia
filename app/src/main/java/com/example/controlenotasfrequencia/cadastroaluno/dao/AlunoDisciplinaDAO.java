package com.example.controlenotasfrequencia.cadastroaluno.dao;

import android.util.Log;

import com.example.controlenotasfrequencia.domain.AlunoDisciplina;

public class AlunoDisciplinaDAO {

    public static long salvar(AlunoDisciplina alunoDisciplina){
        try{
            return alunoDisciplina.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o relacionamento: "+ex.getMessage());
            return -1;
        }
    }
}
