package com.example.controlenotasfrequencia.cadastrodisciplina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroprofessor.dao.ProfessorDAO;
import com.example.controlenotasfrequencia.domain.Professor;
import com.example.controlenotasfrequencia.util.Util;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText edCodigoDisciplina;
    private TextInputEditText edNomeDisciplina;
    private TextInputEditText edCargaHoraria;
    private LinearLayout lnDisciplina;
    private MaterialSpinner spProfessor;
    private Professor profSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        edCodigoDisciplina = findViewById(R.id.edCodigoDisciplina);
        edNomeDisciplina = findViewById(R.id.edNomeDisciplina);
        edCargaHoraria = findViewById(R.id.edCargaHoraria);
        lnDisciplina = findViewById(R.id.lnDisciplina);

        profSelecionado = null;

        iniciaSpinners();


    }
    private void iniciaSpinners(){
        spProfessor= findViewById(R.id.spProfessor);

        List<Professor> professores = ProfessorDAO.retornaProfessores("", new String[]{}, "nome");

        ArrayAdapter adapterProfessores = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  professores);

        spProfessor.setAdapter(adapterProfessores);

        //Ação ao selecionar o item da lista
        spProfessor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i >= 0){
                    profSelecionado = professores.get(i);
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

        if(edCodigoDisciplina.getText().toString().equals("")){
            edCodigoDisciplina.setError("Informe o Codigo da Disciplina!");
            edCodigoDisciplina.requestFocus();
            return;
        }


        if(edNomeDisciplina.getText().toString().equals("")){
            edNomeDisciplina.setError("Informe o Nome da Disciplina!");
            edNomeDisciplina.requestFocus();
            return;
        }


        if(edCargaHoraria.getText().toString().equals("")){
            edCargaHoraria.setError("Informe a carga Horária da Disciplina!");
            edCargaHoraria.requestFocus();
            return;
        }

        if(profSelecionado == null){
            spProfessor.setError("Informe o professor da Disciplina!");
            spProfessor.requestFocus();
            return;
        }

        salvarDisciplina();
    }
    public void salvarDisciplina(){
        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(Integer.parseInt(edCodigoDisciplina.getText().toString()));
        disciplina.setNome(edNomeDisciplina.getText().toString());
        disciplina.setCargaHoraria(edCargaHoraria.getText().toString());
        disciplina.setProfessor(profSelecionado.getNome());

        if(DisciplinaDAO.salvar(disciplina) > 0) {

            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnDisciplina, "erro ao salvar a disciplina ("+disciplina.getNome()+") " +
                    "verifique o log", 0);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    private void limparCampos() {
        edCodigoDisciplina.setText("");
        edNomeDisciplina.setText("");
        edCargaHoraria.setText("");
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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


}
