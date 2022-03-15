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
import com.example.controlenotasfrequencia.cadastrodisciplina.CadastroDisciplinaActivity;
import com.example.controlenotasfrequencia.cadastrodisciplina.adapters.DisciplinaAdapter;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.adapters.NotasFrequenciaAdapter;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.dao.NotasFrequenciaDAO;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.example.controlenotasfrequencia.domain.NotasFrequencia;
import com.example.controlenotasfrequencia.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaNotasFrequenciaActivity extends AppCompatActivity {

    private RecyclerView rvListaNotasFrequencia;
    private LinearLayout lnListaNotasFrequencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_notas_frequencia);

        lnListaNotasFrequencia = findViewById(R.id.lnListaNotasFrequencia);

        atualizaListaNotasFrequencia();

    }
    public void atualizaListaNotasFrequencia(){
        List<NotasFrequencia> listaNotasFrequencia = new ArrayList<>();
        listaNotasFrequencia = NotasFrequenciaDAO.retornaNotasFrequencia("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: "+listaNotasFrequencia.size());

        rvListaNotasFrequencia = findViewById(R.id.rvListaNotasFrequencia);
        NotasFrequenciaAdapter adapter = new NotasFrequenciaAdapter(listaNotasFrequencia, this);
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
        switch (item.getItemId()) {
            case R.id.mn_add:
                abrirCadastroNotasFrequencia();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroNotasFrequencia() {
        Intent intent = new Intent(this, CadastroNotasFrequenciaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnListaNotasFrequencia, "Disciplina salva com sucesso!", 1);
        }
        atualizaListaNotasFrequencia();
    }

}