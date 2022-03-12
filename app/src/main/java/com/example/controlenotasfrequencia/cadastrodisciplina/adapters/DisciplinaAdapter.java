package com.example.controlenotasfrequencia.cadastrodisciplina.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroaluno.adapters.AlunoAdapter;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.Disciplina;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder> {

    private Context context;

    public DisciplinaAdapter(List<Disciplina> listaDisciplina, Context context){
        this.context = context;
    }



    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edCodigoDiscilplina;
        TextInputEditText edNomeDisciplina;
        TextInputEditText edCargaHoraria;

        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);

            edCodigoDiscilplina = (TextInputEditText)itemView.findViewById(R.id.edCodigoDisciplina);
            edNomeDisciplina = (TextInputEditText)itemView.findViewById(R.id.edNomeDisciplina);
            edCargaHoraria =  (TextInputEditText)itemView.findViewById(R.id.edCargaHoraria);

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
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
