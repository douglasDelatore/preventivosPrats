package com.example.douglasdelatore.preventivosprats.model;

import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.example.douglasdelatore.preventivosprats.helper.UsuarioFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CadastroPreventivos implements Serializable {

    private String id;
    private String idUsuario;
    private String codigo;
    private String posicao;
    private String componente;
    private String operacao;
    private String Colocacao;
    private String periodo;
    private String horas;
    private String nivel;
    private String procSheet;
    private String dataHoraCadastro;

    public CadastroPreventivos() {
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference cadastroPreventivosRef = firebaseRef.child("preventivosFixos");
        String idPreventivoCadastro = cadastroPreventivosRef.push().getKey();
        setId( idPreventivoCadastro );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getProcSheet() {
        return procSheet;
    }

    public void setProcSheet(String procSheet) {
        this.procSheet = procSheet;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getColocacao() {
        return Colocacao;
    }

    public void setColocacao(String colocacao) {
        Colocacao = colocacao;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(String dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }
}
