package com.example.controlenotasfrequencia.cadastroaluno.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private final List<Aluno> listaAlunos;
    private Context context;

    public AlunoAdapter(List<Aluno> listaAlunos, Context context) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edCpfAluno;
        TextInputEditText edTurma;
        TextInputEditText edDtMatricula;
        TextInputEditText edDtNasc;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaAluno = itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = itemView.findViewById(R.id.edNomeAluno);
            edCpfAluno = itemView.findViewById(R.id.edCpfAluno);
            edTurma = itemView.findViewById(R.id.edTurma);
            edDtMatricula = itemView.findViewById(R.id.edDtMatricula);
            edDtNasc = itemView.findViewById(R.id.edDtNascAluno);

        }
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_aluno, parent, false);

        AlunoAdapter.AlunoViewHolder viewHolder = new AlunoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);

        holder.edRaAluno.setText(String.valueOf(aluno.getRa()));
        holder.edCpfAluno.setText(aluno.getCpf());
        holder.edNomeAluno.setText(aluno.getNome());

        Turma turma = TurmaDAO.getTurma(aluno.getTurma());
        holder.edTurma.setText(turma.getDescricao());

        holder.edDtMatricula.setText(aluno.getDtMatricula());
        holder.edDtNasc.setText(aluno.getDtNasc());

    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }
}
