package com.example.controlenotasfrequencia.cadastroprofessor;

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
import com.example.controlenotasfrequencia.cadastroaluno.CadastroAlunoActivity;
import com.example.controlenotasfrequencia.cadastroprofessor.adapters.ProfessorAdapter;
import com.example.controlenotasfrequencia.cadastroprofessor.dao.ProfessorDAO;
import com.example.controlenotasfrequencia.domain.Professor;
import com.example.controlenotasfrequencia.util.Util;

import java.util.List;

public class ListaProfessorActivity extends AppCompatActivity implements ProfessorAdapter.OnProfessorListener {

    private LinearLayout lnLista;
    private List<Professor> listaProfessores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professor);

        lnLista = findViewById(R.id.lnListaProfessor);

        atualizaListaProfessor();
    }

    public void atualizaListaProfessor() {
        listaProfessores = ProfessorDAO.getAll("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: " + listaProfessores.size());

        RecyclerView rvListaProfessores = findViewById(R.id.rvListaProfessor);
        ProfessorAdapter adapter = new ProfessorAdapter(listaProfessores, this, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaProfessores.setLayoutManager(llm);
        rvListaProfessores.setAdapter(adapter);
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
            abrirCadastroProfessor();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void abrirCadastroProfessor() {
        Intent intent = new Intent(this, CadastroProfessorActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnLista, "Professor salvo com sucesso!", 1);
        }
        atualizaListaProfessor();
    }

    @Override
    public void onProfessorClick(int position) {
        Intent intent = new Intent(this, CadastroProfessorActivity.class);
        intent.putExtra("nome", listaProfessores.get(position).getNome());
        startActivityForResult(intent, 1);
    }
}