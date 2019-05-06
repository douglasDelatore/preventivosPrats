package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.helper.UsuarioFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private Button botaoLancarPreventivo, botaoListarPreventivo, botaoRelatorio, botaoCadastrarUsuario, botaoSair, botaoCadastrarPreventivos, botaoChecklist;
    private TextView campoPerfil, campoNomeUsuario;
    private FirebaseAuth autenticacao;
    private String idUsuarioLogado;
    private int contador = 0;
    private ImageView imageView;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();

        //configuracoes de objetos
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        //Botao que abre a tela para lançar preventivos
        botaoLancarPreventivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarPreventivosRealizadosSemanaActivity.class);
                startActivity(intent);
            }
        });

        //Botao que lista preventivos
        botaoListarPreventivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarPreventivasFiltroActivity.class);
                startActivity(intent);
            }
        });

        //Botao que abre a pagina de relatorios (visivel apenas para adms)
        botaoRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RelatorioActivity.class);
                startActivity(intent);
            }
        });

        //Botao para cadastrar usuarios (visivel apenas para adms)
        botaoCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        //Botao para cadastrar um novo preventivo (visivel apenas para adms)
        botaoCadastrarPreventivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroPreventivosActivity.class);
                startActivity(intent);
            }
        });

        //Botao para cadastrar checklist(visivel apenas para adms)
        botaoChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckListActivity.class);
                startActivity(intent);
            }
        });

        //Configura o botao sair
        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deslogarUsuario();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        easterEgg();
    }

    //Metodo para deslogar usuários
    public void deslogarUsuario(){
        try{
            autenticacao.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void easterEgg(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador = contador++;
                if (contador == 10){
                    startActivity(new Intent(MainActivity.this, EasterEggPlayerActivity.class));
                }
            }
        });
    }

    public void iniciarComponentes(){
        botaoLancarPreventivo       = findViewById(R.id.buttonLancarPreventivos);
        botaoListarPreventivo       = findViewById(R.id.buttonListaPreventivos);
        botaoRelatorio              = findViewById(R.id.buttonRelatorio);
        botaoCadastrarUsuario       = findViewById(R.id.buttonCadastrarNovoUsuario);
        botaoCadastrarPreventivos   = findViewById(R.id.buttonCadastrarPreventivos);
        botaoChecklist              = findViewById(R.id.buttonCheclist);
        botaoSair                   = findViewById(R.id.buttonSair);
        campoNomeUsuario            = findViewById(R.id.textViewNome);
        imageView                   = findViewById(R.id.imageViewLogoPrincipal);

        //Recuperar dados do usuário
        FirebaseUser usuarioPerfil  = UsuarioFirebase.getUsuarioAtual();
        String verificaNome         = usuarioPerfil.getDisplayName().toUpperCase();
        idUsuarioLogado             = UsuarioFirebase.getIdentificadorUsuario();
        campoNomeUsuario.setText(verificaNome);

        botaoRelatorio.setVisibility(View.GONE);
        botaoCadastrarUsuario.setVisibility(View.GONE);
        //botaoCadastrarPreventivos.setVisibility(View.GONE);
        botaoChecklist.setVisibility(View.GONE);

    }
}
