package com.example.douglasdelatore.preventivosprats.model;

import com.example.douglasdelatore.preventivosprats.helper.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class PreventivosAFazer {

    private String id;
    private String idUsuarioAFazer;
    private String codigoAFazer;
    private String posicaoAFazer;
    private String componenteAFazer;
    private String operacaoAFazer;
    private String ColocacaoAFazer;
    private String periodoAFazer;
    private String horasAFazer;
    private String nivelAFazer;
    private String procSheetAFazer;
    private String dataHoraCadastroAFazer;

    public PreventivosAFazer() {
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference preventivoAFazerRef = firebaseRef.child("PreventivosAFazer");
        String idPreventivoAFazer = preventivoAFazerRef.push().getKey();
        setId(idPreventivoAFazer);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuarioAFazer() {
        return idUsuarioAFazer;
    }

    public void setIdUsuarioAFazer(String idUsuarioAFazer) {
        this.idUsuarioAFazer = idUsuarioAFazer;
    }

    public String getCodigoAFazer() {
        return codigoAFazer;
    }

    public void setCodigoAFazer(String codigoAFazer) {
        this.codigoAFazer = codigoAFazer;
    }

    public String getPosicaoAFazer() {
        return posicaoAFazer;
    }

    public void setPosicaoAFazer(String posicaoAFazer) {
        this.posicaoAFazer = posicaoAFazer;
    }

    public String getComponenteAFazer() {
        return componenteAFazer;
    }

    public void setComponenteAFazer(String componenteAFazer) {
        this.componenteAFazer = componenteAFazer;
    }

    public String getOperacaoAFazer() {
        return operacaoAFazer;
    }

    public void setOperacaoAFazer(String operacaoAFazer) {
        this.operacaoAFazer = operacaoAFazer;
    }

    public String getColocacaoAFazer() {
        return ColocacaoAFazer;
    }

    public void setColocacaoAFazer(String colocacaoAFazer) {
        ColocacaoAFazer = colocacaoAFazer;
    }

    public String getPeriodoAFazer() {
        return periodoAFazer;
    }

    public void setPeriodoAFazer(String periodoAFazer) {
        this.periodoAFazer = periodoAFazer;
    }

    public String getHorasAFazer() {
        return horasAFazer;
    }

    public void setHorasAFazer(String horasAFazer) {
        this.horasAFazer = horasAFazer;
    }

    public String getNivelAFazer() {
        return nivelAFazer;
    }

    public void setNivelAFazer(String nivelAFazer) {
        this.nivelAFazer = nivelAFazer;
    }

    public String getProcSheetAFazer() {
        return procSheetAFazer;
    }

    public void setProcSheetAFazer(String procSheetAFazer) {
        this.procSheetAFazer = procSheetAFazer;
    }

    public String getDataHoraCadastroAFazer() {
        return dataHoraCadastroAFazer;
    }

    public void setDataHoraCadastroAFazer(String dataHoraCadastroAFazer) {
        this.dataHoraCadastroAFazer = dataHoraCadastroAFazer;
    }
}
