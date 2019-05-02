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
import android.widget.TextView;
import android.widget.Toast;
import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CadastroPreventivosActivity extends AppCompatActivity {

    private EditText campoId, campoComponente, campoHoras, campoProcSheet, campoPosicao;
    private TextView campoDataHoraCadastro;
    private Spinner campoNivel, campoPeriodo, campoOperacao, campoColocacao;
    private Button botaoSalvar;
    private String idUsuarioLogado;
    private ProgressBar progressBarCadastroPreventivos;
    private String[] items = new String[] {"1", "2", "3", "4", "5"};
    private String[] periodo = new String[] {"Diário","Semanal","Mensal","Bimestral","Trimestral", "Semestral", "Anual", "Dois anos", "Três Anos","Quatro anos", "Condicional"};
    private String[] operacao = new String[] {"Controle","Limpeza","Substituição","Lubrificação"};
    private String[] colocacao = new String[] {"Enchedora", "Sopradora", "Módulo Transferencia Combi",
                            "Tratamento de cápsulas dry", "Capsuladora", "Fim da linha", "Unidade CIP", "Unidade PAA"};
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
                String colocacao    = campoColocacao.getSelectedItem().toString();
                String procSheet    = campoProcSheet.getText().toString().toUpperCase();
                String posicao      = campoPosicao.getText().toString();
                String dataHora     = campoDataHoraCadastro.getText().toString();



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
                                        cadastroPreventivos.setPosicao(posicao);
                                        cadastroPreventivos.setColocacao(colocacao);
                                        cadastroPreventivos.setNivel(nivel);
                                        cadastroPreventivos.setDataHoraCadastro(dataHora);

                                        salvarNovoPreventivo(cadastroPreventivos);

                                    } else {
                                        progressBarCadastroPreventivos.setVisibility(View.GONE);
                                        //Toast.makeText(CadastroPreventivosActivity.this, "Preencha o ProcSheet", Toast.LENGTH_LONG).show();
                                        campoProcSheet.setText("-");
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
        databaseReference = ConfiguracaoFirebase.getFirebase().child("PreventivoFixo").child("Sidel");

        String textoPeriodo = "";
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
        if (campoColocacao.getSelectedItemPosition() == 0){ //enchedora
            databaseReference = ConfiguracaoFirebase.getFirebase()
                    .child("PreventivoFixo")
                    .child("Sidel")
                    .child("Enchedora")
                    .child(textoPeriodo)
                    .child(cadastroPreventivos.getId());

        } else if (campoColocacao.getSelectedItemPosition() == 1) { //sopradora
            databaseReference = ConfiguracaoFirebase.getFirebase()
                    .child("PreventivoFixo")
                    .child("Sidel")
                    .child("Sopradora")
                    .child(textoPeriodo)
                    .child(cadastroPreventivos.getId());
            }

        else if (campoColocacao.getSelectedItemPosition() == 2) { //Módulo Transferencia Combi
            databaseReference = ConfiguracaoFirebase.getFirebase()
                    .child("PreventivoFixo")
                    .child("Sidel")
                    .child("Módulo Transferencia Combi")
                    .child(textoPeriodo)
                    .child(cadastroPreventivos.getId());
        }

        else if (campoColocacao.getSelectedItemPosition() == 3) { //Tratamento de cápsulas dry
            databaseReference = ConfiguracaoFirebase.getFirebase()
                    .child("PreventivoFixo")
                    .child("Sidel")
                    .child("Tratamento de cápsulas dry")
                    .child(textoPeriodo)
                    .child(cadastroPreventivos.getId());
        }

        else if (campoColocacao.getSelectedItemPosition() == 4) { //Capsuladora
            databaseReference = ConfiguracaoFirebase.getFirebase()
                    .child("PreventivoFixo")
                    .child("Sidel")
                    .child("Capsuladora")
                    .child(textoPeriodo)
                    .child(cadastroPreventivos.getId());
        }

        else if (campoColocacao.getSelectedItemPosition() == 5) { //Fim da linha
            databaseReference = ConfiguracaoFirebase.getFirebase()
                    .child("PreventivoFixo")
                    .child("Sidel")
                    .child("Fim da linha")
                    .child(textoPeriodo)
                    .child(cadastroPreventivos.getId());
        }

        else if (campoColocacao.getSelectedItemPosition() == 6) { //Unidade CIP
            databaseReference = ConfiguracaoFirebase.getFirebase()
                    .child("PreventivoFixo")
                    .child("Sidel")
                    .child("Unidade CIP")
                    .child(textoPeriodo)
                    .child(cadastroPreventivos.getId());
        }

        else if (campoColocacao.getSelectedItemPosition() == 7) { //Unidade PAA
            databaseReference = ConfiguracaoFirebase.getFirebase()
                    .child("PreventivoFixo")
                    .child("Sidel")
                    .child("Unidade PAA")
                    .child(textoPeriodo)
                    .child(cadastroPreventivos.getId());
        }


        //databaseReference = ConfiguracaoFirebase.getFirebase().child("Sidel").child("PreventivoFixo").child(cadastroPreventivos.getId());
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
        campoDataHoraCadastro           = findViewById(R.id.textViewDtHrCadPrev);
        campoComponente                 = findViewById(R.id.editTextComponenteCadPrev);
        campoPosicao                    = findViewById(R.id.editTextPosicaoCadPrev);
        campoOperacao                   = findViewById(R.id.spinnerCadastroOperacao);
        campoPeriodo                    = findViewById(R.id.spinnerCadastroPeriodo);
        campoHoras                      = findViewById(R.id.editTextHorasCadPrev);
        campoNivel                      = findViewById(R.id.spinnerNivelCadPrev);
        campoColocacao                  = findViewById(R.id.spinnerCadastroColocacao);
        campoProcSheet                  = findViewById(R.id.editTextProcSheetCadPrev);
        botaoSalvar                     = findViewById(R.id.buttonSalvarCadPrev);
        progressBarCadastroPreventivos  = findViewById(R.id.progressBarCadastroPreventivos);
        progressBarCadastroPreventivos.setVisibility(View.GONE);

        //Implementar spinner de itens
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoNivel.setAdapter(adapter);

        //Implementar spinner de periodo
        ArrayAdapter<String> adapterPeriodo = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, periodo);
        adapterPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoPeriodo.setAdapter(adapterPeriodo);

        //Implementar spinner de Operacao
        ArrayAdapter<String> adapterOperacao = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, operacao);
        adapterOperacao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoOperacao.setAdapter(adapterOperacao);

        //Implementar spinner de Colocacao
        ArrayAdapter<String> adapterColocacao = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, colocacao);
        adapterColocacao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoColocacao.setAdapter(adapterColocacao);

        campoHoras.setEnabled(false);

        //Pegar Data atual do celular!!!
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        String data_completa = dateFormat.format(data_atual);
        campoDataHoraCadastro.setText(data_completa);

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