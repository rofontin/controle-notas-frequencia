package com.example.controlenotasfrequencia.cadastronotasfrequencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastroprofessor.dao.ProfessorDAO;
import com.example.controlenotasfrequencia.domain.Professor;
import com.example.controlenotasfrequencia.domain.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroNotasFrequenciaActivity extends AppCompatActivity {

    private MaterialSpinner spTurma;
    private MaterialSpinner spAluno;
    private MaterialSpinner spDisciplina;
    private TextInputEditText edFrequencia;
    private TextInputEditText edNota;
    private Turma turmaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_notas_frequencia);

        edFrequencia = findViewById(R.id.edFrequencia);
        edNota = findViewById(R.id.edNota);

        turmaSelecionada = null;

        iniciaSpinners();


    }
    private void iniciaSpinners(){
        spTurma= findViewById(R.id.spTurma);
        spAluno= findViewById(R.id.spAluno);
        spDisciplina= findViewById(R.id.spDisciplina);

        List<Turma> turma = TurmaDAO.retornaTurmas("", new String[]{}, "nome");

        ArrayAdapter adapterTurma = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  turma);

        spTurma.setAdapter(adapterTurma);

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


    }
}