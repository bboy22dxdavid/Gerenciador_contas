package com.primeiro.gerencontas.ObjConta;
/**
 * created by David nilo
 * versão 1
 * 02/01/2020**/

//CLASSE QUE CRIA O OBJETO CONTA, COM SEUS ATRIBUTOS.
public class Conta {
    private Integer codigo;
    private String mes;
    private String nome;
    private String valor;
    private byte registroAtivo;
//********************************************************************************
    // METODOS GET E SET


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public byte getRegistroAtivo() {
        return registroAtivo;
    }

    public void setRegistroAtivo(byte registroAtivo) {
        this.registroAtivo = registroAtivo;
    }
}
