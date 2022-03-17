package com.example.controlenotasfrequencia.cadastroaluno;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bytcode.lib.spinner.multiselectspinner.interfaces.MultiSpinnerListener;
import com.bytcode.lib.spinner.multiselectspinner.spinners.MultiSpinner;
import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDAO;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.example.controlenotasfrequencia.domain.Turma;
import com.example.controlenotasfrequencia.util.CpfMask;
import com.example.controlenotasfrequencia.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroAlunoActivity extends AppCompatActivity {

    private TextInputEditText edRaAluno;
    private TextInputEditText edNomeAluno;
    private TextInputEditText edCpfAluno;
    private TextInputEditText edDtNascAluno;
    private TextInputEditText edDtMatAluno;
    private MaterialSpinner spTurma;
    private MultiSpinner spDisciplina;
    private LinearLayout lnPrincipal;

    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        edRaAluno = findViewById(R.id.edRaAluno);
        edNomeAluno = findViewById(R.id.edNomeAluno);
        edCpfAluno = findViewById(R.id.edCpfAluno);
        edDtNascAluno = findViewById(R.id.edDtNascAluno);
        edDtMatAluno = findViewById(R.id.edDtMatAluno);
        lnPrincipal = findViewById(R.id.lnPrincipalAluno);

        edDtNascAluno.setFocusable(false);
        edDtMatAluno.setFocusable(false);
        edCpfAluno.addTextChangedListener(CpfMask.insert(edCpfAluno));

        iniciaSpinners();
        setDataAtual();
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void iniciaSpinners(){
        spTurma = findViewById(R.id.spTurma);
        spDisciplina = findViewById(R.id.spDisciplina);

        List<Turma> turma = TurmaDAO.retornaTurmas("", new String[]{}, "descricao");
        List<Disciplina> disciplinas = DisciplinaDAO.retornaDisciplina("", new String[]{}, "nome");
        spTurma.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  turma));
        LinkedHashMap<String, Boolean> objectObjectHashMap = new LinkedHashMap<>();

        disciplinas.forEach(disciplina -> {
            objectObjectHashMap.put(disciplina.toString(), Boolean.FALSE);
        });

        objectObjectHashMap.put("ai", Boolean.FALSE);
        spDisciplina.setItems(objectObjectHashMap, selected -> {
            for(int i=0; i<selected.length; i++) {
                if(selected[i]) {
//                    Log.i("TAG", i + " : "+ disciplinas.get(i));
                }
            }
        });

        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i >= 0){
//                    turmaSelecionada = turma.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void validaCampos(){
        if(edRaAluno.getText().toString().equals("")){
            edRaAluno.setError("Informe o RA do Aluno!");
            edRaAluno.requestFocus();
            return;
        }

        if(edNomeAluno.getText().toString().equals("")){
            edNomeAluno.setError("Informe o Nome do Aluno!");
            edNomeAluno.requestFocus();
            return;
        }

        if(edCpfAluno.getText().toString().equals("")){
            edCpfAluno.setError("Informe o CPF do Aluno!");
            edCpfAluno.requestFocus();
            return;
        }

        if(edDtNascAluno.getText().toString().equals("")){
            edDtNascAluno.setError("Informe a data de nascimento do Aluno!");
            edDtNascAluno.requestFocus();
            return;
        }

        if(edDtMatAluno.getText().toString().equals("")){
            edDtMatAluno.setError("Informe a data de matricula do Aluno!");
            edDtMatAluno.requestFocus();
            return;
        }

        salvarAluno();
    }

    public void salvarAluno(){
        Aluno aluno = new Aluno();
        aluno.setRa(Integer.parseInt(edRaAluno.getText().toString()));
        aluno.setNome(edNomeAluno.getText().toString());
        aluno.setCpf(edCpfAluno.getText().toString());
        aluno.setDtNasc(edDtNascAluno.getText().toString());
        aluno.setDtMatricula(edDtMatAluno.getText().toString());
        aluno.setTurma(spTurma.getSelectedItem().toString());

        //TODO pegar disciplinas listadas e salvar um AlunoDisciplina

        if(AlunoDAO.salvar(aluno) > 0) {

            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o aluno ("+aluno.getNome()+") " +
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
        switch (item.getItemId()){
            case R.id.mn_limpar:
                //TODO: adicionar método  de limpar dados
                limparCampos();
                return true;
            case R.id.mn_salvar:
                //TODO: adicionar método  de salvar dados
                validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        edRaAluno.setText("");
        edNomeAluno.setText("");
        edCpfAluno.setText("");
        edDtNascAluno.setText("");
        edDtMatAluno.setText("");
    }

    public void selecionarData(View view) {
        dataSelecionada = view;
        showDialog(0);
    }

    private DatePickerDialog.OnDateSetListener setDatePicker =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    vAno = year;
                    vMes = month;
                    vDia = day;

                    atualizaData();
                }
            };

    private void atualizaData() {
        TextInputEditText edit = (TextInputEditText)dataSelecionada;
        edit.setText(new StringBuilder().append(vDia).append("/")
                .append(vMes + 1).append("/")
                .append(vAno));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        setDataAtual();
        return new DatePickerDialog(this, setDatePicker,
                vAno, vMes, vDia);
    }
}