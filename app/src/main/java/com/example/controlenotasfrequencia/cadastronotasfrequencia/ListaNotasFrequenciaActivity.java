package com.example.controlenotasfrequencia.cadastronotasfrequencia;

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
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDAO;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.adapters.NotasFrequenciaAdapter;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.util.Util;

import java.util.List;

public class ListaNotasFrequenciaActivity extends AppCompatActivity {

    private LinearLayout lnListaNotasFrequencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas_frequencia);

        lnListaNotasFrequencia = findViewById(R.id.lnListaNotasFrequencia);

        atualizaListaNotasFrequencia();
    }

    public void atualizaListaNotasFrequencia(){
        List<Aluno> listaAluno = AlunoDAO.retornaAlunos("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: "+listaAluno.size());

        RecyclerView rvListaNotasFrequencia = findViewById(R.id.rvListaNotasFrequencia);
        NotasFrequenciaAdapter adapter = new NotasFrequenciaAdapter(listaAluno, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaNotasFrequencia.setLayoutManager(llm);
        rvListaNotasFrequencia.setAdapter(adapter);
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
            abrirCadastroNotasFrequencia();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void abrirCadastroNotasFrequencia() {
        Intent intent = new Intent(this, CadastroNotasFrequenciaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnListaNotasFrequencia, "Nota e frequencia salvos com sucesso!", 1);
        }
        atualizaListaNotasFrequencia();
    }

}