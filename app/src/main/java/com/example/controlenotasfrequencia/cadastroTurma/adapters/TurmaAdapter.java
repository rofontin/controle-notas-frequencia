package com.example.controlenotasfrequencia.cadastroTurma.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDAO;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.dao.NotasFrequenciaDAO;
import com.example.controlenotasfrequencia.cadastroprofessor.dao.ProfessorDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.NotasFrequencia;
import com.example.controlenotasfrequencia.domain.Professor;
import com.example.controlenotasfrequencia.domain.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {

    private final List<Turma> listaTurma;
    private Context context;

    public TurmaAdapter(List<Turma> listaTurma, Context context) {
        this.listaTurma = listaTurma;
        this.context = context;
    }

    public static class TurmaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edCodigo;
        TextInputEditText edDescricao;
        TextInputEditText edTurno;
        TextInputEditText edRegime;
        TextInputEditText edPeriodo;
        TextInputEditText edDtInicio;
        TextInputEditText edDtTermino;
        Button deletar;

        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);

            edCodigo = itemView.findViewById(R.id.edCodigoTurma);
            edDescricao = itemView.findViewById(R.id.edDescricaoTurma);
            edTurno = itemView.findViewById(R.id.edTurnoTurma);
            edRegime = itemView.findViewById(R.id.edRegimeTurma);
            edPeriodo = itemView.findViewById(R.id.edPeriodoTurma);
            edDtInicio = itemView.findViewById(R.id.edDtInicioTurma);
            edDtTermino = itemView.findViewById(R.id.edDtTerminoTurma);
            deletar = itemView.findViewById(R.id.deletar);
        }
    }

    @Override
    public TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_turma, parent, false);

        return new TurmaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaViewHolder holder, int position) {
        Turma turma = listaTurma.get(position);

        holder.edCodigo.setText(String.valueOf(turma.getCodigo()));
        holder.edDescricao.setText(turma.getDescricao());
        holder.edTurno.setText(turma.getTurno().toString());
        holder.edRegime.setText(turma.getRegime().toString());
        holder.edPeriodo.setText(turma.getPeriodo().toString());
        holder.edDtInicio.setText(turma.getDtInicio());
        holder.edDtTermino.setText(turma.getDtTermino());

        holder.deletar.setOnClickListener(view -> {
            validateAndDelete(holder, turma);
        });
    }

    private void validateAndDelete(@NonNull TurmaViewHolder holder, Turma turma) {
        List<NotasFrequencia> notasFrequenciaByAluno = NotasFrequenciaDAO.getNotasFrequenciaByTurma(turma.getId());
        if (!notasFrequenciaByAluno.isEmpty()) {
            holder.deletar.setError("Não é possível remover a Turma, pois possui notas lançadas.");
            return;
        }

        List<Aluno> alunoByTurma = AlunoDAO.getAlunoByTurma(turma.getId());
        if (!alunoByTurma.isEmpty()) {
            holder.deletar.setError("Não é possível remover a Turma, pois possui Alunos vinculados.");
            return;
        }

        List<Professor> professoresByTurma = ProfessorDAO.getProfessoresByTurma(turma.getId());
        if (!professoresByTurma.isEmpty()) {
            holder.deletar.setError("Não é possível remover a Turma, pois possui Professores vinculados.");
            return;
        }
        TurmaDAO.delete(turma);
    }

    @Override
    public int getItemCount() {
        return listaTurma.size();
    }
}
