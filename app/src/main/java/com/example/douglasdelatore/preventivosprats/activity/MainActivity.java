package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.UsuarioFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private Button botaoLancarPreventivo, botaoListarPreventivo, botaoRelatorio, botaoCadastrarUsuario;
    private TextView campoPerfil, opcaoSair;
    private FirebaseAuth autenticacao;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();

        botaoLancarPreventivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LancarPreventivosActivity.class);
                startActivity(intent);
            }
        });

        botaoListarPreventivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        botaoRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        botaoCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Deslogar Usuário
        opcaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    autenticacao.signOut();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void iniciarComponentes(){
        botaoLancarPreventivo   = findViewById(R.id.buttonLancarPreventivos);
        botaoListarPreventivo   = findViewById(R.id.buttonListaPreventivos);
        botaoRelatorio          = findViewById(R.id.buttonRelatorio);
        botaoCadastrarUsuario   = findViewById(R.id.buttonCadastrarNovoUsuario);
        campoPerfil             = findViewById(R.id.textViewPerfil);
        opcaoSair               = findViewById(R.id.textViewSair);

        //Recuperar dados do usuário
        FirebaseUser usuarioPerfil = UsuarioFirebase.getUsuarioAtual();
        String verificaPerfil = usuarioPerfil.getDisplayName().toUpperCase();
        campoPerfil.setText(verificaPerfil);


        if (!campoPerfil.equals("RAFAEL")) {
            botaoRelatorio.setVisibility(View.GONE);
            botaoCadastrarUsuario.setVisibility(View.GONE);
        }

    }

}
