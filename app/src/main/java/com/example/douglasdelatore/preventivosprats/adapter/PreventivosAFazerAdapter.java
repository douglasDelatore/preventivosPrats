package com.example.douglasdelatore.preventivosprats.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.model.PreventivosAFazer;

import java.util.List;

public class PreventivosAFazerAdapter extends RecyclerView.Adapter<PreventivosAFazerAdapter.MyViewHolder> {

    private List<PreventivosAFazer> preventivos;
    private Context context;

    public PreventivosAFazerAdapter(List<PreventivosAFazer> listaPreventivos, Context c) {
        this.preventivos = listaPreventivos;
        this.context = c;
    }


    @NonNull
    @Override
    public PreventivosAFazerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_preventivos_a_fazer, viewGroup, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull PreventivosAFazerAdapter.MyViewHolder myViewHolder, int i) {
        PreventivosAFazer preventivo = preventivos.get(i);
    }


    @Override
    public int getItemCount() {
        return preventivos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
