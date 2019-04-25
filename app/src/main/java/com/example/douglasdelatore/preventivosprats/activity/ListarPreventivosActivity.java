package com.example.douglasdelatore.preventivosprats.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.douglasdelatore.preventivosprats.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ListarPreventivosActivity extends AppCompatActivity {

    private List<String> listPreventivos;
    private RecyclerView recyclerViewPreventivos;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_preventivos);

        iniciarComponentes();

    }

    public void iniciarComponentes(){
        recyclerViewPreventivos = findViewById(R.id.recyclerViewListaPreventivos);
    }
}