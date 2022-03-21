package com.example.controlenotasfrequencia.cadastroprofessor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastroprofessor.dao.ProfessorDAO;
import com.example.controlenotasfrequencia.domain.Professor;
import com.example.controlenotasfrequencia.domain.Turma;
import com.example.controlenotasfrequencia.util.CpfMask;
import com.example.controlenotasfrequencia.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroProfessorActivity extends AppCompatActivity {

    private TextInputEditText edRegistroProfessor;
    private TextInputEditText edNomeProfessor;
    private TextInputEditText edCpfProfessor;
    private TextInputEditText edDtNascProfessor;
    private TextInputEditText edDtAdesaoProfessor;
    private MaterialSpinner spTurma;
    private LinearLayout lnPrincipal;
    private Turma turmaSelecionada;

    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        edRegistroProfessor = findViewById(R.id.edRegistro);
        edNomeProfessor = findViewById(R.id.edNomeProfessor);
        edCpfProfessor = findViewById(R.id.edCpfProfessor);
        edDtNascProfessor = findViewById(R.id.edDtNascProfessor);
        edDtAdesaoProfessor = findViewById(R.id.edDtAdesaoProfessor);
        lnPrincipal = findViewById(R.id.lnPrincipalProfessor);

        edDtNascProfessor.setFocusable(false);
        edDtAdesaoProfessor.setFocusable(false);

        edCpfProfessor.addTextChangedListener(CpfMask.insert(edCpfProfessor));
        iniciaSpinners();

        setDataAtual();
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    private void iniciaSpinners() {
        spTurma = findViewById(R.id.spTurma);
        List<Turma> turmas = TurmaDAO.retornaTurmas("", new String[]{}, "codigo");

        iniciaSpinnerTurma(turmas);
    }

    private void iniciaSpinnerTurma(List<Turma> turmas) {
        spTurma.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turmas));

        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    turmaSelecionada = turmas.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void validaCampos() {
        if (edRegistroProfessor.getText().toString().equals("")) {
            edRegistroProfessor.setError("Informe o Registro do Professor!");
            edRegistroProfessor.requestFocus();
            return;
        }

        if (edNomeProfessor.getText().toString().equals("")) {
            edNomeProfessor.setError("Informe o Nome do Professor!");
            edNomeProfessor.requestFocus();
            return;
        }

        if (edCpfProfessor.getText().toString().equals("")) {
            edCpfProfessor.setError("Informe o CPF do Professor!");
            edCpfProfessor.requestFocus();
            return;
        }

        if (edDtNascProfessor.getText().toString().equals("")) {
            edDtNascProfessor.setError("Informe a data de nascimento do Professor!");
            edDtNascProfessor.requestFocus();
            return;
        }

        if (edDtAdesaoProfessor.getText().toString().equals("")) {
            edDtAdesaoProfessor.setError("Informe a data de adesão do Professor!");
            edDtAdesaoProfessor.requestFocus();
            return;
        }

        salvarProfessor();
    }

    private void salvarProfessor() {
        Professor professor = new Professor();
        professor.setRegistro(Integer.parseInt(edRegistroProfessor.getText().toString()));
        professor.setNome(edNomeProfessor.getText().toString());
        professor.setCpf(edCpfProfessor.getText().toString());
        professor.setDtNasc(edDtNascProfessor.getText().toString());
        professor.setDtAdesao(edDtAdesaoProfessor.getText().toString());
        professor.setTurma(turmaSelecionada.getId());

        if (ProfessorDAO.salvar(professor) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o professor (" + professor.getNome() + ") " +
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
        edRegistroProfessor.setText("");
        edNomeProfessor.setText("");
        edCpfProfessor.setText("");
        edDtNascProfessor.setText("");
        edDtAdesaoProfessor.setText("");
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
        TextInputEditText edit = (TextInputEditText) dataSelecionada;
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