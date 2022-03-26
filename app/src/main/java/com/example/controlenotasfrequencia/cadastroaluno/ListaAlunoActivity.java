package com.example.controlenotasfrequencia.cadastroaluno;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroaluno.adapters.AlunoAdapter;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunoActivity extends AppCompatActivity implements AlunoAdapter.OnAlunoListener {

    private RecyclerView rvListaAlunos;
    private LinearLayout lnLista;
    private List<Aluno> listaAluno = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno);

        lnLista = findViewById(R.id.lnListaAluno);

        atualizaListaAluno();
    }

    public void atualizaListaAluno(){
        listaAluno = AlunoDAO.getAll("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: "+listaAluno.size());

        rvListaAlunos = findViewById(R.id.rvListaAluno);
        AlunoAdapter adapter = new AlunoAdapter(listaAluno, this,this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaAlunos.setLayoutManager(llm);
        rvListaAlunos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_add:
                abrirCadastroAluno();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void abrirCadastroAluno() {
        Intent intent = new Intent(this, CadastroAlunoActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Util.customSnackBar(lnLista, "Aluno salvo com sucesso!", 1);
        }
        atualizaListaAluno();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onAlunoClick(int position) {
        Intent intent = new Intent(this, CadastroAlunoActivity.class);
        intent.putExtra("ra", listaAluno.get(position).getRa());
        startActivityForResult(intent, 1);
    }
}