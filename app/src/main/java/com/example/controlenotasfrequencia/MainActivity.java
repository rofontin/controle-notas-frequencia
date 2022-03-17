package com.example.controlenotasfrequencia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlenotasfrequencia.cadastroTurma.ListaTurmaActivity;
import com.example.controlenotasfrequencia.cadastroaluno.ListaAlunoActivity;
import com.example.controlenotasfrequencia.cadastrodisciplina.ListaDisciplinaActivity;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.ListaNotasFrequenciaActivity;
import com.example.controlenotasfrequencia.cadastroprofessor.ListaProfessorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listarAlunos(View view) {
        Intent intent = new Intent(this, ListaAlunoActivity.class);
        startActivity(intent);
    }

    public void listarProfessores(View view) {
        Intent intent = new Intent(this, ListaProfessorActivity.class);
        startActivity(intent);
    }

    public void listarTurmas(View view) {
        Intent intent = new Intent(this, ListaTurmaActivity.class);
        startActivity(intent);
    }

    public void listarDisciplinas(View view) {
        Intent intent = new Intent(this, ListaDisciplinaActivity.class);
        startActivity(intent);
    }

    public void listarNotasFrequencia(View view) {
        Intent intent = new Intent(this, ListaNotasFrequenciaActivity.class);
        startActivity(intent);
    }
}