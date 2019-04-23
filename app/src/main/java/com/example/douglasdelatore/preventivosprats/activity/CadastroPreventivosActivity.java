package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CadastroPreventivosActivity extends AppCompatActivity {

    private EditText campoId, campoComponente, campoOperacao, campoPeriodo, campoHoras, campoProcSheet;
    private Spinner campoNivel;
    private Button botaoSalvar;
    private String idUsuarioLogado;
    private String[] items = new String[] {"1", "2", "3"};
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_preventivos);

        iniciarComponentes();

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id           = campoId.getText().toString();
                String componente   = campoComponente.getText().toString();
                String operacao     = campoOperacao.getText().toString();
                String periodo      = campoPeriodo.getText().toString();
                String horas        = campoHoras.getText().toString();
                String nivel        = campoNivel.getSelectedItem().toString();
                String procSheet    = campoProcSheet.getText().toString();

                if (!id.isEmpty()) {
                    if (!componente.isEmpty()) {
                        if (!operacao.isEmpty()) {
                            if (!periodo.isEmpty()) {
                                if (!horas.isEmpty()) {
                                    if (!procSheet.isEmpty()) {

                                        CadastroPreventivos cadastroPreventivos = new CadastroPreventivos();
                                        cadastroPreventivos.setCodigo(id);
                                        cadastroPreventivos.setComponente(componente);
                                        cadastroPreventivos.setOperacao(operacao);
                                        cadastroPreventivos.setPeriodo(periodo);
                                        cadastroPreventivos.setHoras(horas);
                                        cadastroPreventivos.setProcSheet(procSheet);
                                        cadastroPreventivos.setNivel(nivel);
                                        salvarNovoPreventivo(cadastroPreventivos);

                                    } else {
                                        Toast.makeText(CadastroPreventivosActivity.this, "Preencha o ProcSheet", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(CadastroPreventivosActivity.this, "Preencha as horas", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(CadastroPreventivosActivity.this, "Preencha o período", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(CadastroPreventivosActivity.this, "Preencha a operação", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(CadastroPreventivosActivity.this, "Preencha o componente", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CadastroPreventivosActivity.this, "Preencha o id", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void salvarNovoPreventivo(CadastroPreventivos cadastroPreventivos){
        databaseReference = ConfiguracaoFirebase.getFirebase().child("PreventivoFixo").child(cadastroPreventivos.getId());
        databaseReference.setValue(cadastroPreventivos).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if( task.isSuccessful() ){
                    Toast.makeText(CadastroPreventivosActivity.this, "Preventivo cadastrado com sucesso", Toast.LENGTH_LONG).show();
                    startActivity( new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoNivel.setAdapter(adapter);
    }
}
