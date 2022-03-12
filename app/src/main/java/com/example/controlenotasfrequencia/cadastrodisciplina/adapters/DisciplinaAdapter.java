package com.example.controlenotasfrequencia.cadastrodisciplina.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder> {

    private List<Disciplina> listaDisciplina;
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

            edCodigoDiscilplina = (TextInputEditText)itemView.findViewById(R.id.edCodigoDisciplina);
            edNomeDisciplina = (TextInputEditText)itemView.findViewById(R.id.edNomeDisciplina);
            edCargaHoraria =  (TextInputEditText)itemView.findViewById(R.id.edCargaHoraria);
            edProfessor =  (TextInputEditText)itemView.findViewById(R.id.edProfessor);

        }
    }
    @NonNull
    @Override
    public DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_disciplina, parent, false);

        DisciplinaAdapter.DisciplinaViewHolder viewHolder = new DisciplinaAdapter.DisciplinaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplina.get(position);

        holder.edCargaHoraria.setText(disciplina.getCargaHoraria().toString());
        holder.edCodigoDiscilplina.setText(String.valueOf(disciplina.getCodigo()));
        holder.edNomeDisciplina.setText(disciplina.getNome().toString());
        holder.edProfessor.setText(disciplina.getProfessor().toString());
    }

    @Override
    public int getItemCount() {
        return listaDisciplina.size();
    }
}
