package com.example.douglasdelatore.preventivosprats.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class CadastroPreventivosActivity extends AppCompatActivity {

    private EditText campoId, campoComponente, campoOperacao, campoPeriodo, campoHoras, campoProcSheet;
    private Spinner campoNivel;
    private Button botaoSalvar;

    private CadastroPreventivos cadastroPreventivos;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_preventivos);

        iniciarComponentes();

        //Cadastrar preventivos
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = campoId.getText().toString();
                String componente = campoComponente.getText().toString();
                String operacao = campoOperacao.getText().toString();
                String periodo = campoPeriodo.getText().toString();
                String horas = campoHoras.getText().toString();
                String procSheet = campoProcSheet.getText().toString();
                String nivel = campoNivel.getSelectedItem().toString();

               cadastroPreventivos = new CadastroPreventivos();
               cadastroPreventivos.setCodigo(id);
               cadastroPreventivos.setCodigo(componente);
               cadastroPreventivos.setCodigo(operacao);
               cadastroPreventivos.setCodigo(periodo);
               cadastroPreventivos.setCodigo(horas);
               cadastroPreventivos.setCodigo(procSheet);
               cadastroPreventivos.setCodigo(nivel);

                cadastrarPreventivos(cadastroPreventivos);

            }
        });


    }

    public void cadastrarPreventivos(final CadastroPreventivos cadastroPreventivos){
        databaseReference = ConfiguracaoFirebase.getFirebase();
        databaseReference.child("preventivos");

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
