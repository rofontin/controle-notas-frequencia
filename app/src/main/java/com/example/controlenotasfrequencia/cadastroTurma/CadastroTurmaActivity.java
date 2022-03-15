package com.example.controlenotasfrequencia.cadastroTurma;

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
import com.example.controlenotasfrequencia.cadastroTurma.enumTurma.Periodo;
import com.example.controlenotasfrequencia.cadastroTurma.enumTurma.Regime;
import com.example.controlenotasfrequencia.cadastroTurma.enumTurma.Turno;
import com.example.controlenotasfrequencia.domain.Turma;
import com.example.controlenotasfrequencia.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaActivity extends AppCompatActivity {

    private TextInputEditText edCodigo;
    private TextInputEditText edDescricao;
    private TextInputEditText edDtInicio;
    private TextInputEditText edDtTermino;
    private MaterialSpinner spTurno;
    private MaterialSpinner spRegime;
    private MaterialSpinner spPeriodo;
    private LinearLayout lnPrincipal;

    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        edCodigo = findViewById(R.id.edCodigoTurma);
        edDescricao = findViewById(R.id.edDescricaoTurma);
        edDtInicio = findViewById(R.id.edDtInicioTurma);
        edDtTermino = findViewById(R.id.edDtTerminoTurma);
        lnPrincipal = findViewById(R.id.lnPrincipalTurma);

        edDtInicio.setFocusable(false);
        edDtTermino.setFocusable(false);

        iniciaSpinners();
        setDataAtual();
    }

    private void iniciaSpinners() {
        spRegime = findViewById(R.id.spRegimeTurma);
        spTurno = findViewById(R.id.spTurnoTurma);
        spPeriodo = findViewById(R.id.spPeriodoTurma);

        String[] regimes = new String[]{Regime.ANUAL.toString(), Regime.SEMESTRAL.toString()};
        String[] turnos = new String[]{Turno.MANHA.toString(), Turno.TARDE.toString(), Turno.NOITE.toString()};

        spTurno.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turnos));
        spRegime.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, regimes));

        spRegime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] periodos = new String[]{};
                if (i != -1) {
                    if (regimes[i].equals(Regime.SEMESTRAL.toString())) {
                        periodos = new String[]{Periodo.SEMESTRE_1.toString(), Periodo.SEMESTRE_2.toString()};
                    } else {
                        periodos = new String[]{Periodo.BIMESTRE_1.toString(), Periodo.BIMESTRE_2.toString(),
                                Periodo.BIMESTRE_3.toString(), Periodo.BIMESTRE_4.toString()};
                    }
                }
                spPeriodo.setAdapter(new ArrayAdapter(CadastroTurmaActivity.this, android.R.layout.simple_list_item_1, periodos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    private void validaCampos() {
        if (edCodigo.getText().toString().equals("")) {
            edCodigo.setError("Informe o Código da Turma!");
            edCodigo.requestFocus();
            return;
        }

        if (edDescricao.getText().toString().equals("")) {
            edDescricao.setError("Informe a descrição da turma!");
            edDescricao.requestFocus();
            return;
        }

        if (edDtInicio.getText().toString().equals("")) {
            edDtInicio.setError("Informe a data de início");
            edDtInicio.requestFocus();
            return;
        }

        if (edDtTermino.getText().toString().equals("")) {
            edDtTermino.setError("Informe a data de término!");
            edDtTermino.requestFocus();
            return;
        }

        salvarTurma();
    }

    public void salvarTurma() {
        Turma turma = new Turma();
        turma.setCodigo(Integer.parseInt(edCodigo.getText().toString()));
        turma.setDescricao(edDescricao.getText().toString());
        turma.setDtInicio(edDtInicio.getText().toString());
        turma.setDtTermino(edDtTermino.getText().toString());
        turma.setRegime(Regime.fromString(spRegime.getSelectedItem().toString()));
        turma.setTurno(Turno.fromString(spTurno.getSelectedItem().toString()));
        turma.setPeriodo(Periodo.fromString(spPeriodo.getSelectedItem().toString()));

        if (TurmaDAO.salvar(turma) > 0) {

            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar a turma", 0);
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
        edCodigo.setText("");
        edDescricao.setText("");
        edDtInicio.setText("");
        edDtTermino.setText("");
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