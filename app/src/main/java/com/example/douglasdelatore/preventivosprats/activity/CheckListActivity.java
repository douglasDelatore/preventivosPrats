package com.example.douglasdelatore.preventivosprats.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.douglasdelatore.preventivosprats.R;
import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.helper.UsuarioFirebase;
import com.example.douglasdelatore.preventivosprats.model.CheckList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckListActivity extends AppCompatActivity {

    private CheckBox opcao1,opcao2,opcao3,opcao4,opcao5;
    private TextView dataHora;
    private Button botaoSalvarCheckList;
    private Spinner spinnerTurno;
    private String[] turno = new String[] {"1","2","3"};
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        iniciarComponentes();

        botaoSalvarCheckList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String campoOpcao = "Não Conforme",
                        campoOpcao2 = "Não Conforme",
                        campoOpcao3 = "Não Conforme",
                        campoOpcao4 = "Não Conforme",
                        campoOpcao5 = "Não Conforme";

                String campoDataHora = dataHora.getText().toString();
                String idUsuario = UsuarioFirebase.getNomeUsuario();
                String campoTurno = spinnerTurno.getSelectedItem().toString();


                if (opcao1.isChecked()) {
                    campoOpcao = "ok";
                }
                if (opcao2.isChecked()){
                    campoOpcao2 = "ok";
                }
                if (opcao3.isChecked()){
                    campoOpcao3 = "ok";
                }
                if (opcao4.isChecked()){
                    campoOpcao4 = "ok";
                }
                if (opcao5.isChecked()){
                    campoOpcao5 = "ok";
                }

                CheckList checkList = new CheckList();

                checkList.setOpcao1(campoOpcao);
                checkList.setOpcao2(campoOpcao2);
                checkList.setOpcao3(campoOpcao3);
                checkList.setOpcao4(campoOpcao4);
                checkList.setOpcao5(campoOpcao5);
                checkList.setDataHora(campoDataHora);
                checkList.setIdUsuario(idUsuario);
                checkList.setTurno(campoTurno);
                salvarChecklist(checkList);

            }
        });

    }

    public void salvarChecklist(CheckList checkList){
        databaseReference = ConfiguracaoFirebase.getFirebase().child("ChecklistTurno").child(checkList.getId());
        databaseReference.setValue(checkList).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CheckListActivity.this, "CheckList Realizado com sucesso", Toast.LENGTH_LONG).show();
                    startActivity( new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }

    public void iniciarComponentes(){
        opcao1                  = findViewById(R.id.checkBox);
        opcao2                  = findViewById(R.id.checkBox2);
        opcao3                  = findViewById(R.id.checkBox3);
        opcao4                  = findViewById(R.id.checkBox4);
        opcao5                  = findViewById(R.id.checkBox5);
        spinnerTurno            = findViewById(R.id.spinnerClTurno);
        dataHora                = findViewById(R.id.textViewCheckListDataHora);
        botaoSalvarCheckList    = findViewById(R.id.buttonSalvarCheckList);

        //Pegar Data atual do celular!!!
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        String data_completa = dateFormat.format(data_atual);
        dataHora.setText(data_completa);

        //Implementar spinner de turno
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, turno);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTurno.setAdapter(adapter);

    }
}
