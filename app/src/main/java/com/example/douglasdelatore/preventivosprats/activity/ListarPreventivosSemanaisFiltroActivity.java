package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.douglasdelatore.preventivosprats.R;

public class ListarPreventivosSemanaisFiltroActivity extends AppCompatActivity {

    private Button botaoEnchedora, botaoSopradora, botaoTransfCombi, botaoCapsulaDry,
            botaoCapsuladora, botaoFimLinha, botaoCip, botaoPaa, botaoCapdisPredis, botaoTransporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_preventivos_semanais_filtro);

        iniciarComponente();

        botaoEnchedora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        ListaSemanaEnchedoraActivity.class));
            }
        });
        botaoSopradora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        ListaSemanaSopradoraActivity.class));
            }
        });
        botaoTransfCombi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        TransfCombiActivity.class));
            }
        });
        botaoCapsulaDry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        ListaSemanaCapsulaDryActivity.class));
            }
        });
        botaoCapsuladora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        ListaSemanaCapsuladoraActivity.class));
            }
        });
        botaoFimLinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        ListaSemanaFimLinhaActivity.class));
            }
        });
        botaoCip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        ListaSemanaCipActivity.class));
            }
        });
        botaoPaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        ListaSemanaPaaActivity.class));
            }
        });
        botaoCapdisPredis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        ListaSemanaCapdisPredisActivity.class));
            }
        });
        botaoTransporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ListarPreventivosSemanaisFiltroActivity.this,
                        ListaSemanaTransporteActivity.class));
            }
        });



    }


    public void iniciarComponente(){
        botaoEnchedora =  findViewById(R.id.buttonSemanaEnchedora);
        botaoSopradora =  findViewById(R.id.buttonSemanaSopradora);
        botaoTransfCombi =  findViewById(R.id.buttonSemanaTransCombi);
        botaoCapsulaDry =  findViewById(R.id.buttonSemanaCapDry);
        botaoCapsuladora =  findViewById(R.id.buttonSemanaCapsuladora);
        botaoFimLinha =  findViewById(R.id.buttonSemanaFimLinha);
        botaoCip =  findViewById(R.id.buttonSemanaCip);
        botaoPaa =  findViewById(R.id.buttonSemanaPAA);
        botaoCapdisPredis =  findViewById(R.id.buttonSemanaCapdisPredis);
        botaoTransporte =  findViewById(R.id.buttonSemanaTransporte);
    }
}
