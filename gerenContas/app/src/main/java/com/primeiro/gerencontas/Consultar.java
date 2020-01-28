package com.primeiro.gerencontas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.primeiro.gerencontas.BancoDados.DBContas;
import com.primeiro.gerencontas.BancoDados.dbControle;
import com.primeiro.gerencontas.ObjConta.Conta;
import com.primeiro.gerencontas.Uteis.LinhasConsulta;

import java.util.ArrayList;
import java.util.List;

/**
 * created by David nilo
 * versão 1
 * 04/01/2020**/
//CLASSE QUE CONTROLA A CONSULTA.
public class Consultar extends Activity {

    DBContas dbContas;
    dbControle controle;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;
    ArrayAdapter<String> adapter;//array adapitado para montar
    ArrayList<String> arrayList;// array liste
    //CRIANDO UM OBJETO DO TIPO ListView PARA RECEBER OS REGISTROS DE UM ADAPTER
    ListView listViewContas;
    //CRIANDO O BOTÃO VOLTAR PARA RETORNAR PARA A TELA COM AS OPÇÕES
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        //VINCULANDO O LISTVIEW DA TELA AO OBJETO CRIADO
        listViewContas = (ListView)this.findViewById(R.id.listViewContas);

        //VINCULANDO O BOTÃO VOLTAR DA TELA AO OBJETO CRIADO
        buttonVoltar    = (Button)this.findViewById(R.id.buttonVoltar);

        //CHAMA O MÉTODO QUE CARREGA AS PESSOAS CADASTRADAS NA BASE DE DADOS
        this.CarregarCOntaCadastradas();

        //CHAMA O MÉTODO QUE CRIA O EVENTO PARA O BOTÃO VOLTAR
        this.CriarEvento();

    }
//************************************************************************************************
//MÉTODO QUE CRIA EVENTO PARA O BOTÃO VOLTAR
    protected  void CriarEvento(){

        buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //REDIRECIONA PARA A TELA PRINCIPAL
                Intent intentInicio = new Intent(getApplicationContext(), TelaInical.class);
                startActivity(intentInicio);

                //FINALIZA A ATIVIDADE ATUAL
                finish();
            }
        });
    }
//*************************************************************************************************
    //MÉTODO QUE CONSULTA AS CONTA CADASTRADAS
        public void CarregarCOntaCadastradas(){
           /* List<Conta> contas = controle.SelecionarTodas();
            arrayList = new ArrayList<String>();
            adapter = new ArrayAdapter<String>(Consultar.this, android.R.layout.simple_list_item_1,arrayList);
            listViewContas.setAdapter(adapter);

            for (Conta c:contas){
                arrayList.add(c.getCodigo() + "-" + c.getNome());
                adapter.notifyDataSetChanged();
            }*/

            dbControle bancodados=  new dbControle(this);

            //BUSCA AS CONTAS CADASTRADAS
            List<Conta> contaList = bancodados.SelecionarTodas();

            //SETA O ADAPTER DA LISTA COM OS REGISTROS RETORNADOS DA BASE
            listViewContas.setAdapter(new LinhasConsulta(this,contaList));
        }
}
