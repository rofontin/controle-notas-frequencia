package com.example.controlenotasfrequencia.cadastronotasfrequencia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.domain.NotasFrequencia;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class NotasFrequenciaAdapter extends RecyclerView.Adapter<NotasFrequenciaAdapter.NotasFrequenciaViewHolder> {

    private final List<NotasFrequencia> listaNotasFrequencia;
    private Context context;

    public NotasFrequenciaAdapter(List<NotasFrequencia> listaNotasFrequencia, Context context){
        this.listaNotasFrequencia = listaNotasFrequencia;
        this.context = context;
    }

    public static class NotasFrequenciaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edCodigo;
        TextInputEditText edTurma;
        TextInputEditText edAluno;
        TextInputEditText edResultado;

        public NotasFrequenciaViewHolder(@NonNull View itemView) {
            super(itemView);

            edCodigo = itemView.findViewById(R.id.edCodigoTurma);
            edTurma = itemView.findViewById(R.id.edTurma);
            edAluno = itemView.findViewById(R.id.edNomeAluno);
            edResultado = itemView.findViewById(R.id.edResultadoFinal);
        }
    }

    @Override
    public NotasFrequenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_notas_e_frequencia, parent, false);

        return new NotasFrequenciaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotasFrequenciaViewHolder holder, int position) {
        NotasFrequencia NotasFrequencia = listaNotasFrequencia.get(position);

        holder.edCodigo.setText(NotasFrequencia.getCodigo());
        holder.edTurma.setText(NotasFrequencia.getTurma());
        holder.edAluno.setText(NotasFrequencia.getAluno());
        holder.edResultado.setText(NotasFrequencia.getResultado());
    }

    @Override
    public int getItemCount() {
        return listaNotasFrequencia.size();
    }
}
