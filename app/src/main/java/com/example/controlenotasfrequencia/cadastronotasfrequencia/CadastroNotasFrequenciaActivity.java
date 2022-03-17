package com.example.controlenotasfrequencia.cadastronotasfrequencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDAO;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.dao.NotasFrequenciaDAO;
import com.example.controlenotasfrequencia.cadastroprofessor.dao.ProfessorDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.example.controlenotasfrequencia.domain.NotasFrequencia;
import com.example.controlenotasfrequencia.domain.Professor;
import com.example.controlenotasfrequencia.domain.Turma;
import com.example.controlenotasfrequencia.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroNotasFrequenciaActivity extends AppCompatActivity {

    private MaterialSpinner spTurma;
    private MaterialSpinner spAluno;
    private MaterialSpinner spDisciplina;
    private TextInputEditText edFrequencia;
    private TextInputEditText edNota;
    private LinearLayout lnListaNotasFrequencia;
    private Turma turmaSelecionada;
    private Aluno alunoSelecionado;
    private Disciplina disciplinaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_notas_frequencia);

        edFrequencia = findViewById(R.id.edFrequencia);
        edNota = findViewById(R.id.edNota);
        lnListaNotasFrequencia = findViewById(R.id.lnListaNotasFrequencia);

        turmaSelecionada = null;
        alunoSelecionado = null;
        disciplinaSelecionada = null;

        iniciaSpinners();


    }
    private void iniciaSpinners(){
        spTurma= findViewById(R.id.spTurma);
        spAluno= findViewById(R.id.spAluno);
        spDisciplina= findViewById(R.id.spDisciplina);

        List<Turma> turma = TurmaDAO.retornaTurmas("", new String[]{}, "descricao");
        List<Aluno> aluno = AlunoDAO.retornaAlunos("", new String[]{}, "nome");
        List<Disciplina> disciplina = DisciplinaDAO.retornaDisciplina("", new String[]{}, "nome");

        ArrayAdapter adapterTurma = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  turma);
        ArrayAdapter adapterAluno = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  aluno);
        ArrayAdapter adapterDisciplina = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  turma);

        spTurma.setAdapter(adapterTurma);
        spAluno.setAdapter(adapterAluno);
        spDisciplina.setAdapter(adapterDisciplina);

        //Ação ao selecionar o item da lista
        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i >= 0){
                    turmaSelecionada = turma.get(i);
                    /*Button btADS = new Button(getBaseContext());
                    btADS.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                             LinearLayout.LayoutParams.WRAP_CONTENT));
                    btADS.setText("Botao ADS");
                    btADS.setBackgroundColor(getColor(R.color.teal_200));

                    llPrincipal.addView(btADS);*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spAluno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i >= 0){
                    alunoSelecionado = aluno.get(i);
                    /*Button btADS = new Button(getBaseContext());
                    btADS.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                             LinearLayout.LayoutParams.WRAP_CONTENT));
                    btADS.setText("Botao ADS");
                    btADS.setBackgroundColor(getColor(R.color.teal_200));

                    llPrincipal.addView(btADS);*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i >= 0){
                    disciplinaSelecionada = disciplina.get(i);
                    /*Button btADS = new Button(getBaseContext());
                    btADS.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                             LinearLayout.LayoutParams.WRAP_CONTENT));
                    btADS.setText("Botao ADS");
                    btADS.setBackgroundColor(getColor(R.color.teal_200));

                    llPrincipal.addView(btADS);*/
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
        notasFrequencia.setFrequencia(edFrequencia.getText().toString());
        notasFrequencia.setNotas(edNota.getText().toString());

        if(NotasFrequenciaDAO.salvar(notasFrequencia) > 0) {

            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnListaNotasFrequencia, "erro ao salvar a nota e a disciplina do aluno ("+notasFrequencia.getAluno()+") " +
                    "verifique o log", 0);


    }

}