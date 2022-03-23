package com.example.controlenotasfrequencia.cadastroTurma;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.adapters.TurmaAdapter;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.domain.Turma;
import com.example.controlenotasfrequencia.util.Util;

import java.util.List;

public class ListaTurmaActivity extends AppCompatActivity {

    private LinearLayout lnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turma);

        lnLista = findViewById(R.id.lnListaTurma);

        atualizaListaTurma();
    }

    public void atualizaListaTurma() {
        List<Turma> listaTurma = TurmaDAO.retornaTurmas("", new String[]{}, "codigo asc");
        Log.e("PHS", "Tamanho da lista: " + listaTurma.size());

        RecyclerView rvListaTurma = findViewById(R.id.rvListaTurma);
        TurmaAdapter adapter = new TurmaAdapter(listaTurma, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaTurma.setLayoutManager(llm);
        rvListaTurma.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mn_add) {
            abrirCadastroTurma();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void abrirCadastroTurma() {
        Intent intent = new Intent(this, CadastroTurmaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnLista, "Turma salva com sucesso!", 1);
        }
        atualizaListaTurma();
    }

    public void deletar(View view) {
        //TODO deletar vinculos tbm
//        Aluno aluno = AlunoDAO.getAluno(1L);
//        AlunoDAO.delete(aluno);
        atualizaListaTurma();
    }
}