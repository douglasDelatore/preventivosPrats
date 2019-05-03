package com.example.douglasdelatore.preventivosprats.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.model.Preventivo;

import java.util.List;

public class PreventivosRealizadosAdapter extends RecyclerView.Adapter<PreventivosRealizadosAdapter.MyViewHolder> {

    private List<Preventivo> preventivos;
    private Context context;

    public PreventivosRealizadosAdapter(List<Preventivo> listaPreventivos, Context c) {
        this.preventivos = listaPreventivos;
        this.context = c;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.preventivos_realizados_adapter, viewGroup, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Preventivo preventivo = preventivos.get(i);

        myViewHolder.tarefa.setText(preventivo.getTarefa());
        myViewHolder.periodo.setText(preventivo.getPeriodo());
        myViewHolder.procedimento.setText(preventivo.getProcedimento());
        myViewHolder.posicao.setText(preventivo.getNomeUsuario());
        myViewHolder.dataHora.setText(preventivo.getDataHora());


    }

    @Override
    public int getItemCount() {
        return preventivos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tarefa, procedimento, periodo, posicao, dataHora;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa          = itemView.findViewById(R.id.textListaTarefaRealizada);
            periodo         = itemView.findViewById(R.id.textListaPeriodoRealizada);
            procedimento    = itemView.findViewById(R.id.textListaProcedimentoRealizada);
            posicao         = itemView.findViewById(R.id.textViewListaPosicaoRealizada);
            dataHora        = itemView.findViewById(R.id.textViewListaDataHoraPreventivaRealizada);

        }
    }
}
