package com.example.controlenotasfrequencia.cadastroprofessor.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.cadastroprofessor.dao.ProfessorDAO;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.example.controlenotasfrequencia.domain.Professor;
import com.example.controlenotasfrequencia.domain.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Optional;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {

    private final List<Professor> listaProfessores;
    private Context context;
    private OnProfessorListener mOnProfessorListener;

    public ProfessorAdapter(List<Professor> listaProfessores, Context context, OnProfessorListener onProfessorListener) {
        this.listaProfessores = listaProfessores;
        this.context = context;
        this.mOnProfessorListener = onProfessorListener;
    }

    public void delete(int position){
        listaProfessores.remove(position);
        notifyItemRemoved(position);
    }

    public static class ProfessorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextInputEditText edRegistro;
        TextInputEditText edNomeProfessor;
        TextInputEditText edCpfProfessor;
        TextInputEditText edTurma;
        TextInputEditText edDisciplina;
        TextInputEditText edDtAdesao;
        TextInputEditText edDtNasc;
        Button deletar;

        OnProfessorListener onProfessorListener;
        public ProfessorViewHolder(@NonNull View itemView, OnProfessorListener onProfessorListener) {
            super(itemView);

            edRegistro = itemView.findViewById(R.id.edRegistro);
            edNomeProfessor = itemView.findViewById(R.id.edNomeProfessor);
            edCpfProfessor = itemView.findViewById(R.id.edCpfProfessor);
            edTurma = itemView.findViewById(R.id.edTurmaProfessor);
            edDisciplina = itemView.findViewById(R.id.edDisciplinaProfessor);
            edDtAdesao = itemView.findViewById(R.id.edDtAdesaoProfessor);
            edDtNasc = itemView.findViewById(R.id.edDtNascProfessor);
            deletar = itemView.findViewById(R.id.deletar);

            edRegistro.setOnClickListener(this);
            edNomeProfessor.setOnClickListener(this);
            edCpfProfessor.setOnClickListener(this);
            edDisciplina.setOnClickListener(this);
            edTurma.setOnClickListener(this);
            edDtAdesao.setOnClickListener(this);
            edDtNasc.setOnClickListener(this);

            this.onProfessorListener = onProfessorListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onProfessorListener.onProfessorClick(getAdapterPosition());
        }
    }

    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_professor, parent, false);

        return new ProfessorAdapter.ProfessorViewHolder(view,mOnProfessorListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor professor = listaProfessores.get(position);

        holder.edRegistro.setText(String.valueOf(professor.getRegistro()));
        holder.edCpfProfessor.setText(professor.getCpf());
        holder.edNomeProfessor.setText(professor.getNome());

        Turma turma = TurmaDAO.getTurma(professor.getTurma());
        holder.edTurma.setText(turma.getDescricao());

        Optional<Disciplina> disciplina = DisciplinaDAO.getDisciplinaByProfessor(professor.getId());

        if(disciplina.isPresent()){
            holder.edDisciplina.setText(disciplina.get().getNome());
        }else{
            holder.edDisciplina.setText("Sem Disciplina!");
        }

        holder.edDtAdesao.setText(professor.getDtAdesao());
        holder.edDtNasc.setText(professor.getDtNasc());

        holder.deletar.setOnClickListener(view -> {
            if(disciplina.isPresent()){
                holder.deletar.setError("N??o ?? poss??vel remover o professor, " +
                        "pois possui v??nculo com Disciplina: " + disciplina.get().getNome());
                return;
            }
            ProfessorDAO.delete(professor);
            delete(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaProfessores.size();
    }

    public interface OnProfessorListener {
        void onProfessorClick(int position);
    }
}
