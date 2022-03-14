package com.example.controlenotasfrequencia.cadastroprofessor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.domain.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {

    private List<Professor> listaProfessores;
    private Context context;

    public ProfessorAdapter(List<Professor> listaProfessores, Context context) {
        this.listaProfessores = listaProfessores;
        this.context = context;
    }

    public static class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRegistro;
        TextInputEditText edNomeProfessor;
        TextInputEditText edCpfProfessor;
        TextInputEditText edTurma;
        TextInputEditText edDisciplina;
        TextInputEditText edDtAdesao;
        TextInputEditText edDtNasc;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);

            edRegistro = itemView.findViewById(R.id.edRegistro);
            edNomeProfessor = itemView.findViewById(R.id.edNomeProfessor);
            edCpfProfessor = itemView.findViewById(R.id.edCpfProfessor);
            edTurma = itemView.findViewById(R.id.edTurmaProfessor);
            edDisciplina = itemView.findViewById(R.id.edDisciplinaProfessor);
            edDtAdesao = itemView.findViewById(R.id.edDtAdesaoProfessor);
            edDtNasc = itemView.findViewById(R.id.edDtNascProfessor);
        }
    }

    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_professor, parent, false);

        return new ProfessorAdapter.ProfessorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor professor = listaProfessores.get(position);

        holder.edRegistro.setText(String.valueOf(professor.getRegistro()));
        holder.edCpfProfessor.setText(professor.getCpf());
        holder.edNomeProfessor.setText(professor.getNome());
        holder.edTurma.setText(professor.getTurma());
        holder.edDisciplina.setText(professor.getDisciplina());
        holder.edDtAdesao.setText(professor.getDtAdesao());
        holder.edDtNasc.setText(professor.getDtNasc());
    }

    @Override
    public int getItemCount() {
        return listaProfessores.size();
    }
}
