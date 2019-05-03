package com.example.douglasdelatore.preventivosprats.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.example.douglasdelatore.preventivosprats.model.Preventivo;

public class VisualizarPreventivosRealizadosActivity extends AppCompatActivity {

    private TextView campoTarefa, campoProcedimento, campoColocacao, campoPeriodo,
            campoObs, campoStatus, campoData, campoUsuario, campoOs;
    private Boolean campoStatus1;
    private Preventivo preventivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_preventivos_realizados);

        inicicarComponente();


    }

    public void inicicarComponente(){

        campoTarefa = findViewById(R.id.textViewVTarefa);
        campoProcedimento = findViewById(R.id.textViewVProcedimento);
        campoColocacao = findViewById(R.id.textViewVColocacao);
        campoPeriodo = findViewById(R.id.textViewVPeriodo);
        campoObs = findViewById(R.id.textViewVObs);
        campoStatus = findViewById(R.id.textViewVStatus);
        campoData = findViewById(R.id.textViewVData);
        campoUsuario = findViewById(R.id.textViewVUsuario);
        campoOs = findViewById(R.id.textViewVNumeroOs);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            preventivo = (Preventivo) bundle.getSerializable("preventivoRealizado");
            campoTarefa.setText("Tarefa: "+ preventivo.getTarefa());
            campoProcedimento.setText("Procedimento: " + preventivo.getProcedimento());
            campoColocacao.setText("Colocação: " + preventivo.getColocacao());
            campoPeriodo.setText("Período: " + preventivo.getPeriodo());
            campoObs.setText("Obs.:" + preventivo.getObs());
            campoData.setText("Data e Hora: " + preventivo.getDataHora());
            campoUsuario.setText("Usuário: " + preventivo.getNomeUsuario());
            campoOs.setText("Número O.S.: " + preventivo.getNumeroOS());
            campoStatus.setText("Status: " + preventivo.getStatus());



        }

    }
}
