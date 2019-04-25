package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;


public class CadastroPreventivosActivity extends AppCompatActivity {

    private EditText campoId, campoComponente, campoHoras, campoProcSheet;
    private Spinner campoNivel, campoPeriodo, campoOperacao;
    private Button botaoSalvar;
    private String idUsuarioLogado;
    private ProgressBar progressBarCadastroPreventivos;
    private String[] items = new String[] {"1", "2", "3", "4", "5"};
    private String[] periodo = new String[] {"Diário","Semanal","Mensal","Bimestral","Trimestral", "Semestral", "Anual", "Dois anos", "Três Anos","Quatro anos", "Condicional"};
    private String[] operacao = new String[] {"Controle","Limpeza","Substituição","Lubrificação"};
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_preventivos);

        iniciarComponentes();

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBarCadastroPreventivos.setVisibility(View.VISIBLE);
                String id           = campoId.getText().toString();
                String componente   = campoComponente.getText().toString();
                String operacao     = campoOperacao.getSelectedItem().toString();
                String periodo      = campoPeriodo.getSelectedItem().toString();
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
                                        progressBarCadastroPreventivos.setVisibility(View.GONE);
                                        Toast.makeText(CadastroPreventivosActivity.this, "Preencha o ProcSheet", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    progressBarCadastroPreventivos.setVisibility(View.GONE);
                                    Toast.makeText(CadastroPreventivosActivity.this, "Preencha as horas", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                progressBarCadastroPreventivos.setVisibility(View.GONE);
                                Toast.makeText(CadastroPreventivosActivity.this, "Preencha o período", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            progressBarCadastroPreventivos.setVisibility(View.GONE);
                            Toast.makeText(CadastroPreventivosActivity.this, "Preencha a operação", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        progressBarCadastroPreventivos.setVisibility(View.GONE);
                        Toast.makeText(CadastroPreventivosActivity.this, "Preencha o componente", Toast.LENGTH_LONG).show();
                    }
                } else {
                    progressBarCadastroPreventivos.setVisibility(View.GONE);
                    //Toast.makeText(CadastroPreventivosActivity.this, "Preencha o id", Toast.LENGTH_LONG).show();
                    campoId.setText("-");
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
                    progressBarCadastroPreventivos.setVisibility(View.GONE);
                    Toast.makeText(CadastroPreventivosActivity.this, "Preventivo cadastrado com sucesso", Toast.LENGTH_LONG).show();
                    startActivity( new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

    }

    public void iniciarComponentes(){
        campoId                         = findViewById(R.id.editTextCodigoCadPrev);
        campoComponente                 = findViewById(R.id.editTextComponenteCadPrev);
        campoOperacao                   = findViewById(R.id.spinnerCadastroOperacao);
        campoPeriodo                    = findViewById(R.id.spinnerCadastroPeriodo);
        campoHoras                      = findViewById(R.id.editTextHorasCadPrev);
        campoNivel                      = findViewById(R.id.spinnerNivelCadPrev);
        campoProcSheet                  = findViewById(R.id.editTextProcSheetCadPrev);
        botaoSalvar                     = findViewById(R.id.buttonSalvarCadPrev);
        progressBarCadastroPreventivos  = findViewById(R.id.progressBarCadastroPreventivos);
        progressBarCadastroPreventivos.setVisibility(View.GONE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoNivel.setAdapter(adapter);

        ArrayAdapter<String> adapterPeriodo = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, periodo);
        adapterPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoPeriodo.setAdapter(adapterPeriodo);

        ArrayAdapter<String> adapterOperacao = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, operacao);
        adapterOperacao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoOperacao.setAdapter(adapterOperacao);

        campoHoras.setEnabled(false);

        campoPeriodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (campoPeriodo.getSelectedItemPosition()) {
                    case 0:
                         campoHoras.setText("24"); //Diario
                        break;
                    case 1:
                        campoHoras.setText("125"); //Semanal
                        break;
                    case 2:
                        campoHoras.setText("500"); //Mensal
                        break;
                    case 3:
                        campoHoras.setText("1000"); //Bimestral
                        break;
                    case 4:
                        campoHoras.setText("1500"); //Trimestral
                        break;
                    case 5:
                        campoHoras.setText("3000"); //Semestral
                        break;
                    case 6:
                        campoHoras.setText("6000"); //Anual
                        break;
                    case 7:
                        campoHoras.setText("12000"); //Dois Anos
                        break;
                    case 8:
                        campoHoras.setText("18000"); //Tres Anos
                        break;
                    case 9:
                        campoHoras.setText("24000"); //Quatro Anos
                        break;
                    case 10:
                        campoHoras.setText("0"); //Condicional
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}