package com.example.controlenotasfrequencia.cadastrodisciplina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.util.Util;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText edCodigoDisciplina;
    private TextInputEditText edNomeDisciplina;
    private TextInputEditText edCargaHoraria;
    private LinearLayout lnDisciplina;
    private LinearLayout lnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        edCodigoDisciplina = findViewById(R.id.edCodigoDisciplina);
        edNomeDisciplina = findViewById(R.id.edNomeDisciplina);
        edCargaHoraria = findViewById(R.id.edCargaHoraria);
        lnDisciplina = findViewById(R.id.lnDisciplina);


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

        salvarDisciplina();
    }
    public void salvarDisciplina(){
        Disciplina disciplina = new Disciplina();
        disciplina.setCodigoDisciplina(Integer.parseInt(edCodigoDisciplina.getText().toString()));
        disciplina.setNomeDisciplina(edNomeDisciplina.getText().toString());
        disciplina.setCargaHoraria(edCargaHoraria.getText().toString());

        if(DisciplinaDAO.salvar(disciplina) > 0) {

            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnDisciplina, "erro ao salvar a disciplina ("+disciplina.getNomeDisciplina()+") " +
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
