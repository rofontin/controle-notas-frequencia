package com.example.controlenotasfrequencia.cadastronotasfrequencia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlenotasfrequencia.R;
import com.example.controlenotasfrequencia.cadastroTurma.dao.TurmaDAO;
import com.example.controlenotasfrequencia.cadastrodisciplina.dao.DisciplinaDAO;
import com.example.controlenotasfrequencia.cadastronotasfrequencia.dao.NotasFrequenciaDAO;
import com.example.controlenotasfrequencia.domain.Aluno;
import com.example.controlenotasfrequencia.domain.Disciplina;
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

    public void delete(int position){
        listaAlunos.remove(position);
        notifyItemRemoved(position);
    }

    public static class NotasFrequenciaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edTurma;
        TextInputEditText edAluno;
        TextInputEditText edResultado;
        TextInputEditText edFrequencia;
        TextInputEditText edMediaFinal;
        Button deletar;

        public NotasFrequenciaViewHolder(@NonNull View itemView) {
            super(itemView);

            edTurma = itemView.findViewById(R.id.edTurma);
            edAluno = itemView.findViewById(R.id.edNomeAluno);
            edResultado = itemView.findViewById(R.id.edResultadoFinal);
            edFrequencia = itemView.findViewById(R.id.edFrequencia);
            edMediaFinal = itemView.findViewById(R.id.edMedia);
            deletar = itemView.findViewById(R.id.deletar);
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
        List<NotasFrequencia> notasFrequencias = NotasFrequenciaDAO.getNotasFrequenciaByAluno(aluno.getId());

        Turma turma = TurmaDAO.getTurma(aluno.getTurma());
        holder.edTurma.setText(turma.getDescricao());
        holder.edAluno.setText(aluno.getNome());
        holder.edResultado.setText("Sem notas lançadas");
        holder.edFrequencia.setText("0%");
        holder.edMediaFinal.setText("0.0");
        calculaMediaEFrequencia(holder, notasFrequencias);

        holder.deletar.setOnClickListener(view -> {
            if(!notasFrequencias.isEmpty()){
                NotasFrequenciaDAO.deleteInBatch(notasFrequencias);
                notifyItemRemoved(position);
            }
        });
    }

    private void calculaMediaEFrequencia(@NonNull NotasFrequenciaViewHolder holder, List<NotasFrequencia> notasFrequencias) {
        if(!notasFrequencias.isEmpty()){
            double media = 0;
            float frequencia = 0;
            float frequenciaTotal = 0;

            for (NotasFrequencia nota: notasFrequencias) {
                Disciplina disciplina = DisciplinaDAO.getDisciplina(nota.getDisciplina());
                frequenciaTotal += disciplina.getCargaHoraria();
                media += nota.getNota();
                frequencia += nota.getFrequencia();
            }
            double mediaFinal = media / notasFrequencias.size();
            float frequenciaFinal = ((frequencia * 100 ) / frequenciaTotal);

            if(mediaFinal >= 70.0){
                if(frequenciaFinal >= 30.0){
                    holder.edResultado.setText("Aprovado");
                } else{
                    holder.edResultado.setText("Reprovado por falta.");
                }
            } else {
                holder.edResultado.setText("Reprovado por Nota");
            }

            holder.edFrequencia.setText(Math.round(frequenciaFinal) + "%");
            holder.edMediaFinal.setText(String.valueOf(mediaFinal));
        }
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }
}
