package com.example.douglasdelatore.preventivosprats.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.douglasdelatore.preventivosprats.R;

public class CadastroPreventivosActivity extends AppCompatActivity {

    private EditText campoId, campoComponente, campoOperacao, campoPeriodo, campoHoras, campoProcSheet;
    private Spinner campoNivel;
    private Button botaoSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_preventivos);

        iniciarComponentes();


    }

    public void iniciarComponentes(){
        campoId         = findViewById(R.id.editTextCodigoCadPrev);
        campoComponente = findViewById(R.id.editTextComponenteCadPrev);
        campoOperacao   = findViewById(R.id.editTextOperacaoCadPrev);
        campoPeriodo    = findViewById(R.id.editTextPeriodoCadPrev);
        campoHoras      = findViewById(R.id.editTextHorasCadPrev);
        campoNivel      = findViewById(R.id.spinnerNivelCadPrev);
        campoProcSheet  = findViewById(R.id.editTextProcSheetCadPrev);
        botaoSalvar     = findViewById(R.id.buttonSalvarCadPrev);

    }
}
