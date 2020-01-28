package com.primeiro.gerencontas.Uteis;

/**
 * created by David nilo
 * versão 1
 * 03/01/2020**/

// CLASSE DE CONTROLA O MES.
public class Mes {
    private String  codigo;
    private String  descricao;
//********************************************************************************
    // METODOS GET E SET
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /*RETORNA A DESCRICAO NO COMPONENTE SPINNER */
    @Override
    public String toString() {
        return this.descricao;
    }

    public Mes(){

    }
    public Mes(String codigo, String descricao){

        this.codigo    = codigo;
        this.descricao = descricao;

    }
}
