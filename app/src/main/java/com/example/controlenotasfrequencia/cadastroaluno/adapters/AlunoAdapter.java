package com.example.controlenotasfrequencia.cadastroaluno.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDAO;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDisciplinaDAO;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.dao.NotasFrequenciaDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.AlunoDisciplina;
import com.example.controlenotasfrequencia.domain.NotasFrequencia;
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

    public void delete(int position){
        listaAlunos.remove(position);
        notifyItemRemoved(position);
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edCpfAluno;
        TextInputEditText edTurma;
        TextInputEditText edDtMatricula;
        TextInputEditText edDtNasc;
        Button deletar;
        CardView cardAluno;

        @SuppressLint("ResourceType")
        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaAluno = itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = itemView.findViewById(R.id.edNomeAluno);
            edCpfAluno = itemView.findViewById(R.id.edCpfAluno);
            edTurma = itemView.findViewById(R.id.edTurma);
            edDtMatricula = itemView.findViewById(R.id.edDtMatricula);
            edDtNasc = itemView.findViewById(R.id.edDtNascAluno);
            deletar = itemView.findViewById(R.id.deletar);
            cardAluno = itemView.findViewById(R.layout.card_view_aluno);

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

        holder.deletar.setOnClickListener(view -> {
            List<NotasFrequencia> notasFrequenciaByAluno = NotasFrequenciaDAO.getNotasFrequenciaByAluno(aluno.getId());

            if (!notasFrequenciaByAluno.isEmpty()) {
                holder.deletar.setError("Não é possível remover o Aluno, pois possui notas lançadas.");
                return;
            }

            List<AlunoDisciplina> alunoDisciplinaByAluno = AlunoDisciplinaDAO.getAlunoDisciplinaByAluno(aluno.getId());
            AlunoDisciplinaDAO.deleteInBatch(alunoDisciplinaByAluno);
            AlunoDAO.delete(aluno);
            delete(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

}
