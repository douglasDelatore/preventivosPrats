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
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.helper.RecyclerItemClickListener;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaSemanaCapsuladoraActivity extends AppCompatActivity {

    private ArrayList<CadastroPreventivos> listaPreventivos = new ArrayList<>();
    private RecyclerView recyclerViewPreventivos;
    private DatabaseReference preventivosRef;
    private PreventivosAdapter adapter;
    private ValueEventListener valueEventListenerPreventivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_semana_capsuladora);
        iniciarComponentes();
        preventivosRef = ConfiguracaoFirebase.getFirebase().child("PreventivoFixo")
                .child("Sidel")
                .child("Capsuladora")
                .child("Semanal");

        //Configurar adapter
        adapter = new PreventivosAdapter(listaPreventivos, this);

        //configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPreventivos.setLayoutManager(layoutManager);
        recyclerViewPreventivos.setHasFixedSize(true);
        recyclerViewPreventivos.setAdapter(adapter);

        //configurar o evento de clique no recyclerview
        recyclerViewPreventivos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerViewPreventivos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                CadastroPreventivos cadastroPreventivosSelecionado = listaPreventivos.get(position);
                                Intent intent = new Intent(ListaSemanaCapsuladoraActivity.this, LancarPreventivosActivity.class);
                                intent.putExtra("tarefa", cadastroPreventivosSelecionado );
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
        recyclerViewPreventivos = findViewById(R.id.recyclerViewListaPreventivosSemanaisCapsuladora);
    }

    public void recuperarPreventivos(){

        valueEventListenerPreventivos = preventivosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dados: dataSnapshot.getChildren()) {

                    CadastroPreventivos cadastroPreventivos = dados.getValue(CadastroPreventivos.class);
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