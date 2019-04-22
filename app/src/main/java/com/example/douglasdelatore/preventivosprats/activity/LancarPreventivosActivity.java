package com.example.douglasdelatore.preventivosprats.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.UsuarioFirebase;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LancarPreventivosActivity extends AppCompatActivity {

    private EditText campoProcedimento, campoObs, campoNumeroOS;
    private TextView campoTarefa, campoDataEHora, campoUsuario;
    private Button botaoLancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar_preventivos);

        iniciarComponentes();

    }

    private void iniciarComponentes(){
        campoProcedimento   = findViewById(R.id.editTextProcedimento);
        campoObs            = findViewById(R.id.editTextObs);
        campoNumeroOS       = findViewById(R.id.editTextNumeroOS);
        campoTarefa         = findViewById(R.id.textViewTarefa);
        campoDataEHora      = findViewById(R.id.textViewDataEHora);
        campoUsuario        = findViewById(R.id.textViewUsuarioLogado);
        botaoLancar         = findViewById(R.id.buttonLancar);


        //Pegar Data atual do celular!!!
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        Date data = new Date();

        Calendar  cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String data_completa = dateFormat.format(data_atual);
        campoDataEHora.setText(data_completa);

        //Recuperar dados do usuário
        FirebaseUser usuarioPerfil = UsuarioFirebase.getUsuarioAtual();
        String verificaPerfil = usuarioPerfil.getDisplayName().toUpperCase();
        campoUsuario.setText(verificaPerfil);

        campoProcedimento.setEnabled(false);
        campoNumeroOS.requestFocus();

    }
}
