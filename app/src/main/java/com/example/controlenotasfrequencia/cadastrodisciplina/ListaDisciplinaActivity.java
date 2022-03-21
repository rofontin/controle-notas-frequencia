package com.example.controlenotasfrequencia.cadastrodisciplina;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastrodisciplina.adapters.DisciplinaAdapter;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.example.controlenotasfrequencia.util.Util;

import java.util.List;

public class ListaDisciplinaActivity extends AppCompatActivity {

    private LinearLayout lnListaDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplina);

        lnListaDisciplina = findViewById(R.id.lnListaDisciplina);

        atualizaListaDisciplina();
    }

    public void atualizaListaDisciplina() {
        List<Disciplina> listaDisciplina = DisciplinaDAO.retornaDisciplina("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: " + listaDisciplina.size());

        RecyclerView rvListaDisciplina = findViewById(R.id.rvListaDisciplina);
        DisciplinaAdapter adapter = new DisciplinaAdapter(listaDisciplina, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaDisciplina.setLayoutManager(llm);
        rvListaDisciplina.setAdapter(adapter);
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
            abrirCadastroDisciplina();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void abrirCadastroDisciplina() {
        Intent intent = new Intent(this, CadastroDisciplinaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnListaDisciplina, "Disciplina salva com sucesso!", 1);
        }
        atualizaListaDisciplina();
    }
}