package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.douglasdelatore.preventivosprats.R;

public class ListarPreventivasFiltroActivity extends AppCompatActivity {

    private Button botaoDiario, botaoSemanal, botaoMensal, botaoTrimestral, botaoSemestral,
                   botaoAnual, botaoDoisAnos, botaoTresAnos, botaoQuatroAnos, botaoCondicional;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_preventivas_filtro);

        iniciarComponentes();

        botaoDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosDiarioActivity.class));
            }
        });

        botaoSemanal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosSemanaisActivity.class));
            }
        });

        botaoMensal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosMensaisActivity.class));
            }
        });

        //Arrumar
        botaoTrimestral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosActivity.class));
            }
        });

        //Arrumar
        botaoSemestral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosSemanaisActivity.class));
            }
        });

        botaoAnual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosAnuaisActivity.class));
            }
        });

        botaoDoisAnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosDoisAnosActivity.class));
            }
        });

        botaoTresAnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosTresAnosActivity.class));
            }
        });

        botaoQuatroAnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosQuatroAnosActivity.class));
            }
        });

        botaoCondicional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListarPreventivasFiltroActivity.this, ListarPreventivosCondicionaisActivity.class));
            }
        });

    }

    private void iniciarComponentes() {

        botaoDiario         = findViewById(R.id.buttonListaDiario);
        botaoSemanal        = findViewById(R.id.buttonListaSemanal);
        botaoMensal         = findViewById(R.id.buttonListaMensal);
        botaoTrimestral     = findViewById(R.id.buttonListaTrimestral);
        botaoSemestral      = findViewById(R.id.buttonListaSemestral);
        botaoAnual          = findViewById(R.id.buttonListaAnual);
        botaoDoisAnos       = findViewById(R.id.buttonListaDoisAnos);
        botaoTresAnos       = findViewById(R.id.buttonListaTresAnos);
        botaoQuatroAnos     = findViewById(R.id.buttonListaQuatroAnos);
        botaoCondicional    = findViewById(R.id.buttonListaCondicional);

    }
}
