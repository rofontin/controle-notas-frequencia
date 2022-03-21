package com.example.controlenotasfrequencia.cadastronotasfrequencia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.dao.NotasFrequenciaDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.NotasFrequencia;
import com.example.controlenotasfrequencia.domain.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class NotasFrequenciaAdapter extends RecyclerView.Adapter<NotasFrequenciaAdapter.NotasFrequenciaViewHolder> {

    private final List<Aluno> listaAlunos;
    private Context context;

    public NotasFrequenciaAdapter(List<Aluno> listaAlunos, Context context){
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    public static class NotasFrequenciaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edCodigo;
        TextInputEditText edTurma;
        TextInputEditText edAluno;
        TextInputEditText edResultado;

        public NotasFrequenciaViewHolder(@NonNull View itemView) {
            super(itemView);

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
        Aluno aluno = listaAlunos.get(position);
        List<NotasFrequencia> notasFrequencias = NotasFrequenciaDAO.retornaNotasFrequencia("aluno = " + aluno.getId(),
                new String[]{}, "codigo asc");
        Turma turma = TurmaDAO.getTurma(aluno.getTurma());

        holder.edTurma.setText(turma.getDescricao());
        holder.edAluno.setText(aluno.getNome());

        Double media = (double) 0;

        for (NotasFrequencia nota: notasFrequencias) {
            media += nota.getNota();
        }
        holder.edResultado.setText((int) (media/notasFrequencias.size()));
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }
}
