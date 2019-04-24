package com.example.douglasdelatore.preventivosprats.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.model.Preventivo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

        databaseReference = ConfiguracaoFirebase.getFirebase();
        databaseReference.child("PreventivoFixo");
        databaseReference.getDatabase();

        recyclerViewPreventivos.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }

    public void iniciarComponentes(){
        recyclerViewPreventivos = findViewById(R.id.recyclerViewListaPreventivos);


    }
}