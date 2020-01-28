package com.primeiro.gerencontas.BancoDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.primeiro.gerencontas.ObjConta.Conta;

import java.util.ArrayList;
import java.util.List;

/**
 * created by David nilo
 * versão 1
 * 02/01/2020**/
public class DBContas extends SQLiteOpenHelper {
    // CLASSE QUE CRIAR A BASE DE DADOS.
//*************************************************************************************************
    //OBJETOS DA BASE DE DADOS
    private static final String DB_CONTA = "db_conta";//criando objeto nome do banco
    private static final int VERSAO = 1;//VERSÃO DO BANCO DE DADOS
//***********************************************************************************************
//TABELA CONTA
    private  static  final String TABELA_CONTA = "tb_conta";//criando objeto tabela do banco.
    private  static  final String COLUNA_MES = "mes";//criando objeto coluna do banco.
    private  static  final String COLUNA_CODIGO = "codigo";//criando objeto coluna do banco.
    private  static  final String COLUNA_NOME = "nome";//criando objeto coluna do banco.
    private  static  final String COLUNA_VALOR = "valor";//criando objeto coluna do banco.
//***********************************************************************************************

    //CONSTRUTOR PARA INSTANCIAR A BASE DE DADOS
    public DBContas(Context context) {
        super(context, DB_CONTA, null, VERSAO);

    }

    //*************************************************************************************************
    @Override
// METODO QUE CRIA A BASE DE DADOS.
    public void onCreate(SQLiteDatabase db) {
        /*NA INICIALIZAÇÃO DA CLASSE CRIAR A TABELA.*/
//************************************************************************************************
//TABELA CONTA
        String QUERY_COLUNA = "CREATE TABLE " + TABELA_CONTA + "("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY, "
                + COLUNA_MES + " TEXT, "
                + COLUNA_NOME + " TEXT,"
                + COLUNA_VALOR + " TEXT) " ;

        db.execSQL(QUERY_COLUNA);//para executar a tabela
    }

//*************************************************************************************************
    @Override
// METODO DE ATUALIZA A BASE DE DADOS.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* QUANDO TROCAR, VERSÃO DO BANCO DE DADOS, PODE EXECUTAR ALGUMA ROTINA
      COMO: CRIAR COLUNAS, EXCLUIR ENTRE OUTRAS. */

        db.execSQL("DROP TABLE IF EXISTS tb_pessoa");
        onCreate(db);
    }
//*************************************************************************************************
    /*MÉTODO QUE VAI EXECUTAR AS ROTINAS NO
    BANCO DE DADOS*/
    public SQLiteDatabase GetConexaoDataBase(){

        return this.getWritableDatabase();
    }

}


