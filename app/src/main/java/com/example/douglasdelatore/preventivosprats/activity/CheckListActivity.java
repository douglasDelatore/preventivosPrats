package com.example.douglasdelatore.preventivosprats.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.douglasdelatore.preventivosprats.R;

public class CheckListActivity extends AppCompatActivity {

    private CheckBox opcao1,opcao2,opcao3,opcao4,opcao5;
    private TextView dataHora;
    private Button botaoSalvarCheckList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        iniciarComponentes();

    }

    public void iniciarComponentes(){
        opcao1 = findViewById(R.id.checkBox);
        opcao2 = findViewById(R.id.checkBox2);
        opcao3 = findViewById(R.id.checkBox3);
        opcao4 = findViewById(R.id.checkBox4);
        opcao5 = findViewById(R.id.checkBox5);
        dataHora = findViewById(R.id.textViewCheckListDataHora);

    }
}
