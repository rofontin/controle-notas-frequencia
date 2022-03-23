package com.example.controlenotasfrequencia.cadastronotasfrequencia;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDAO;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDisciplinaDAO;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.dao.NotasFrequenciaDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.AlunoDisciplina;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.example.controlenotasfrequencia.domain.NotasFrequencia;
import com.example.controlenotasfrequencia.domain.Turma;
import com.example.controlenotasfrequencia.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CadastroNotasFrequenciaActivity extends AppCompatActivity {

    private MaterialSpinner spTurma;
    private MaterialSpinner spAluno;
    private MaterialSpinner spDisciplina;
    private TextInputEditText edFrequencia;
    private TextInputEditText edNota;
    private LinearLayout lnNotasFrequencia;
    private List<Aluno> aluno = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();
    private Aluno alunoSelecionado;
    private Turma turmaSelecionada;
    private Disciplina disciplinaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_notas_frequencia);

        edFrequencia = findViewById(R.id.edFrequencia);
        edNota = findViewById(R.id.edNota);
        lnNotasFrequencia = findViewById(R.id.lnPrincipalNotasFrequencia);

        iniciaSpinners();
    }

    private void iniciaSpinners(){
        spTurma= findViewById(R.id.spTurma);
        spAluno= findViewById(R.id.spAluno);
        spDisciplina= findViewById(R.id.spDisciplina);

        List<Turma> turma = TurmaDAO.retornaTurmas("", new String[]{}, "descricao");
        iniciaSpinnerTurma(turma);
        iniciaSpinnerAluno();
        iniciaSpinnerDisciplina();
    }

    private void iniciaSpinnerDisciplina() {
        spDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i >= 0){
                    disciplinaSelecionada = disciplinas.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void iniciaSpinnerAluno() {
        spAluno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i >= 0){
                    alunoSelecionado = aluno.get(i);
                    List<AlunoDisciplina> alunoDisciplinas = AlunoDisciplinaDAO.getAlunoDisciplinaByAluno(alunoSelecionado.getId());

                    if(!alunoDisciplinas.isEmpty()){
                        List<Long> idsDisciplinas = new ArrayList<>();
                        alunoDisciplinas.forEach(alunoDisciplina -> {
                            idsDisciplinas.add(alunoDisciplina.getDisciplina());
                        });

                        disciplinas = DisciplinaDAO.retornaDisciplina("id in"
                                + idsDisciplinas.toString().replace("[", "(").replace("]", ")"));

                        spDisciplina.setAdapter(new ArrayAdapter(CadastroNotasFrequenciaActivity.this,
                                android.R.layout.simple_list_item_1,  disciplinas));
                    }
                }
            }

            private List<String> getListIdDisciplina(AlunoDisciplina alunoDisciplina) {
                List<String> ids = new ArrayList<>();
                return ids;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void iniciaSpinnerTurma(List<Turma> turma) {
        spTurma.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turma));

        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i >= 0){
                    turmaSelecionada = turma.get(i);
                    aluno = AlunoDAO.retornaAlunos("turma = " + turma.get(i).getId().toString() , new String[]{}, "nome");

                    spAluno.setAdapter(new ArrayAdapter(CadastroNotasFrequenciaActivity.this,
                            android.R.layout.simple_list_item_1,  aluno));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void validaCampos(){
        if(edFrequencia.getText().toString().equals("")){
            edFrequencia.setError("Informe a frequência do Aluno!");
            edFrequencia.requestFocus();
            return;
        }

        if(edNota.getText().toString().equals("")){
            edNota.setError("Informe a Nota do Aluno!");
            edNota.requestFocus();
            return;
        }
        salvarDisciplina();
    }

    public void salvarDisciplina(){
        NotasFrequencia notasFrequencia = new NotasFrequencia();
        notasFrequencia.setTurma(turmaSelecionada.getId());
        notasFrequencia.setDisciplina(disciplinaSelecionada.getId());
        notasFrequencia.setAluno(alunoSelecionado.getId());
        notasFrequencia.setFrequencia(Integer.parseInt(edFrequencia.getText().toString()));
        notasFrequencia.setNota(Double.valueOf(edNota.getText().toString()));

        if(NotasFrequenciaDAO.salvar(notasFrequencia) > 0) {
            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnNotasFrequencia, "erro ao salvar a nota e a disciplina do aluno ("+notasFrequencia.getAluno()+") " +
                    "verifique o log", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_limpar:
                limparCampos();
                return true;
            case R.id.mn_salvar:
                validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        edFrequencia.setText("");
        edNota.setText("");
    }
}