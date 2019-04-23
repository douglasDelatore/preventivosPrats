package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.helper.UsuarioFirebase;
import com.example.douglasdelatore.preventivosprats.model.CadastroPreventivos;
import com.example.douglasdelatore.preventivosprats.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private Button botaoLancarPreventivo, botaoListarPreventivo, botaoRelatorio, botaoCadastrarUsuario, botaoSair, botaoCadastrarPreventivos;
    private TextView campoPerfil;
    private FirebaseAuth autenticacao;
    private String idUsuarioLogado;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();

        //configuracoes de objetos
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

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
            }
        });

        botaoCadastrarPreventivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroPreventivos.class);
                startActivity(intent);
            }
        });

        //Deslogar Usuário
        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deslogarUsuario();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    public void deslogarUsuario(){
        try{
            autenticacao.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void iniciarComponentes(){
        botaoLancarPreventivo       = findViewById(R.id.buttonLancarPreventivos);
        botaoListarPreventivo       = findViewById(R.id.buttonListaPreventivos);
        botaoRelatorio              = findViewById(R.id.buttonRelatorio);
        botaoCadastrarUsuario       = findViewById(R.id.buttonCadastrarNovoUsuario);
        botaoCadastrarPreventivos   = findViewById(R.id.buttonCadastrarPreventivos);
        botaoSair                   = findViewById(R.id.buttonSair);
        campoPerfil                 = findViewById(R.id.textViewPerfil);

        //Recuperar dados do usuário
        FirebaseUser usuarioPerfil  = UsuarioFirebase.getUsuarioAtual();
        String verificaPerfil       = usuarioPerfil.getDisplayName().toUpperCase();
        idUsuarioLogado             = UsuarioFirebase.getIdentificadorUsuario();
        campoPerfil.setText(verificaPerfil);

        if (campoPerfil.equals("RAFAEL")) {
            botaoRelatorio.setVisibility(View.GONE);
            botaoCadastrarUsuario.setVisibility(View.GONE);
        }
    }
}
