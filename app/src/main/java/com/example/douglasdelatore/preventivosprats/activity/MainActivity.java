package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.helper.UsuarioFirebase;
import com.example.douglasdelatore.preventivosprats.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    private Button botaoLancarPreventivo, botaoListarPreventivo, botaoRelatorio, botaoCadastrarUsuario;
    private TextView campoPerfil;
    private FirebaseAuth autenticacao;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configurações iniciais
        //usuarioLogado = UsuarioFirebase.getDadosUsuarioLogado();
        //storageRef = ConfiguracaoFirebase.getFirebaseStorage();
        //identificadorUsuario = UsuarioFirebase.getIdentificadorUsuario();

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

    }

    public void iniciarComponentes(){
        String perfil = "";
        botaoLancarPreventivo   = findViewById(R.id.buttonLancarPreventivos);
        botaoListarPreventivo   = findViewById(R.id.buttonListaPreventivos);
        botaoRelatorio          = findViewById(R.id.buttonRelatorio);
        botaoCadastrarUsuario   = findViewById(R.id.buttonCadastrarNovoUsuario);
        campoPerfil             = findViewById(R.id.textViewPerfil);

        String verificaPerfil;
        //Recuperar dados do usuário
        FirebaseUser usuarioPerfil = UsuarioFirebase.getUsuarioAtual();
        verificaPerfil = usuarioPerfil.getDisplayName().toUpperCase();
        campoPerfil.setText(verificaPerfil);


        if (!campoPerfil.equals("RAFAEL")) {
            botaoRelatorio.setVisibility(View.GONE);
            botaoCadastrarUsuario.setVisibility(View.GONE);
        }

    }


    private void deslogarUsuario(){
        try{
            autenticacao.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
