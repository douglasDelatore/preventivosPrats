package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.helper.UsuarioFirebase;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.example.douglasdelatore.preventivosprats.model.Preventivo;
import com.example.douglasdelatore.preventivosprats.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LancarPreventivosActivity extends AppCompatActivity {

    private EditText campoProcedimento, campoObs, campoNumeroOS;
    private TextView campoTarefa, campoDataEHora;
    private Button botaoLancar;
    private DatabaseReference databaseReference;
    private CadastroPreventivos preventivosDestino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar_preventivos);

        iniciarComponentes();

        botaoLancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String procedimento = campoProcedimento.getText().toString();
                String obs = campoObs.getText().toString();
                String numeroOs = campoNumeroOS.getText().toString();
                String tarefa = campoTarefa.getText().toString();
                String dataEHora = campoDataEHora.getText().toString();
                String idUsuario = UsuarioFirebase.getIdentificadorUsuario();
                String nomeUsuario = UsuarioFirebase.getNomeUsuario();

                if (!numeroOs.isEmpty()){
                    if (!obs.isEmpty()){

                        Preventivo preventivo = new Preventivo();
                        preventivo.setTarefa(tarefa);
                        preventivo.setProcedimento(procedimento);
                        preventivo.setObs(obs);
                        preventivo.setNumeroOS(numeroOs);
                        preventivo.setDataHora(dataEHora);
                        preventivo.setIdUsuario(idUsuario);
                        preventivo.setNomeUsuario(nomeUsuario);

                        lancarPreventivo(preventivo);

                    }else{
                        Toast.makeText(LancarPreventivosActivity.this, "Preencha o campo de Observação", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LancarPreventivosActivity.this, "Preencha o número da OS", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void lancarPreventivo(Preventivo preventivo){
        databaseReference = ConfiguracaoFirebase.getFirebase().child("preventivosRealizados").child(preventivo.getId());
        databaseReference.setValue(preventivo).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if( task.isSuccessful() ){
                    Toast.makeText(LancarPreventivosActivity.this, "Lançamento realizado com sucesso", Toast.LENGTH_LONG).show();
                    startActivity( new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

    }

    public void iniciarComponentes(){
        campoProcedimento   = findViewById(R.id.editTextProcedimento);
        campoObs            = findViewById(R.id.editTextObs);
        campoNumeroOS       = findViewById(R.id.editTextNumeroOS);
        campoTarefa         = findViewById(R.id.textViewTarefa);
        campoDataEHora      = findViewById(R.id.textViewDataEHora);
        botaoLancar         = findViewById(R.id.buttonLancar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            preventivosDestino = (CadastroPreventivos) bundle.getSerializable("tarefa");
            campoTarefa.setText(preventivosDestino.getComponente());
            campoProcedimento.setText(preventivosDestino.getProcSheet());
        }

        //Pegar Data atual do celular!!!
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        Date data = new Date();

        Calendar  cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String data_completa = dateFormat.format(data_atual);
        campoDataEHora.setText(data_completa);

        campoProcedimento.setEnabled(false);
        campoNumeroOS.requestFocus();

    }
}
