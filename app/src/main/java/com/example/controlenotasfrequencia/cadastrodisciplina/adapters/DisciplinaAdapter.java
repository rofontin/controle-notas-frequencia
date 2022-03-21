package com.example.controlenotasfrequencia.cadastrodisciplina.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroprofessor.dao.ProfessorDAO;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.example.controlenotasfrequencia.domain.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder> {

    private final List<Disciplina> listaDisciplina;
    private Context context;

    public DisciplinaAdapter(List<Disciplina> listaDisciplina, Context context){
        this.listaDisciplina = listaDisciplina;
        this.context = context;
    }

    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edCodigoDiscilplina;
        TextInputEditText edNomeDisciplina;
        TextInputEditText edCargaHoraria;
        TextInputEditText edProfessor;

        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);

            edCodigoDiscilplina = itemView.findViewById(R.id.edCodigoDisciplina);
            edNomeDisciplina = itemView.findViewById(R.id.edNomeDisciplina);
            edCargaHoraria = itemView.findViewById(R.id.edCargaHoraria);
            edProfessor = itemView.findViewById(R.id.edProfessor);
        }
    }
    @NonNull
    @Override
    public DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_disciplina, parent, false);

        return new DisciplinaViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull DisciplinaViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplina.get(position);

        holder.edCargaHoraria.setText(disciplina.getCargaHoraria());
        holder.edCodigoDiscilplina.setText(String.valueOf(disciplina.getCodigo()));
        holder.edNomeDisciplina.setText(disciplina.getNome());

        Professor professor = ProfessorDAO.getProfessor(disciplina.getProfessor());
        holder.edProfessor.setText(professor.getNome());
    }

    @Override
    public int getItemCount() {
        return listaDisciplina.size();
    }
}
