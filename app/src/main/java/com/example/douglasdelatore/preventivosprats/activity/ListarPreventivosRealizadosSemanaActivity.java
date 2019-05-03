package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.adapter.PreventivosAdapter;
import com.example.douglasdelatore.preventivosprats.adapter.PreventivosRealizadosAdapter;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.helper.RecyclerItemClickListener;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.example.douglasdelatore.preventivosprats.model.Preventivo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarPreventivosRealizadosSemanaActivity extends AppCompatActivity {

    private ArrayList<Preventivo> listaPreventivos = new ArrayList<>();
    private RecyclerView recyclerViewPreventivosRealizados;
    private DatabaseReference preventivosRef;
    private PreventivosRealizadosAdapter adapter;
    private ValueEventListener valueEventListenerPreventivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_preventivos_realizados_semana);
        iniciarComponentes();

        preventivosRef = ConfiguracaoFirebase.getFirebase().child("PreventivosRealizados");

        //Configurar adapter
        adapter = new PreventivosRealizadosAdapter(listaPreventivos, this);

        //configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPreventivosRealizados.setLayoutManager(layoutManager);
        recyclerViewPreventivosRealizados.setHasFixedSize(true);
        recyclerViewPreventivosRealizados.setAdapter(adapter);

        //configurar o evento de clique no recyclerview
        recyclerViewPreventivosRealizados.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerViewPreventivosRealizados,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Preventivo preventivoSelecionado = listaPreventivos.get(position);
                                Intent intent = new Intent(
                                        ListarPreventivosRealizadosSemanaActivity.this,
                                        VisualizarPreventivosRealizadosActivity.class);
                                intent.putExtra("preventivoRealizado", preventivoSelecionado );
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarPreventivos();
    }

    @Override
    protected void onStop() {
        super.onStop();
        preventivosRef.removeEventListener(valueEventListenerPreventivos);
    }

    public void iniciarComponentes(){
        recyclerViewPreventivosRealizados = findViewById(R.id.recyclerViewListaPreventivosRealizadosSemanais);
    }

    public void recuperarPreventivos(){

        valueEventListenerPreventivos = preventivosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dados: dataSnapshot.getChildren()) {

                    Preventivo cadastroPreventivos = dados.getValue(Preventivo.class);
                    listaPreventivos.add(cadastroPreventivos);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}