package com.example.douglasdelatore.preventivosprats.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class FiltroActivity extends AppCompatActivity {

    private Spinner campoColocacao, campoPeriodo;
    private Button botaoFiltrar;

    private DatabaseReference preventivosRef;

    private String[] periodo = new String[] {"Diário","Semanal","Mensal","Bimestral","Trimestral", "Semestral", "Anual", "Dois anos", "Três Anos","Quatro anos", "Condicional"};
    private String[] colocacao = new String[] {"Enchedora", "Sopradora"};
    private String[] maquina = new String[] {"Sidel", "Mesal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        inicarComponentes();


        botaoFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoColocacao   = "";
                String textoPeriodo     = "";
                String textoMaquina     = "";

                switch (campoColocacao.getSelectedItemPosition()){
                    case 0:
                        textoColocacao = "Enchedora";
                        break;
                    case 1:
                        textoColocacao = "Sopradora";
                        break;
                }

                switch (campoPeriodo.getSelectedItemPosition()){
                    case 0:
                        textoPeriodo = "Diario";
                        break;
                    case 1:
                        textoPeriodo = "Semanal";
                        break;
                    case 2:
                        textoPeriodo = "Mensal";
                        break;
                    case 3:
                        textoPeriodo = "Bimestral";
                        break;
                    case 4:
                        textoPeriodo = "Trimestral";
                        break;
                    case 5:
                        textoPeriodo = "Semestral";
                        break;
                    case 6:
                        textoPeriodo = "Anual";
                        break;
                    case 7:
                        textoPeriodo = "Dois anos";
                        break;
                    case 8:
                        textoPeriodo = "Tres anos";
                        break;
                    case 9:
                        textoPeriodo = "Quatro anos";
                        break;
                    case 10:
                        textoPeriodo = "Condicional";
                        break;
                }

                switch (campoColocacao.getSelectedItemPosition()){
                    case 0:
                        textoMaquina = "Sidel";
                        break;
                    case 1:
                        textoMaquina = "Mesal";
                        break;
                }

                preventivosRef = ConfiguracaoFirebase
                        .getFirebase()
                        .child("PreventivoFixo")
                        .child(textoMaquina) //monta filtro por maquina
                        .child(textoColocacao) //monta filtro por colocacao
                        .child(textoPeriodo); //monta filtro por periodo



            }
        });

    }

    public void inicarComponentes(){
        campoColocacao  = findViewById(R.id.spinnerFiltroColocacao);
        campoPeriodo    = findViewById(R.id.spinnerFiltroPeriodo);
        botaoFiltrar    = findViewById(R.id.buttonFiltroRelatorio);

        //Implementar spinner de periodo
        ArrayAdapter<String> adapterPeriodo = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, periodo);
        adapterPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoPeriodo.setAdapter(adapterPeriodo);

        //Implementar spinner de Colocacao
        ArrayAdapter<String> adapterColocacao = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, colocacao);
        adapterColocacao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoColocacao.setAdapter(adapterColocacao);
    }
}