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
import com.example.douglasdelatore.preventivosprats.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText campoSenha, campoEmail;
    private TextView cadastroProvisorio;
    private Button botaoEntrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        verificarUsuarioLogado();
        iniciarComponente();

        cadastroProvisorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoEmail = campoEmail.getText().toString();
                String textosenha = campoSenha.getText().toString();

                if( !textoEmail.isEmpty() ){
                    if( !textosenha.isEmpty() ){

                        usuario = new Usuario();
                        usuario.setEmail( textoEmail );
                        usuario.setSenha( textosenha );
                        validarLogin( usuario );

                    }else{
                        Toast.makeText(LoginActivity.this,
                                "Preencha a senha!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,
                            "Preencha o e-mail!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    //Validação de login passando o usuario como parametro
    public void validarLogin( Usuario usuario ){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,
                            "Erro ao fazer login",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Aqui verifica se já tem algum usuario logado
    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if( autenticacao.getCurrentUser() != null ){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    public void iniciarComponente(){
        campoEmail  = findViewById(R.id.editTextEmail);
        campoSenha  = findViewById(R.id.editTextSenha);
        botaoEntrar = findViewById(R.id.buttonEntrar);

        cadastroProvisorio = findViewById(R.id.textView14);

        cadastroProvisorio.setVisibility(View.GONE);

        campoEmail.requestFocus();
    }
}