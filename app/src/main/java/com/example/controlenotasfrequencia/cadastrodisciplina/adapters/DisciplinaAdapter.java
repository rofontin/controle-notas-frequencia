package com.example.controlenotasfrequencia.cadastrodisciplina.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroaluno.dao.AlunoDisciplinaDAO;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.dao.NotasFrequenciaDAO;
import com.example.controlenotasfrequencia.cadastroprofessor.dao.ProfessorDAO;
import com.example.controlenotasfrequencia.domain.AlunoDisciplina;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.example.controlenotasfrequencia.domain.NotasFrequencia;
import com.example.controlenotasfrequencia.domain.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder> {

    private final List<Disciplina> listaDisciplina;
    private Context context;
    private OnDisciplinaListener mOnDisciplinaListener;

    public DisciplinaAdapter(List<Disciplina> listaDisciplina, Context context, OnDisciplinaListener onDisciplinaListener){
        this.listaDisciplina = listaDisciplina;
        this.context = context;
        this.mOnDisciplinaListener = onDisciplinaListener;
    }

    public void delete(int position){
        listaDisciplina.remove(position);
        notifyItemRemoved(position);
    }

    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextInputEditText edCodigoDiscilplina;
        TextInputEditText edNomeDisciplina;
        TextInputEditText edCargaHoraria;
        TextInputEditText edProfessor;
        Button deletar;

        OnDisciplinaListener onDisciplinaListener;

        public DisciplinaViewHolder(@NonNull View itemView, OnDisciplinaListener onDisciplinaListener) {
            super(itemView);
            this.onDisciplinaListener = onDisciplinaListener;

            edCodigoDiscilplina = itemView.findViewById(R.id.edCodigoDisciplina);
            edNomeDisciplina = itemView.findViewById(R.id.edNomeDisciplina);
            edCargaHoraria = itemView.findViewById(R.id.edCargaHoraria);
            edProfessor = itemView.findViewById(R.id.edProfessor);
            deletar = itemView.findViewById(R.id.deletar);

            edCodigoDiscilplina.setOnClickListener(this);
            edNomeDisciplina.setOnClickListener(this);
            edCargaHoraria.setOnClickListener(this);
            edProfessor.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onDisciplinaListener.onDisciplinalick(getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_disciplina, parent, false);

        return new DisciplinaViewHolder(view, mOnDisciplinaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplina.get(position);

        holder.edCargaHoraria.setText(String.valueOf(disciplina.getCargaHoraria()));
        holder.edCodigoDiscilplina.setText(String.valueOf(disciplina.getCodigo()));
        holder.edNomeDisciplina.setText(disciplina.getNome());

        Professor professor = ProfessorDAO.getProfessor(disciplina.getProfessor());
        holder.edProfessor.setText(professor.getNome());

        holder.deletar.setOnClickListener(view -> {
            List<NotasFrequencia> notasFrequenciaByDisciplina = NotasFrequenciaDAO.getNotasFrequenciaByDisciplina(disciplina.getId());

            if (!notasFrequenciaByDisciplina.isEmpty()) {
                holder.deletar.setError("N??o ?? poss??vel remover a Disciplina, pois possui notas lan??adas.");
                return;
            }

            List<AlunoDisciplina> alunoDisciplinaByDisciplina = AlunoDisciplinaDAO.getAlunoDisciplinaByDisciplina(disciplina.getId());
            AlunoDisciplinaDAO.deleteInBatch(alunoDisciplinaByDisciplina);
            DisciplinaDAO.delete(disciplina);
            delete(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaDisciplina.size();
    }

    public interface OnDisciplinaListener{
        void onDisciplinalick(int position);
    }
}
