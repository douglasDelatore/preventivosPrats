package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.douglasdelatore.preventivosprats.R;

public class RelatorioActivity extends AppCompatActivity {

    private Button botaoRelatorioPreveRealizadas, botaoRelatorioPorOperador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        iniciarComponentes();

        botaoRelatorioPreveRealizadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RelatorioActivity.this, RelPreventivasRealizadasActivity.class);
                startActivity(intent);
            }
        });

        botaoRelatorioPorOperador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RelatorioActivity.this, RelPreventivasRealizadasActivity.class);
                startActivity(intent);
            }
        });

    }

    public void iniciarComponentes(){
        botaoRelatorioPreveRealizadas   = findViewById(R.id.buttonRelPrevRealizadas);
        botaoRelatorioPorOperador       = findViewById(R.id.buttonRelPrevOperador);
    }
}
