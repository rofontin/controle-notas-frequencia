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

public class NotasFrequenciaAdapter extends RecyclerView.Adapter<NotasFrequenciaAdapter.CadastroNotaseFrequenciaViewHolder> {

    private List<NotasFrequencia> listaNotaseFrequencia;
    private Context context;

    public NotasFrequenciaAdapter(List<NotasFrequencia> listaNotaseFrequencia, Context context){
        this.listaNotaseFrequencia = listaNotaseFrequencia;
        this.context = context;
    }



    public static class CadastroNotaseFrequenciaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edCodigo;
        TextInputEditText edTurma;
        TextInputEditText edAluno;
        TextInputEditText edResultado;

        public CadastroNotaseFrequenciaViewHolder(@NonNull View itemView) {
            super(itemView);

            edCodigo = (TextInputEditText)itemView.findViewById(R.id.edCodigoTurma);
            edTurma = (TextInputEditText)itemView.findViewById(R.id.edTurma);
            edAluno =  (TextInputEditText)itemView.findViewById(R.id.edNomeAluno);
            edResultado =  (TextInputEditText)itemView.findViewById(R.id.edResultadoFinal);

        }
    }
    @NonNull
    @Override
    public CadastroNotaseFrequenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_notas_e_frequencia, parent, false);

        NotasFrequenciaAdapter.CadastroNotaseFrequenciaViewHolder viewHolder = new NotasFrequenciaAdapter.CadastroNotaseFrequenciaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CadastroNotaseFrequenciaViewHolder holder, int position) {
        NotasFrequencia NotasFrequencia = listaNotaseFrequencia.get(position);

        holder.edCodigo.setText(NotasFrequencia.getCodigo());
        holder.edTurma.setText(NotasFrequencia.getTurma().toString());
        holder.edAluno.setText(NotasFrequencia.getAluno().toString());
        holder.edResultado.setText(NotasFrequencia.getResultado().toString());

    }

    @Override
    public int getItemCount() {
        return listaNotaseFrequencia.size();
    }
}
