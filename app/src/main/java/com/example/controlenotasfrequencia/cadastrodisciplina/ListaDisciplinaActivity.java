package com.example.controlenotasfrequencia.cadastrodisciplina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroaluno.adapters.AlunoAdapter;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDAO;
import com.example.controlenotasfrequencia.cadastrodisciplina.adapters.DisciplinaAdapter;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class ListaDisciplinaActivity extends AppCompatActivity {

    private RecyclerView rvListaDisciplina;
    private LinearLayout lnListaDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplina);

    }
    public void atualizaListaDisciplina(){
        List<Disciplina> listaDisciplina = new ArrayList<>();
        listaDisciplina = DisciplinaDAO.retornaDisciplina("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: "+listaDisciplina.size());

        rvListaDisciplina = findViewById(R.id.rvListaDisciplina);
        DisciplinaAdapter adapter = new DisciplinaAdapter(listaDisciplina, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaDisciplina.setLayoutManager(llm);
        rvListaDisciplina.setAdapter(adapter);
    }

}