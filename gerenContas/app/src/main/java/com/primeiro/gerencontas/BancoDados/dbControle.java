package com.primeiro.gerencontas.BancoDados;

import android.content.ContentValues;
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

// CLASSE QUE GERENCIA O CRUD
public class dbControle  {

    DBContas dbContas;// INSTANCIANDO CLASSE BANCO DADOS
    /***
     * CONSTRUTOR
     * @param context
     */
    public dbControle(Context context){

        dbContas = new DBContas(context);
    }
//***************************************************************************************
    /***
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param conta
     */
    public void Salvar(Conta conta){

        dbContas.GetConexaoDataBase();//intanciando o banco para escrever na tabela
        ContentValues values = new ContentValues();// instanciando objeto p receber valores

        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        values.put("mes", conta.getMes());
        values.put("nome", conta.getNome());
        values.put("valor", conta.getValor());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        dbContas.GetConexaoDataBase().insert("tb_conta", null, values);
        dbContas.close();//fecha o banco.
    }
//********************************************************************************************
    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param conta
     */
    public void Atualizar(Conta conta){

        dbContas.GetConexaoDataBase();//intanciando o banco para escrever na tabela
        ContentValues values = new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM ATUALIZADO*/
        values.put("mes", conta.getMes());
        values.put("nome", conta.getNome());
        values.put("valor", conta.getValor());

        /*ATUALIZANDO UM NOVO REGISTRO*/
        dbContas.GetConexaoDataBase().update("tb_conta", values,"codigo = ?",
                new String[]{Integer.toString(conta.getCodigo())});
        dbContas.close();//fechanco banco
    }
//********************************************************************************************
    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */
    public Integer Excluir(int codigo){
        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
       dbContas.GetConexaoDataBase();//intanciando o banco para escrever na tabela

        return dbContas.GetConexaoDataBase().delete("tb_conta", "codigo = ?",
                new String[]{Integer.toString(codigo)});
        //verificar se precisa fechar banco
    }
//********************************************************************************************
    /***
     * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
     * @param codigo
     * @return
     */
    public Conta Consultar(int codigo){
        dbContas.GetConexaoDataBase();//intanciando o banco para escrever na tabela
        Cursor cursor = dbContas.GetConexaoDataBase().rawQuery("SELECT * FROM tb_conta WHERE " +
                "codigo= " + codigo, null);

        cursor.moveToFirst();

        ///CRIANDO UMA NOVA CONTA
        Conta conta = new Conta();

        //ADICIONANDO OS DADOS DA CONTA
        conta.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
        conta.setMes(cursor.getString(cursor.getColumnIndex("mes")));
        conta.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        conta.setValor(cursor.getString(cursor.getColumnIndex("valor")));

        //RETORNANDO A CONTA
        return conta;
    }
//*************************************************************************************************
    /***
     * CONSULTA TODAS AS PESSOAS CADASTRADAS NA BASE
     * @return
     */
        public List<Conta> SelecionarTodas(){
            List<Conta> contas = new ArrayList<Conta>();

            //MONTA A QUERY A SER EXECUTADA
            StringBuilder query = new StringBuilder();
            query.append(" SELECT codigo,        ");
            query.append("            mes,       ");
            query.append("            nome,      ");
            query.append("            valor      ");
            query.append("  FROM  tb_conta       ");
            query.append(" ORDER BY nome          ");

            //CONSULTANDO OS REGISTROS CADASTRADOS
            Cursor cursor = dbContas.GetConexaoDataBase().rawQuery(query.toString(), null);

            /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
            cursor.moveToFirst();

            Conta conta;

            //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
            while (!cursor.isAfterLast()){

                /* CRIANDO UMA NOVA CONTA*/
                conta = new Conta();

                //ADICIONANDO OS DADOS DA CONTA
                conta.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                conta.setMes(cursor.getString(cursor.getColumnIndex("mes")));
                conta.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                conta.setValor(cursor.getString(cursor.getColumnIndex("valor")));

                //ADICIONANDO UMA PESSOA NA CONTA
                contas.add(conta);

                //VAI PARA O PRÓXIMO REGISTRO
                cursor.moveToNext();
            }
            //RETORNANDO A LISTA DE CONTA
            return contas;
        }
}
