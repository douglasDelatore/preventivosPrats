package com.example.douglasdelatore.preventivosprats.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;

import java.util.List;

public class PreventivosAdapter extends RecyclerView.Adapter<PreventivosAdapter.MyViewHolder> {

    private List<CadastroPreventivos> preventivos;
    private Context context;

    public PreventivosAdapter(List<CadastroPreventivos> listaPreventivos, Context c) {
        this.preventivos = listaPreventivos;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_preventivos, viewGroup, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CadastroPreventivos preventivo = preventivos.get(position);

        holder.tarefa.setText(preventivo.getComponente());
        holder.periodo.setText(preventivo.getPeriodo());
        holder.procedimento.setText(preventivo.getProcSheet());

    }

    @Override
    public int getItemCount() {
        return preventivos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tarefa, procedimento, periodo;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa          = itemView.findViewById(R.id.textListaTarefa);
            periodo         = itemView.findViewById(R.id.textListaPeriodo);
            procedimento    = itemView.findViewById(R.id.textListaProcedimento);

        }
    }
}
