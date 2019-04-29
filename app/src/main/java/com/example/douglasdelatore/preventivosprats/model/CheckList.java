package com.example.douglasdelatore.preventivosprats.model;

import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class CheckList {

    private String id;
    private String opcao1;
    private String opcao2;
    private String opcao3;
    private String opcao4;
    private String opcao5;
    private String turno;
    private String idUsuario;
    private String dataHora;

    public CheckList() {
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference cadastroCheklist = firebaseRef.child("checklist");
        String idChecklistCadastro = cadastroCheklist.push().getKey();
        setId( idChecklistCadastro );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpcao1() {
        return opcao1;
    }

    public void setOpcao1(String opcao1) {
        this.opcao1 = opcao1;
    }

    public String getOpcao2() {
        return opcao2;
    }

    public void setOpcao2(String opcao2) {
        this.opcao2 = opcao2;
    }

    public String getOpcao3() {
        return opcao3;
    }

    public void setOpcao3(String opcao3) {
        this.opcao3 = opcao3;
    }

    public String getOpcao4() {
        return opcao4;
    }

    public void setOpcao4(String opcao4) {
        this.opcao4 = opcao4;
    }

    public String getOpcao5() {
        return opcao5;
    }

    public void setOpcao5(String opcao5) {
        this.opcao5 = opcao5;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
