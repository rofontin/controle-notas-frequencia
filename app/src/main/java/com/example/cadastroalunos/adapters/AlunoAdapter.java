package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Aluno;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private List<Aluno> listaAlunos;
    private Context context;

    public AlunoAdapter(List<Aluno> listaAlunos, Context context) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edCpfAluno;
        TextInputEditText edCurso;
        TextInputEditText edPeriodo;
        TextInputEditText edDtMatricula;
        TextInputEditText edDtNasc;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaAluno = (TextInputEditText)itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = (TextInputEditText)itemView.findViewById(R.id.edNomeAluno);
            edCpfAluno =  (TextInputEditText)itemView.findViewById(R.id.edCpfAluno);
            edCurso = (TextInputEditText)itemView.findViewById(R.id.edCursoAluno);
            edPeriodo = (TextInputEditText)itemView.findViewById(R.id.edPeriodoAluno);
            edDtMatricula = (TextInputEditText)itemView.findViewById(R.id.edDtMatricula);
            edDtNasc = (TextInputEditText)itemView.findViewById(R.id.edDtNascAluno);

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
        holder.edCurso.setText(aluno.getCurso());
        holder.edPeriodo.setText(aluno.getPeriodo());
        holder.edDtMatricula.setText(aluno.getDtMatricula());
        holder.edDtNasc.setText(aluno.getDtNasc());

    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }


}
